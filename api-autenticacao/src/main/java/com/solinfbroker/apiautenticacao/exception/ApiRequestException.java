package com.solinfbroker.apiautenticacao.exception;

public class ApiRequestException extends RuntimeException{

    public ApiRequestException (String mensagem){
        super(mensagem);
    }
    public ApiRequestException (String mensagem, Throwable causa){
        super(mensagem, causa);
    }
}
