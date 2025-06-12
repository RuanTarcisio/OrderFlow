package com.rtarcisio.gatewayms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtGrantedAuthoritiesConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // Desabilita CSRF para APIs (comum em gateways e APIs REST)
                .authorizeExchange(exchanges -> exchanges.pathMatchers(HttpMethod.GET).permitAll()
                        .pathMatchers("/product-category/**").hasRole("PRODUCT")
                        .pathMatchers("/product/**").hasRole("PRODUCT")
                        .pathMatchers("/orders/**").hasRole("ADMIN")
                        .pathMatchers("/payments/**").permitAll()
                        .pathMatchers("/eazybank/cards/**").hasRole("CARDS")
                        .pathMatchers("/eazybank/loans/**").hasRole("LOANS"))
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                // 6. Configura o JWK Set URI do seu Spring Authorization Server
                                // É aqui que o Gateway busca as chaves públicas para validar os JWTs
                                .jwkSetUri("http://localhost:9000/oauth2/jwks")
                                // 7. Opcional: Configura um conversor para extrair roles/authorities do JWT
                                // Isso é crucial se suas roles não estão na claim padrão "scope" ou "aud"
                                .jwtAuthenticationConverter(reactiveJwtAuthenticationConverter())
                        )
                );
        return serverHttpSecurity.build();
//                .oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec
//                        .jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(grantedAuthoritiesExtractor())));
//        serverHttpSecurity.csrf(csrfSpec -> csrfSpec.disable());
//        return serverHttpSecurity.build();
    }

    /**
     * Define um conversor para extrair as GrantedAuthorities (roles) do JWT.
     * O Spring Authorization Server, por padrão, coloca as roles em uma claim "roles".
     * Este conversor lê essa claim e as transforma em GrantedAuthorities.
     */
    @Bean
    public ReactiveJwtAuthenticationConverter reactiveJwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        // Define o nome da claim no JWT onde as roles estão (ex: "roles", "authorities")
        grantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
        // Opcional: define um prefixo para as roles (ex: "ROLE_"). O Spring Security adiciona "ROLE_" por padrão.
        // Se suas roles no JWT já vêm como "ROLE_ADMIN", você pode remover o prefixo aqui ou no SAS.
        // Se no SAS você removeu o "ROLE_", aqui você pode adicionar de volta se quiser usar hasRole("ADMIN").
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_"); // Adicionar se no SAS você removeu o prefixo

        ReactiveJwtAuthenticationConverter jwtAuthenticationConverter = new ReactiveJwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new ReactiveJwtGrantedAuthoritiesConverterAdapter(grantedAuthoritiesConverter));
        return jwtAuthenticationConverter;
    }

//    private Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor() {
//        JwtAuthenticationConverter jwtAuthenticationConverter =
//                new JwtAuthenticationConverter();
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter
//                (new KeycloakRoleConverter());
//        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
//    }

}
