package com.example.server;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Map;
import java.util.function.Function;

@Log4j2
@RestController
@SpringBootApplication
public class ServerApplication {

    @GetMapping("/**")
    Map<String, String> fallback(HttpServletRequest request) {
        var self = request.getRequestURL().toString();
        log.info("handling {} {} request", request.getMethod(), self);
        var uri = URI.create(self);
        var baseUrl = String.format("%s://%s", uri.getScheme(), uri.getAuthority());
        Function<String, String> url = path -> String.format("%s%s", baseUrl, path);
        return Map.of("_self", self);
    }

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
