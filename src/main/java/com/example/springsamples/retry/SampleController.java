package com.example.springsamples.retry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SampleController {
    @Autowired
    private SampleService service;

    @GetMapping("/samples")
    public Quote samples() {
        log.info("----------------- /samples ------------------");
        return service.getQuote();
    }
}
