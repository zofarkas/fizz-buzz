package com.example.fizzbuzz.fragment.service.provider.delegating;

import com.example.fizzbuzz.fragment.service.FizzBuzzFragment;
import com.example.fizzbuzz.fragment.service.provider.FragmentProvider;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

public abstract class AbstractDelegatingFragmentProvider implements FragmentProvider {

    private final FragmentProvider delegate;

    public AbstractDelegatingFragmentProvider(FragmentProvider delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public final FizzBuzzFragment getFragment(int position) {
        return get(position)
            .orElseGet(() -> delegate.getFragment(position));
    }

    abstract Optional<FizzBuzzFragment> get(int position);
}
