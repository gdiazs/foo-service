package com.happyworld.fooservice.exceptions;

public class BarServiceException extends RuntimeException
{

    public BarServiceException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
