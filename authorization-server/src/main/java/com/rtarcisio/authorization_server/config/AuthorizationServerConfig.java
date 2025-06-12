package com.rtarcisio.authorization_server.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.InMemoryOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity // Habilita o Spring Security
public class AuthorizationServerConfig {

    // 1. Cadeia de Filtros para o Endpoint do Servidor de Autorização
    @Bean
    @Order(1) // Garante que esta cadeia de filtros seja processada primeiro
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http); // Aplica as configurações padrão do SAS

        // Habilita o OpenID Connect
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());

        // Redireciona para a página de login customizada se a autenticação falhar
        http.exceptionHandling(exceptions ->
                        exceptions.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                )
                // Configura o SAS como um Resource Server para si mesmo (opcional, mas boa prática para APIs internas)
                .oauth2ResourceServer(oauth2ResourceServer ->
                        oauth2ResourceServer.jwt(Customizer.withDefaults()));

        return http.build();
    }

    // 2. Cadeia de Filtros para a Autenticação de Formulário (página de login)
    @Bean
    @Order(2) // Processada depois da cadeia de filtros do SAS
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/login", "/error").permitAll() // Permite acesso à página de login e erro
                                .anyRequest().authenticated() // Qualquer outra requisição exige autenticação
                )
                .formLogin(Customizer.withDefaults()); // Habilita a autenticação por formulário padrão do Spring Security
        return http.build();
    }

    // 3. UserDetailsService: Onde o SAS encontra os usuários
    @Bean
    public UserDetailsService userDetailsService() {
        // Exemplo simples com usuários em memória para demonstração.
        // Em produção, você integraria com um banco de dados, LDAP, etc.
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder().encode("password")) // Codifica a senha
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("admin")) // Codifica a senha
                .roles("ADMIN", "USER") // Múltiplas roles
                .build();
        return new org.springframework.security.provisioning.InMemoryUserDetailsManager(user, admin);
    }

    // 4. RegisteredClientRepository: Onde o SAS encontra os clientes OAuth2
    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        // Exemplo de um cliente em memória para demonstração.
        // Em produção, você persistiria isso em um banco de dados.
        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("product-management-client") // ID do cliente
                .clientSecret(passwordEncoder().encode("secret")) // Senha/segredo do cliente (codificado)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC) // Método de autenticação
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE) // Fluxo de código de autorização
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN) // Permite refresh token
                .redirectUri("http://127.0.0.1:8080/authorized") // URI de redirecionamento para o cliente
                .redirectUri("http://localhost:8080/login/oauth2/code/product-management-client") // Para clientes Spring Boot
                .postLogoutRedirectUri("http://127.0.0.1:8080/") // URI de redirecionamento pós-logout
                .scope(OidcScopes.OPENID) // Escopo OIDC padrão
                .scope(OidcScopes.PROFILE) // Escopo de perfil OIDC
                .scope("product.read") // Escopo personalizado para ler produtos
                .scope("product.write") // Escopo personalizado para escrever produtos
                .clientSettings(ClientSettings.builder().requireProofKey(false).build()) // PKCE desativado para este exemplo simples (mas recomendado para clientes públicos)
                .build();

        return new InMemoryRegisteredClientRepository(registeredClient);
    }

    // 5. JWKSource: Geração de chaves para assinar JWTs
    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        // Gera um par de chaves RSA em tempo de execução.
        // EM PRODUÇÃO: USE UM KEYSTORE SEGURO (ex: JKS) OU UM SERVIÇO DE GERENCIAMENTO DE CHAVES.
        RSAKey rsaKey = Jwks.generateRsa();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    // 6. JwtDecoder: Decodificador de JWT para o próprio SAS (se ele atuar como Resource Server para si mesmo)
    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    // 7. AuthorizationServerSettings: Configurações globais do SAS
    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        // O "issuer" é a URL base do seu Authorization Server.
        // É CRUCIAL que ela seja a URL real e acessível do seu SAS em produção.
        return AuthorizationServerSettings.builder().issuer("http://localhost:9000").build();
    }

    // 8. PasswordEncoder: Para codificar senhas de usuários e segredos de clientes
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // 9. OAuth2TokenCustomizer: Para adicionar claims personalizadas ao JWT
    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtTokenCustomizer() {
        return (context) -> {
            if (context.getTokenType().getValue().equals("access_token")) {
                Authentication principal = context.getPrincipal();
                Set<String> authorities = new HashSet<>();
                // Adiciona as roles (GrantedAuthorities) do usuário como uma claim "roles" no JWT
                authorities.addAll(principal.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .map(s -> s.replace("ROLE_", "")) // Opcional: remove o prefixo "ROLE_"
                        .collect(Collectors.toSet()));

                // Você pode adicionar outras claims do usuário aqui, ex: userId, email
                // Exemplo: se o principal.getName() for o ID do usuário
                context.getClaims().claim("user_id", principal.getName());

                context.getClaims().claim("roles", authorities);
            }
        };
    }

    // 10. OAuth2AuthorizationService: Para persistir informações de autorização (tokens, etc.)
    // Para um MVP, InMemory é suficiente. Em produção, você precisaria de uma implementação JpaOAuth2AuthorizationService.
    @Bean
    public OAuth2AuthorizationService authorizationService() {
        return new InMemoryOAuth2AuthorizationService();
    }

    // Classe auxiliar para gerar chaves RSA (não para uso em produção)
    static class Jwks {
        public static RSAKey generateRsa() {
            KeyPair keyPair = generateRsaKey();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            return new RSAKey.Builder(publicKey)
                    .privateKey(privateKey)
                    .keyID(UUID.randomUUID().toString())
                    .build();
        }

        private static KeyPair generateRsaKey() {
            try {
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
                keyPairGenerator.initialize(2048); // Tamanho da chave
                return keyPairGenerator.generateKeyPair();
            } catch (Exception ex) {
                throw new IllegalStateException(ex);
            }
        }
    }
}