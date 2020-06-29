package com.example.fizzbuzz.fragment.service.provider.delegating;

import com.example.fizzbuzz.fragment.service.FizzBuzzFragment;
import com.example.fizzbuzz.fragment.service.provider.FragmentProvider;

import java.util.Optional;

public class FizzFragmentProvider extends AbstractDelegatingFragmentProvider {

    private static final FizzBuzzFragment FRAGMENT = new FizzBuzzFragment("Fizz");

    public FizzFragmentProvider(FragmentProvider delegate) {
        super(delegate);
    }

    @Override
    Optional<FizzBuzzFragment> get(int position) {
        return Optional.of(position)
            .filter(pos -> pos % 3 == 0 && pos % 5 != 0)
            .map(pos -> FRAGMENT);
    }
}
