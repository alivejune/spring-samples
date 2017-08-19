package com.example.springsamples.retry;

public class UnExpectedException extends RuntimeException {
    public UnExpectedException(String msg) {
        super(msg);
    }
}
