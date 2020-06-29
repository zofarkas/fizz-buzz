package com.example.fizzbuzz.fragment.service;

import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;

public class FizzBuzzFragment {

    private final String value;

    public FizzBuzzFragment(String value) {
        this.value = requireNonNull(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FizzBuzzFragment that = (FizzBuzzFragment) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return hash(value);
    }

    @Override
    public String toString() {
        return "FizzBuzzFragment{" +
            "value='" + value + '\'' +
            '}';
    }
}
