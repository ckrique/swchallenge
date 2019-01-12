package br.com.swchallenge.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Nenhum planeta foi encontrado no banco de dados.")
public class PlanetsNotFoudException extends SwChallengeException {
	
	private static final long serialVersionUID = 644058497626013900L;
	
}
