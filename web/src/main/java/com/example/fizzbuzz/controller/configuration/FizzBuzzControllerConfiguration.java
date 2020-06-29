package com.example.fizzbuzz.controller.configuration;

import com.example.fizzbuzz.controller.FizzBuzzController;
import com.example.fizzbuzz.sequence.service.FizzBuzzSequenceService;
import com.example.fizzbuzz.sequence.service.config.FizzBuzzSequenceServiceConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.inject.Inject;

@Configuration
@Import(FizzBuzzSequenceServiceConfiguration.class)
public class FizzBuzzControllerConfiguration {

    @Inject
    private FizzBuzzSequenceService fizzBuzzSequenceService;

    @Bean
    public FizzBuzzController fizzBuzzController() {
        return new FizzBuzzController(fizzBuzzSequenceService);
    }
}
