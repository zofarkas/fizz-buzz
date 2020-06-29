package com.example.fizzbuzz.sequence.service;

import com.example.fizzbuzz.fragment.service.FizzBuzzFragment;

import java.util.List;
import java.util.Objects;

public class FizzBuzzSequence {

    private final List<FizzBuzzFragment> sequence;

    public FizzBuzzSequence(List<FizzBuzzFragment> sequence) {
        this.sequence = List.copyOf(sequence);
    }

    public List<FizzBuzzFragment> getSequence() {
        return sequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FizzBuzzSequence that = (FizzBuzzSequence) o;
        return sequence.equals(that.sequence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sequence);
    }

    @Override
    public String toString() {
        return "FizzBuzzSequence{" +
            "sequence=" + sequence +
            '}';
    }
}
