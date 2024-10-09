package com.rtarcisio.usernotification.mappers;

import com.rtarcisio.usernotification.domains.Person;
import com.rtarcisio.usernotification.dtos.PersonDto;
import com.rtarcisio.usernotification.dtos.input.UsuarioInput;
import com.rtarcisio.usernotification.enums.UserRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

	public static Person inputToPerson(UsuarioInput input) {

		String encryptedPassword = new BCryptPasswordEncoder().encode(input.getPasswd());
		UserRole role;
		if (input.getIsAdmin() == true) {
			role = UserRole.ADMIN;
		}else role = UserRole.USER;

			return Person.builder()
				.cpf(input.getCpf())
				.email(input.getEmail())
				.dataNascimento(input.getDataNascimento())
				.name(input.getName())
				.passwd(encryptedPassword)
				.role(role)
				.build();
				
	}

	 public static PersonDto personToDTO(Person person){
	        return PersonDto.builder()
					.id(person.getId())
					.cpf(person.getCpf())
					.email(person.getEmail())
					.nomeCompleto(person.getName())
					.dataNascimento(person.getDataNascimento())
	                .build();
	    }
}
