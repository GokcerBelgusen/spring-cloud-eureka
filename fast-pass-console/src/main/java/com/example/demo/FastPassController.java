package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by nasir on 30/9/17.
 */
@RestController
public class FastPassController {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(path="/customerdetails", params={"fastpassid"})
    public FastPassCustomer getFastPassCustomerDetails(@RequestParam String fastpassid) {

        FastPassCustomer c = restTemplate.getForObject("http://fast-pass-service/fastpass?fastpassid=" + fastpassid, FastPassCustomer.class);
        System.out.println("retrieved customer details");
        return c;
    }
}
