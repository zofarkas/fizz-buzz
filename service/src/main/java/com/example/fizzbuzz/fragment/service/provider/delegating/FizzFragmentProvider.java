package com.example.fizzbuzz.fragment.service.provider.delegating;

import com.example.fizzbuzz.fragment.service.FizzBuzzFragment;
import com.example.fizzbuzz.fragment.service.provider.FragmentProvider;

import java.util.Optional;
import java.util.function.Predicate;

public class FizzFragmentProvider extends AbstractDelegatingFragmentProvider {

    public FizzFragmentProvider(FragmentProvider delegate) {
        super(delegate, position -> new FizzBuzzFragment("Fizz"));
    }

    @Override
    Predicate<Integer> isApplicable() {
        return position -> position % 3 == 0 && position % 5 != 0;
    }
}
