package com.rtarcisio.usernotification.controllers;

import com.rtarcisio.usernotification.dtos.PersonDto;
import com.rtarcisio.usernotification.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
