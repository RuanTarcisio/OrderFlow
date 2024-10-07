package com.rtarcisio.usernotification.dtos.input;

import com.rtarcisio.usernotification.validations.FileContentType;
import com.rtarcisio.usernotification.validations.FileSize;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@Builder
public class UsuarioInput {

	@FileSize(max = "1MB")
	@FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
	private MultipartFile arquivo;

	@Email
	@NotEmpty
	@Column(unique = true)
	private String email;

	@NotEmpty
	private String passwd;

	@NotEmpty
	private String cpf;

	@NotEmpty
	private String name;

	@NotNull
	private LocalDate dataNascimento;

	private Boolean isAdmin;
	
}
