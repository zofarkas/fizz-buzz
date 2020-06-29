package com.example.fizzbuzz.controller;

import java.util.List;
import java.util.Objects;

public class FizzBuzzResponseBody {
    private final List<String> data;

    public FizzBuzzResponseBody(List<String> data) {
        this.data = List.copyOf(data);
    }

    public List<String> getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FizzBuzzResponseBody that = (FizzBuzzResponseBody) o;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        return "FizzBuzzResponseBody{" +
            "data=" + data +
            '}';
    }
}
