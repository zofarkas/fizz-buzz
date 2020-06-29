package com.example.fizzbuzz.fragment.service.configuration;

import com.example.fizzbuzz.fragment.service.DefaultFizzBuzzFragmentService;
import com.example.fizzbuzz.fragment.service.FizzBuzzFragmentService;
import com.example.fizzbuzz.fragment.service.provider.FragmentProvider;
import com.example.fizzbuzz.fragment.service.provider.NumberFragmentProvider;
import com.example.fizzbuzz.fragment.service.provider.delegating.BuzzFragmentProvider;
import com.example.fizzbuzz.fragment.service.provider.delegating.FizzBuzzFragmentProvider;
import com.example.fizzbuzz.fragment.service.provider.delegating.FizzFragmentProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FizzBuzzFragmentServiceConfiguration {

    @Bean
    public FizzBuzzFragmentService fizzBuzzFragmentService() {
        return new DefaultFizzBuzzFragmentService(fragmentProvider());
    }

    private FragmentProvider fragmentProvider() {
        FragmentProvider numberFragmentProvider = new NumberFragmentProvider();
        FragmentProvider fizzFragmentProvider = new FizzFragmentProvider(numberFragmentProvider);
        FragmentProvider buzzFragmentProvider = new BuzzFragmentProvider(fizzFragmentProvider);
        return new FizzBuzzFragmentProvider(buzzFragmentProvider);
    }
}
