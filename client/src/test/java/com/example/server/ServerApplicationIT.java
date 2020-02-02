package com.example.server;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Log4j2
class ServerApplicationIT {

    @Test
    void embedded_java11_http_client_tests() throws IOException, InterruptedException {
        var client = HttpClient.newBuilder()
                               .version(HttpClient.Version.HTTP_1_1)
                               .build();
        var request = HttpRequest.newBuilder()
                                 .uri(URI.create("http://127.0.0.1:8080/ololo"))
                                 .GET()
                                 .build();
        log.info("req: {}", request);
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        log.info("resp: {}", response.body());
    }
}
