package com.rtarcisio.usernotification.controllers;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthenticationController {

//    private final AuthenticationManager authenticationManager;
//
//    private final TokenService tokenService;
//
//    private final PersonRepository repository;
//
//    private final PersonService service;
//
//    @Autowired
//    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService, PersonRepository repository, PersonService service) {
//        this.authenticationManager = authenticationManager;
//        this.tokenService = tokenService;
//        this.repository = repository;
//        this.service = service;
//    }
//
//    @PostMapping()
//    public ResponseEntity login(@Valid @RequestBody AuthenticationDTO data){
//        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
//        var auth = this.authenticationManager.authenticate(usernamePassword);
//
//        var token = tokenService.generateToken((CustomUserDetails)auth.getPrincipal());
//
//        return ResponseEntity.ok(new LoginResponseDTO(token));
//    }
//
//    @Transactional
//    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<Void> salvarUsuario(@Valid UsuarioInput usuarioInput) throws IOException {
//
//        Person person = service.savePerson(usuarioInput);
//
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(person.getId()).toUri();
//        return ResponseEntity.created(uri).build();
//    }

}
