package ru.practicum.ewm.client;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.practicum.ewm.client.dto.EndPointHitDto;
import ru.practicum.ewm.client.dto.ViewStatsDto;

import java.util.List;

/**
 * WebClient
 */
@Slf4j
@Service
@AllArgsConstructor
public class EventWebClientTest {

    private static final String HIT_URL_TEMPLATE = "/hit";
    private static final String STATS_URL_TEMPLATE = "/stats";

    private final WebClient webClient;

    public void postHitSync(EndPointHitDto endPointHitDto) {
        webClient
                .post()
                .uri(HIT_URL_TEMPLATE)
                .body(Mono.just(endPointHitDto), EndPointHitDto.class)
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(String.class);
                    } else if (response.statusCode().is4xxClientError()) {
                        return Mono.just("Error response");
                    } else {
                        return response.createException()
                                .flatMap(Mono::error);
                    }
                })
                .block();
    }

    public List<ViewStatsDto> getStatsSync(String start, String end, List<String> uris, Boolean unique) {

        ResponseEntity<List<ViewStatsDto>> response = webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(STATS_URL_TEMPLATE)
                        .queryParam("start", start)
                        .queryParam("end", end)
                        .queryParam("uris", uris.toArray())
                        .queryParam("unique", unique)
                        .build())
                .retrieve()
                .toEntityList(ViewStatsDto.class).block();
        assert response != null;
        return response.getBody();

    }
}
