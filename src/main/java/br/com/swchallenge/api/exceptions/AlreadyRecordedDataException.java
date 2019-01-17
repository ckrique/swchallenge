package br.com.swchallenge.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Planeta já cadastrado anteriormente.")
public class AlreadyRecordedDataException extends SwChallengeException {
	private static final long serialVersionUID = -6940214018201903821L;
}