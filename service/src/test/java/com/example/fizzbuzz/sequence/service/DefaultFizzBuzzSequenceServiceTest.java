package com.example.fizzbuzz.sequence.service;

import com.example.fizzbuzz.fragment.service.FizzBuzzFragment;
import com.example.fizzbuzz.fragment.service.FizzBuzzFragmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class DefaultFizzBuzzSequenceServiceTest {

    private FizzBuzzSequenceService service;
    @Mock
    private FizzBuzzFragmentService fragmentService;

    @BeforeEach
    public void initContext() {
        initMocks(this);
        service = new DefaultFizzBuzzSequenceService(fragmentService);
    }

    @Test
    public void shouldReturnFizzBuzzSequenceTillInputPosition() {
        FizzBuzzFragment fragmentForOne = new FizzBuzzFragment("1");
        FizzBuzzFragment fragmentForTwo = new FizzBuzzFragment("2");
        FizzBuzzFragment fragmentForThree = new FizzBuzzFragment("Fizz");
        FizzBuzzFragment fragmentForFour = new FizzBuzzFragment("4");
        FizzBuzzFragment fragmentForFive = new FizzBuzzFragment("Buzz");
        FizzBuzzSequence expectedSequence = new FizzBuzzSequence(List.of(
            fragmentForOne,
            fragmentForTwo,
            fragmentForThree,
            fragmentForFour,
            fragmentForFive));

        when(fragmentService.getFragment(1)).thenReturn(fragmentForOne);
        when(fragmentService.getFragment(2)).thenReturn(fragmentForTwo);
        when(fragmentService.getFragment(3)).thenReturn(fragmentForThree);
        when(fragmentService.getFragment(4)).thenReturn(fragmentForFour);
        when(fragmentService.getFragment(5)).thenReturn(fragmentForFive);

        assertThat(service.determineSequence(5)).isEqualTo(expectedSequence);
    }

    @Test
    public void shouldThrowExceptionWhenLastElementIsNegative() {
        assertThatThrownBy(() -> service.determineSequence(-1))
            .isInstanceOf(IllegalArgumentException.class)
            .as("Last element must be positive but was -1");
    }

    @Test
    public void shouldThrowExceptionWhenLastElementIsZero() {
        assertThatThrownBy(() -> service.determineSequence(0))
            .isInstanceOf(IllegalArgumentException.class)
            .as("Last element must be positive but was 0");
    }
}