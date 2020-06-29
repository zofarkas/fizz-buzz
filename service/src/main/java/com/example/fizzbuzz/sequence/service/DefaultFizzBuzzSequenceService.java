package com.example.fizzbuzz.sequence.service;

import com.example.fizzbuzz.fragment.service.FizzBuzzFragmentService;

import java.util.stream.IntStream;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class DefaultFizzBuzzSequenceService implements FizzBuzzSequenceService {

    private final FizzBuzzFragmentService fragmentService;

    public DefaultFizzBuzzSequenceService(FizzBuzzFragmentService fragmentService) {
        this.fragmentService = requireNonNull(fragmentService);
    }

    @Override
    public FizzBuzzSequence determineSequence(int lastElement) {
        if (lastElement < 1) {
            throw new IllegalArgumentException("Last element must be positive but was " + lastElement);
        }
        return IntStream.range(1, lastElement + 1)
            .mapToObj(fragmentService::getFragment)
            .collect(collectingAndThen(toList(), FizzBuzzSequence::new));
    }
}
