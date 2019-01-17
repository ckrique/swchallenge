package br.com.swchallenge.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Erro ao tentar processar requisição.")
public class InternalFailureException extends SwChallengeException {
	private static final long serialVersionUID = -9206955361193063095L;		
}
