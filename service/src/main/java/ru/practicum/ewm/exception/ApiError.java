package ru.practicum.ewm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity for error
 */
@Getter
@Setter
@AllArgsConstructor
public class ApiError {

    private String status;
    private String reason;
    private String message;
    private String timestamp;
    private String error;
}
