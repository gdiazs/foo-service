package com.happyworld.fooservice.controllers;

import com.happyworld.fooservice.dto.ElementDto;
import com.happyworld.fooservice.entities.Foo;
import com.happyworld.fooservice.exceptions.advices.FooControllerAdvice;
import com.happyworld.fooservice.services.FooService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Validated
@Controller
@RequestMapping( "/foos" )
public class FooController
{

    private final static Logger LOGGER = LoggerFactory.getLogger( FooControllerAdvice.class );

    private final FooService fooService;

    public FooController( FooService fooService )
    {
        this.fooService = fooService;
    }

    @GetMapping
    public ResponseEntity<List<Foo>> get()
    {
        return ResponseEntity.ok( this.fooService.getAllFoo() );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<Foo> getById( @PathVariable String id )
    {
        return ResponseEntity.ok( this.fooService.findFoo( id ) );
    }

    @PostMapping
    public ResponseEntity<Foo> post()
    {
        this.fooService.saveFoo();
        return ResponseEntity.ok().build();
    }

    @PostMapping( "/elements" )
    public ResponseEntity<Foo> saveElement( @RequestBody @Valid ElementDto elementDto )
    {
        this.fooService.saveFoo();
        return ResponseEntity.ok().build();
    }

}
