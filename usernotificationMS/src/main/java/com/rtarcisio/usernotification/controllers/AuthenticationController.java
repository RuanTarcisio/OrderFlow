package com.rtarcisio.usernotification.controllers;


import com.rtarcisio.usernotification.domains.Person;
import com.rtarcisio.usernotification.dtos.AuthenticationDTO;
import com.rtarcisio.usernotification.dtos.LoginResponseDTO;
import com.rtarcisio.usernotification.dtos.input.UsuarioInput;
import com.rtarcisio.usernotification.repositories.PersonRepository;
import com.rtarcisio.usernotification.security.CustomUserDetails;
import com.rtarcisio.usernotification.security.TokenService;
import com.rtarcisio.usernotification.services.PersonService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    private final PersonRepository repository;

    private final PersonService service;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService, PersonRepository repository, PersonService service) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.repository = repository;
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity login(@Valid @RequestBody AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((CustomUserDetails)auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @Transactional
    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> salvarUsuario(@Valid UsuarioInput usuarioInput) throws IOException {

        Person person = service.savePerson(usuarioInput);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(person.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
