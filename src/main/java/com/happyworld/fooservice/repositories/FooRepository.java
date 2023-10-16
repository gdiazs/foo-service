package com.happyworld.fooservice.repositories;

import com.happyworld.fooservice.entities.Foo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface FooRepository extends CrudRepository<Foo, UUID>
{

    @Query( "select p from Foo p where p.id = :id" )
    public List<Foo> doSomethingWrong( String id );

}
