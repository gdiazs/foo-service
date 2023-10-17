package com.happyworld.fooservice.exceptions.advices;

import com.happyworld.fooservice.dto.ApiErrorDto;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{

    private final static Logger LOGGER = LoggerFactory.getLogger( GlobalExceptionHandler.class );

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid( MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request )
    {
        //LOG Root cause
        var rootCause = NestedExceptionUtils.getMostSpecificCause( ex );
        LOGGER.error( ExceptionUtils.getMessage( ex ), rootCause );

        var errors = new HashMap<String, List<ApiErrorDto>>();
        var errorList = getErrorList( ex );
        errors.put( "errors", errorList );

        return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( errors );
    }

    private List<ApiErrorDto> getErrorList( MethodArgumentNotValidException ex )
    {

        return ex.getFieldErrors().stream()
                .map( error -> new ApiErrorDto( "INVALID_INPUT",
                        String.format( "%s %s", error.getField(), error.getDefaultMessage() ) ) )
                .collect( Collectors.toList() );
    }
}
