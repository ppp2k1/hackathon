package com.netapp.configuration;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import static org.springframework.boot.SpringApplication.*;

@SpringBootApplication
public class ConfigurationService {
    public static void main(String[] args) {
        run(ConfigurationService.class, args);
    }
}
