package com.fpsoluctionstechs.hortfruitonline.config.validacao;

import com.fpsoluctionstechs.hortfruitonline.config.exceptions.NegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidacaoHandler {

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NegocioException.class)
    public NegocioException handle(NegocioException exception) {
        return exception;
    }

}