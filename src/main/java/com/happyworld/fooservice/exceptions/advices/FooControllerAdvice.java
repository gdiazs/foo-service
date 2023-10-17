package com.happyworld.fooservice.exceptions.advices;

import com.happyworld.fooservice.dto.ApiErrorDto;
import com.happyworld.fooservice.exceptions.BarServiceException;
import com.happyworld.fooservice.exceptions.FooServiceException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FooControllerAdvice
{

    private final static Logger LOGGER = LoggerFactory.getLogger( FooControllerAdvice.class );

    //Log Root Cause if available otherwise log all trace
    private static void logError( FooServiceException ex )
    {
        var rootCause = NestedExceptionUtils.getMostSpecificCause( ex );
        LOGGER.error( ExceptionUtils.getMessage( ex ), rootCause );
    }

    @ExceptionHandler( FooServiceException.class )
    public ResponseEntity<ApiErrorDto> handleFooServiceException( FooServiceException ex )
    {

        logError( ex );

        //Evaluate Response code to send
        var errorDto = new ApiErrorDto();

        if ( ex.getCause() instanceof BarServiceException )
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
