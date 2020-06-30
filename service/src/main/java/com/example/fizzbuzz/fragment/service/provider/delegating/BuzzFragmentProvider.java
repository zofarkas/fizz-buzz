package com.example.fizzbuzz.fragment.service.provider.delegating;

import com.example.fizzbuzz.fragment.service.FizzBuzzFragment;
import com.example.fizzbuzz.fragment.service.provider.FragmentProvider;

import java.util.Optional;
import java.util.function.Predicate;

public class BuzzFragmentProvider extends AbstractDelegatingFragmentProvider {

    public BuzzFragmentProvider(FragmentProvider delegate) {
        super(delegate, position -> new FizzBuzzFragment("Buzz"));
    }

    @Override
    Predicate<Integer> isApplicable() {
        return position -> position % 5 == 0 && position % 3 != 0;
    }
}
