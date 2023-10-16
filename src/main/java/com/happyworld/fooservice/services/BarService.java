package com.happyworld.fooservice.services;

import com.happyworld.fooservice.exceptions.BarServiceException;
import org.springframework.stereotype.Service;

@Service
public class BarService
{

    public void settingUpBar()
    {
        try
        {
            throw new IllegalStateException( "Invalid Bar state error" );
        }
        catch ( IllegalStateException ex )
        {
            throw new BarServiceException( "Error setting up exception", ex );
        }

    }
}
