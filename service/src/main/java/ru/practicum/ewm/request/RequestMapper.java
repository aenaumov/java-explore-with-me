package ru.practicum.ewm.request;

import ru.practicum.ewm.request.model.Request;
import ru.practicum.ewm.request.model.dto.ParticipationRequestDto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для конвертации Request в DTO и обратно
 */
public final class RequestMapper {

    private RequestMapper() {
    }

    public static ParticipationRequestDto toParticipationRequestDto(Request request) {
        return new ParticipationRequestDto(
                request.getId(),
                request.getCreated(),
                request.getEvent().getId(),
                request.getRequester().getId(),
                request.getStatus()
        );
    }

    public static List<ParticipationRequestDto> toParticipationRequestDtoList(List<Request> requests) {
        return requests.stream()
                .map(RequestMapper::toParticipationRequestDto)
                .collect(Collectors.toList());
    }
}
