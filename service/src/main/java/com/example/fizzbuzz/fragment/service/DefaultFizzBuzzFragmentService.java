package com.example.fizzbuzz.fragment.service;

import com.example.fizzbuzz.fragment.service.provider.FragmentProvider;

import static java.util.Objects.requireNonNull;

public class DefaultFizzBuzzFragmentService implements FizzBuzzFragmentService {

    private final FragmentProvider fragmentProvider;

    public DefaultFizzBuzzFragmentService(FragmentProvider fragmentProvider) {
        this.fragmentProvider = requireNonNull(fragmentProvider);
    }

    @Override
    public FizzBuzzFragment getFragment(int position) {
        if (position < 1) {
            throw new IllegalArgumentException("Position must be positive but was " + position);
        }
        return fragmentProvider.getFragment(position);
    }
}
