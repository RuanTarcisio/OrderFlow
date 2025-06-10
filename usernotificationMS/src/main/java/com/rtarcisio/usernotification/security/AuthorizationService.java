package com.rtarcisio.usernotification.security;

import com.rtarcisio.usernotification.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {// implements UserDetailsService {

    @Autowired
    PersonRepository repository;
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Person person = repository.findByEmail(username).orElseThrow(() -> new UsuarioNaoEncontradoException("User not found"));
//        UserDetails userDetails = new CustomUserDetails(person.getId(), person.getEmail(), person.getName(), person.getRole().name(), person.getPasswd());
//
//        return userDetails;
//    }
}
