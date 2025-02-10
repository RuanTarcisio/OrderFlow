package com.rtarcisio.inventaryms.services.exceptions.templ;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Data
@RequiredArgsConstructor
@Schema(
		name = "ErrorResponse",
		description = "Schema to hold error response information"
)
public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(
			description = "API path invoked by client"
	)
	private String apiPath;

	@Schema(
			description = "Error code representing the error happened"
	)
	private Integer status;

	@Schema(
			description = "Error message representing the error happened"
	)
	private String message;

	@Schema(
			description = "Time representing when the error happened"
	)
	private String timeStamp;
	

	public StandardError(Integer status, String message, String path) {
		super();
		this.apiPath = path;
		this.status = status;
		this.message = message;
		this.timeStamp = converterParaTimeBrasil();
	}

	public static String converterParaTimeBrasil() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String time = ZonedDateTime.now().format(formatter);
		
		return time;
	}



}

