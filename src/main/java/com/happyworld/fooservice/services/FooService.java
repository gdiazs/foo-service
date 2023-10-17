package com.happyworld.fooservice.services;

import com.happyworld.fooservice.entities.Foo;
import com.happyworld.fooservice.exceptions.BarServiceException;
import com.happyworld.fooservice.exceptions.FooServiceException;
import com.happyworld.fooservice.repositories.FooRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import java.util.List;

@Service
public class FooService
{

    private final FooRepository fooRepository;

    private final BarService barService;

    private final WebClient webClient;

    public FooService( FooRepository fooRepository, BarService barService, WebClient webClient )
    {
        this.fooRepository = fooRepository;
        this.barService = barService;
        this.webClient = webClient;
    }

    public List<Foo> getAllFoo()
    {
        final String id = "abc";
        try
        {
            return this.fooRepository.doSomethingWrong( id );
        }
        catch ( DataAccessException ex )
        {
            throw new FooServiceException( "Error getting foo values", ex );
        }
    }

    public Foo findFoo( String id )
    {

        try
        {
            return this.webClient.get().uri( "/{id}", id ).retrieve().bodyToMono( Foo.class ).block();
        }
        catch ( WebClientException ex )
        {
            throw new FooServiceException( String.format( "Error getting foo with id: %s", id ), ex );
        }
    }

    public void saveFoo()
    {
        try
        {
            this.fooRepository.findAll();
            this.barService.settingUpBar();
        }
        catch ( DataAccessException | BarServiceException ex )
        {
            throw new FooServiceException( "Error Saving Foo", ex );
        }
    }
}
