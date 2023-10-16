package com.happyworld.fooservice.exceptions;

public class FooServiceException extends RuntimeException
{

    public FooServiceException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
