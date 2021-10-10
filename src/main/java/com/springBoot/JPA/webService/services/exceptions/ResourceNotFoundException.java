package com.springBoot.JPA.webService.services.exceptions;

// RUNTIME EXCEPTION - EXCEPTION QUE O COMPILADOR NÃO TE OBRIGA A TRATAR //
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(Object id) {
        super("Resource not found. Id " + id);
    }
}
