package com.hnt.dental;

import com.hnt.dental.service.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.TimeZone;

@SpringBootApplication
@EnableAsync
@EnableTransactionManagement
@RequiredArgsConstructor
@Slf4j
public class Application implements CommandLineRunner {

    private final StorageService storageService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("Welcome to HTN Dental Clinic Application");
    }

    @Override
    public void run(String... args) throws Exception {
        storageService.init();
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+7"));
    }
}
