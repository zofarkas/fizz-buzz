package com.example.fizzbuzz.sequence.service.config;

import com.example.fizzbuzz.fragment.service.FizzBuzzFragmentService;
import com.example.fizzbuzz.fragment.service.configuration.FizzBuzzFragmentServiceConfiguration;
import com.example.fizzbuzz.sequence.service.DefaultFizzBuzzSequenceService;
import com.example.fizzbuzz.sequence.service.FizzBuzzSequenceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.inject.Inject;

@Configuration
@Import(FizzBuzzFragmentServiceConfiguration.class)
public class FizzBuzzSequenceServiceConfiguration {

    @Inject
    private FizzBuzzFragmentService fizzBuzzFragmentService;

    @Bean
    public FizzBuzzSequenceService fizzBuzzSequenceService() {
        return new DefaultFizzBuzzSequenceService(fizzBuzzFragmentService);
    }
}
