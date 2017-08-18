package com.example.springsamples;

import com.example.springsamples.retry.SampleConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({SampleConfiguration.class})
public class SpringSamplesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSamplesApplication.class, args);
    }
}
