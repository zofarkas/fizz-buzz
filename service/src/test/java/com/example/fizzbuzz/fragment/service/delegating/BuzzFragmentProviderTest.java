package com.example.fizzbuzz.fragment.service.delegating;

import com.example.fizzbuzz.fragment.service.FizzBuzzFragment;
import com.example.fizzbuzz.fragment.service.provider.FragmentProvider;
import com.example.fizzbuzz.fragment.service.provider.delegating.BuzzFragmentProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.MockitoAnnotations.initMocks;

class BuzzFragmentProviderTest {

    private static final FizzBuzzFragment FRAGMENT_FROM_DELEGATE = new FizzBuzzFragment("anything");
    private FragmentProvider fragmentProvider;
    @Mock
    private FragmentProvider delegate;

    @BeforeEach
    public void initContext() {
        initMocks(this);
        fragmentProvider = new BuzzFragmentProvider(delegate);
    }

    public static Stream<Arguments> happyCases() {
        return Stream.of(
            Arguments.of(5, new FizzBuzzFragment("Buzz")),
            Arguments.of(10, new FizzBuzzFragment("Buzz")),
            Arguments.of(20, new FizzBuzzFragment("Buzz")),
            Arguments.of(145, new FizzBuzzFragment("Buzz")));
    }

    @ParameterizedTest
    @MethodSource("happyCases")
    public void shouldReturnBuzzFragmentForInputsDivisibleByFiveButNotDivisibleByThree(int position, FizzBuzzFragment expectedFragment) {
        assertThat(fragmentProvider.getFragment(position)).isEqualTo(expectedFragment);
        verifyNoInteractions(delegate);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 9, 999})
    public void shouldDelegateCallWhenInputIsNotDivisibleByFive(int position) {
        when(delegate.getFragment(position)).thenReturn(FRAGMENT_FROM_DELEGATE);
        assertThat(fragmentProvider.getFragment(position)).isEqualTo(FRAGMENT_FROM_DELEGATE);
        verify(delegate).getFragment(position);
    }
}