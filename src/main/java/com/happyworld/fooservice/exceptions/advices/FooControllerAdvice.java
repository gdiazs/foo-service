package com.happyworld.fooservice.exceptions.advices;

import com.happyworld.fooservice.dto.ApiErrorDto;
import com.happyworld.fooservice.exceptions.BarServiceException;
import com.happyworld.fooservice.exceptions.FooServiceException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FooControllerAdvice
{

    private final static Logger LOGGER = LoggerFactory.getLogger( FooControllerAdvice.class );

    @ExceptionHandler( FooServiceException.class )
    public ResponseEntity<ApiErrorDto> handleBarException( FooServiceException ex )
    {
        //Log Root Cause
        var rootCause = ExceptionUtils.getRootCause( ex );
        LOGGER.error( rootCause.getMessage(), rootCause );

        //Evaluate Response code to send
        Throwable cause = ex.getCause();
        var errorDto = new ApiErrorDto();

        if ( cause instanceof BarServiceException )
        {

            errorDto.setCode( "BAD_REQUEST" );
            errorDto.setMessage( ex.getMessage() );

            return ResponseEntity.badRequest().body( errorDto );
        }

        //Default Error
        errorDto.setCode( "ERR" );
        errorDto.setMessage( ex.getMessage() );
        return ResponseEntity.internalServerError().body( errorDto );

    }
}
