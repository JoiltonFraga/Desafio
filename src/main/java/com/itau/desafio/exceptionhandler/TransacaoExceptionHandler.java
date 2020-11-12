package com.itau.desafio.exceptionhandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TransacaoExceptionHandler extends ResponseEntityExceptionHandler{

}
