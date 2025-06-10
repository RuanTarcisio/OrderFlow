package com.rtarcisio.usernotification.security;


import com.rtarcisio.usernotification.repositories.PersonRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityFilter {//extends OncePerRequestFilter {

    final TokenService tokenService;

    final PersonRepository personRepository;

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        var token = this.recoverToken(request);
//
//        if(token != null){
//
//           try {
//               var login = tokenService.validateToken(token);
//
//               Person user = personRepository.findByEmail(login).orElseThrow(() -> new NoSuchElementException("Usuario nao encontrado "));
//               UserDetails userDetails = new com.rtarcisio.usernotification.security.CustomUserDetails(user.getId(), user.getEmail(), user.getName(), user.getRole().name(), user.getPasswd());
//
//
//               var authentication = new UsernamePasswordAuthenticationToken(user, user.getPasswd(), userDetails.getAuthorities());
//               SecurityContextHolder.getContext().setAuthentication(authentication);
//           }catch (Exception e){
//               throw new com.rtarcisio.usernotification.services.exceptions.InvalidTokenException("Invalid token");
//           }
//        }
//        filterChain.doFilter(request, response);
//    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
