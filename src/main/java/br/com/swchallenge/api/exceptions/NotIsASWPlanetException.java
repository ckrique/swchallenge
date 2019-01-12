package br.com.swchallenge.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "O Planeta não pertence ao Universo de Star Wars.")
public class NotIsASWPlanetException extends SwChallengeException {

	private static final long serialVersionUID = 1389091833004444813L;


}