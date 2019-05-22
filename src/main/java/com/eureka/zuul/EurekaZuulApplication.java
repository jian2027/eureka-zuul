package com.eureka.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.eureka.zuul.filter.ValidateTokenFilter;
import com.netflix.zuul.ZuulFilter;

@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class EurekaZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaZuulApplication.class, args);
	}

	@Bean
    public ZuulFilter putFilter() {
        return new ValidateTokenFilter() ;
    }
}
