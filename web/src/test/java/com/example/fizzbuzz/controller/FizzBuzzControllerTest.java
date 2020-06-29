package com.example.fizzbuzz.controller;

import com.example.fizzbuzz.fragment.service.FizzBuzzFragment;
import com.example.fizzbuzz.sequence.service.FizzBuzzSequence;
import com.example.fizzbuzz.sequence.service.FizzBuzzSequenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class FizzBuzzControllerTest {

    private FizzBuzzController controller;
    @Mock
    private FizzBuzzSequenceService service;

    @BeforeEach
    public void initContext() {
        initMocks(this);
        controller = new FizzBuzzController(service);
    }

    @Test
    public void shouldReturnFizzBuzzSequenceFromService() {
        FizzBuzzSequence sequence = new FizzBuzzSequence(List.of(
            new FizzBuzzFragment("1"),
            new FizzBuzzFragment("2"),
            new FizzBuzzFragment("Fizz")));
        when(service.determineSequence(3)).thenReturn(sequence);

        assertThat(controller.getSequence(3)).isEqualTo(ResponseEntity.ok(new FizzBuzzResponseBody(List.of("1", "2", "Fizz"))));
    }

    @Test
    public void shouldReturnBadRequestInCaseOfIllegalArgumentException() {
        when(service.determineSequence(5)).thenThrow(new IllegalArgumentException("error!"));

        assertThat(controller.getSequence(5)).isEqualTo(ResponseEntity.badRequest().build());
    }

    @Test
    public void shouldReturnInternalServerErrorInCaseOfAnyOtherExceptions() {
        when(service.determineSequence(5)).thenThrow(new RuntimeException());

        assertThat(controller.getSequence(5)).isEqualTo(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}