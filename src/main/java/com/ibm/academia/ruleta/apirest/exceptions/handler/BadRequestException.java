package com.ibm.academia.ruleta.apirest.exceptions.handler;
public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }

    private static final long serialVersionUID = 1039366653606808071L;
}
