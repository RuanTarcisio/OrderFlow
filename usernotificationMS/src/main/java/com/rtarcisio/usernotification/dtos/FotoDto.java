package com.rtarcisio.usernotification.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@Builder
public class FotoDto {


	private String url;
	private String name;
	private String extension;
	private Long size;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate uploadDate;
	
}
