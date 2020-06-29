package com.example.fizzbuzz.fragment.service.provider;

import com.example.fizzbuzz.fragment.service.FizzBuzzFragment;

public class NumberFragmentProvider implements FragmentProvider {

    @Override
    public FizzBuzzFragment getFragment(int position) {
        return new FizzBuzzFragment(String.valueOf(position));
    }
}
