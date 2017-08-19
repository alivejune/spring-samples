package com.example.springsamples.retry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class SampleService {
    @Autowired
    private RestTemplate restTemplate;

    @Retryable(maxAttempts = 2, backoff = @Backoff(delay = 5000), include = UnExpectedException.class)
    public Quote getQuote() {
        log.info("----------------- getQuote ------------------");

        try {
            return restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        } catch (Exception e) {
            throw new UnExpectedException("retry exception!");
        }
    }

    @Recover
    public Quote recover(UnExpectedException e) {
        log.info("----------------- recover ------------------", e);
        return null;
    }
}
