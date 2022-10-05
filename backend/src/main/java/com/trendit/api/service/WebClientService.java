package com.trendit.api.service;

import com.trendit.api.exception.WebClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebClientService {
    private final WebClient webClient;

    @Autowired
    public WebClientService() {
        this.webClient = WebClient.builder()
                .baseUrl("http://j7a301.p.ssafy.io:9090/internal")
                .build();
    }

    public Mono<String> addUserDictionary(String keyword) {
        Mono<String> response = webClient.post()
                .uri("/user-dictionary/{keyword}", keyword)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        Mono.error(new WebClientException("WebClient400"))
                )
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        Mono.error(new WebClientException("WebClient500"))
                )
                .bodyToMono(String.class);
        String responseStr = response.block();
        System.out.println(responseStr);
        return response;
    }

    public Mono<String> executeDataPipeline(String date) {
        Mono<String> response = webClient.get()
                .uri("/run/{date}", date)
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        Mono.error(new WebClientException("WebClient500"))
                )
                .bodyToMono(String.class);
        String responseStr = response.block();
        System.out.println(responseStr);
        return response;
    }

}
