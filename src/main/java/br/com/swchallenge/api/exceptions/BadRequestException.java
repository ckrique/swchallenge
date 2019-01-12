package br.com.swchallenge.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Não foi possível processar a requisição. Os dados enviados são inválidos.")
public class BadRequestException extends SwChallengeException {

	private static final long serialVersionUID = -642097798468779262L;

}