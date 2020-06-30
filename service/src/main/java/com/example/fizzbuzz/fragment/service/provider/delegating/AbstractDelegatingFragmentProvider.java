package com.example.fizzbuzz.fragment.service.provider.delegating;

import com.example.fizzbuzz.fragment.service.FizzBuzzFragment;
import com.example.fizzbuzz.fragment.service.provider.FragmentProvider;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

public abstract class AbstractDelegatingFragmentProvider implements FragmentProvider {

    private final FragmentProvider delegate;
    private final Function<Integer, FizzBuzzFragment> fragmentFunction;

    public AbstractDelegatingFragmentProvider(FragmentProvider delegate, Function<Integer, FizzBuzzFragment> fragmentFunction) {
        this.delegate = requireNonNull(delegate);
        this.fragmentFunction = requireNonNull(fragmentFunction);
    }

    @Override
    public final FizzBuzzFragment getFragment(int position) {
        return Optional.of(position)
            .filter(pos -> isApplicable().test(pos))
            .map(fragmentFunction)
            .orElseGet(() -> delegate.getFragment(position));
    }

    abstract Predicate<Integer> isApplicable();
}
