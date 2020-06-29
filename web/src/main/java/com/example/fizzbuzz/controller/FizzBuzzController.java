package com.example.fizzbuzz.controller;

import com.example.fizzbuzz.fragment.service.FizzBuzzFragment;
import com.example.fizzbuzz.sequence.service.FizzBuzzSequenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@RestController
public class FizzBuzzController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FizzBuzzController.class);
    public static final ResponseEntity<Object> BAD_REQUEST = ResponseEntity.badRequest().build();
    public static final ResponseEntity<Object> INTERNAL_SERVER_ERROR = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    private final FizzBuzzSequenceService service;

    public FizzBuzzController(FizzBuzzSequenceService service) {
        this.service = requireNonNull(service);
    }

    @GetMapping(path = "/fizz-buzz")
    public ResponseEntity<FizzBuzzResponseBody> getSequence(@RequestParam("lastElement") int lastElement) {
        ResponseEntity response;

        try {
            response = service.determineSequence(lastElement).getSequence().stream()
                .map(FizzBuzzFragment::getValue)
                .collect(collectingAndThen(toList(), sequence -> ResponseEntity.ok(new FizzBuzzResponseBody(sequence))));
        } catch (IllegalArgumentException e) {
            response = BAD_REQUEST;
        } catch (Exception e) {
            LOGGER.error("Error generating Fizz Buzz sequence for lastElement={}", lastElement, e);
            response = INTERNAL_SERVER_ERROR;
        }

        return response;
    }

}
