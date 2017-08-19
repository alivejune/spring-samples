package com.example.springsamples.retry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SampleServiceTest {
    @Autowired
    private SampleService sampleService;
    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void getQuoteWithRetry() {
        when(restTemplate.getForObject(any(String.class), any())).thenAnswer(invocationOnMock -> {
            throw new UnExpectedException("Sample message.");
        });
        sampleService.getQuote();

        verify(restTemplate, times(2)).getForObject(any(String.class), any());
    }

    @Configuration
    @EnableRetry
    static class AppTestConfig {
        @Bean
        SampleService sampleService() {
            return new SampleService();
        }

        @Bean
        RestTemplate restTemplate() {
            return mock(RestTemplate.class);
        }
    }
}