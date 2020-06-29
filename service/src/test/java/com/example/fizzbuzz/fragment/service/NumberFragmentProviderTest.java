package com.example.fizzbuzz.fragment.service;

import com.example.fizzbuzz.fragment.service.FizzBuzzFragment;
import com.example.fizzbuzz.fragment.service.provider.FragmentProvider;
import com.example.fizzbuzz.fragment.service.provider.NumberFragmentProvider;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NumberFragmentProviderTest {

    private FragmentProvider fragmentProvider = new NumberFragmentProvider();

    @Test
    public void shouldReturnNumberFizzBuzzFragmentWithTheInputInIt() {
        assertThat(fragmentProvider.getFragment(123)).isEqualTo(new FizzBuzzFragment("123"));
    }
}