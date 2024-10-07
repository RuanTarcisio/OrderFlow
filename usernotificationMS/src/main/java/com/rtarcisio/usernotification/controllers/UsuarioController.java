package com.rtarcisio.usernotification.controllers;

import com.rtarcisio.usernotification.domains.Person;
import com.rtarcisio.usernotification.dtos.PersonDto;
import com.rtarcisio.usernotification.dtos.input.UsuarioInput;
import com.rtarcisio.usernotification.services.PersonService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/users")
@Validated
public class UsuarioController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDto> findPersonById(@PathVariable Long id) {

        PersonDto dto = personService.buscarPorId(id);

        return ResponseEntity.ok(dto);
    }
}
