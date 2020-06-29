package com.example.fizzbuzz.fragment.service.provider;

import com.example.fizzbuzz.fragment.service.FizzBuzzFragment;

public interface FragmentProvider {
    FizzBuzzFragment getFragment(int position);
}
