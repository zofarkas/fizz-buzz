package com.example.fizzbuzz.fragment.service.delegating;

import com.example.fizzbuzz.fragment.service.FizzBuzzFragment;
import com.example.fizzbuzz.fragment.service.provider.FragmentProvider;
import com.example.fizzbuzz.fragment.service.provider.delegating.FizzBuzzFragmentProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class FizzBuzzFragmentProviderTest {

    private static final FizzBuzzFragment FRAGMENT_FROM_DELEGATE = new FizzBuzzFragment("anything");
    private FragmentProvider fragmentProvider;
    @Mock
    private FragmentProvider delegate;

    @BeforeEach
    public void initContext() {
        initMocks(this);
        fragmentProvider = new FizzBuzzFragmentProvider(delegate);
    }

    public static Stream<Arguments> happyCases() {
        return Stream.of(
            Arguments.of(15, new FizzBuzzFragment("Fizz Buzz")),
            Arguments.of(30, new FizzBuzzFragment("Fizz Buzz")),
            Arguments.of(150, new FizzBuzzFragment("Fizz Buzz")),
            Arguments.of(6000, new FizzBuzzFragment("Fizz Buzz")));
    }

    @ParameterizedTest
    @MethodSource("happyCases")
    public void shouldReturnFizzBuzzFragmentWhenInputIsDivisibleByBothThreeAndFive(int position, FizzBuzzFragment expectedFragment) {
        assertThat(fragmentProvider.getFragment(position)).isEqualTo(expectedFragment);
        verifyNoInteractions(delegate);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 667})
    public void shouldDelegateCallWhenInputIsNotDivisibleByThreeAndFive(int position) {
        when(delegate.getFragment(position)).thenReturn(FRAGMENT_FROM_DELEGATE);
        assertThat(fragmentProvider.getFragment(position)).isEqualTo(FRAGMENT_FROM_DELEGATE);
        verify(delegate).getFragment(position);
    }
}

