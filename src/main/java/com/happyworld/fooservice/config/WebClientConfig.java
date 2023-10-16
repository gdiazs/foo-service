package com.happyworld.fooservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig
{

    @Bean
    public WebClient webClient( WebClient.Builder builder )
    {
        builder.baseUrl( "https://jsonplaceholder.typicode.com" );
        return builder.build();
    }
}
