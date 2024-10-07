package com.rtarcisio.usernotification.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.io.File;
import java.time.LocalDate;

@Builder
@Data
public class PersonDto{

        private Long id;

        private File arquivo;

        @NotEmpty
        @Email
        private String email;

        @NotEmpty
        @CPF
        private String cpf;

        @NotEmpty
        String nomeCompleto;

        @NotNull
        LocalDate dataNascimento;
}
