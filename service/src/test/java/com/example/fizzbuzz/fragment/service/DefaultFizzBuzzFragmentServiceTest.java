package com.example.fizzbuzz.fragment.service;


import com.example.fizzbuzz.fragment.service.provider.FragmentProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class DefaultFizzBuzzFragmentServiceTest {

    private FizzBuzzFragmentService service;
    @Mock
    private FragmentProvider fragmentProvider;

    @BeforeEach
    public void initContext() {
        initMocks(this);
        service = new DefaultFizzBuzzFragmentService(fragmentProvider);
    }

    @Test
    public void shouldReturnFragmentFromProvider() {
        FizzBuzzFragment expectedFragment = new FizzBuzzFragment("5");
        when(fragmentProvider.getFragment(5)).thenReturn(expectedFragment);
        assertThat(service.getFragment(5)).isEqualTo(expectedFragment);
    }

    @Test
    public void shouldThrowErrorInCaseOfNegativeNumberAsInput() {
        assertThatThrownBy(() -> service.getFragment(-1))
            .isInstanceOf(IllegalArgumentException.class)
            .as("Position must be positive but was -1");
    }

    @Test
    public void shouldThrowErrorInCaseOfZeroAsInput() {
        assertThatThrownBy(() -> service.getFragment(-1))
            .isInstanceOf(IllegalArgumentException.class)
            .as("Position must be positive but was -1");
    }
}