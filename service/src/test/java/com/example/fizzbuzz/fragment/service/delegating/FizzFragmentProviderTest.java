package com.example.fizzbuzz.fragment.service.delegating;

import com.example.fizzbuzz.fragment.service.FizzBuzzFragment;
import com.example.fizzbuzz.fragment.service.provider.FragmentProvider;
import com.example.fizzbuzz.fragment.service.provider.delegating.FizzFragmentProvider;
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

class FizzFragmentProviderTest {

    private static final FizzBuzzFragment FRAGMENT_FROM_DELEGATE = new FizzBuzzFragment("anything");
    private FragmentProvider fragmentProvider;
    @Mock
    private FragmentProvider delegate;

    @BeforeEach
    public void initContext() {
        initMocks(this);
        fragmentProvider = new FizzFragmentProvider(delegate);
    }

    private static Stream<Arguments> happyCases() {
        return Stream.of(
            Arguments.of(3, new FizzBuzzFragment("Fizz")),
            Arguments.of(6, new FizzBuzzFragment("Fizz")),
            Arguments.of(9, new FizzBuzzFragment("Fizz")),
            Arguments.of(12, new FizzBuzzFragment("Fizz")));
    }

    @ParameterizedTest
    @MethodSource("happyCases")
    public void shouldReturnFizzFragmentWhenPositionIsDivisibleByThreeAndNotDivisibleByFive(int position, FizzBuzzFragment expectedFragment) {
        assertThat(fragmentProvider.getFragment(position)).isEqualTo(expectedFragment);
        verifyNoInteractions(delegate);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 15})
    public void shouldDelegateCallIfInputIsNotDivisibleByThree(int position) {
        when(delegate.getFragment(position)).thenReturn(FRAGMENT_FROM_DELEGATE);
        assertThat(fragmentProvider.getFragment(position)).isEqualTo(FRAGMENT_FROM_DELEGATE);
        verify(delegate).getFragment(position);
    }
}