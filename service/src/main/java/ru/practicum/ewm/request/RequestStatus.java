package ru.practicum.ewm.request;

/**
 * Enum статус запроса
 */
public enum RequestStatus {

    /**
     * запрос не обработан
     */
    PENDING,

    /**
     * запрос подтвержден
     */
    CONFIRMED,

    /**
     * запрос отклонен
     */
    REJECTED,

    /**
     * запрос отменен
     */
    CANCELED
}
