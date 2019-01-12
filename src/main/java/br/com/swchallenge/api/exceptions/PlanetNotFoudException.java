package br.com.swchallenge.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Planeta não encontrado")
public class PlanetNotFoudException extends SwChallengeException {

	private static final long serialVersionUID = 5606493062491843925L;	
	
}
