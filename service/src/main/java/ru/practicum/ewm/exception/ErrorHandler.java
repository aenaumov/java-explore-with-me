package ru.practicum.ewm.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFoundException(final NotFoundException e) {
        log.info("404 message = {}, StackTrace = {}", e.getMessage(), e.getStackTrace());
        return new ApiError(
                "NOT_FOUND",
                "The required object was not found.",
                e.getMessage(),
                LocalDateTime.now().toString(),
                Arrays.toString(e.getStackTrace()));
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleArgumentNotValid(final MethodArgumentNotValidException e) {
        log.info("400 message = {}, StackTrace = {}", e.getMessage(), e.getStackTrace());
        return new ApiError("BAD_REQUEST",
                "For the requested operation the conditions are not met.",
                e.getMessage(),
                LocalDateTime.now().toString(),
                Arrays.toString(e.getStackTrace()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleIllegalArgumentException(final IllegalArgumentException e) {
        log.info("400 message = {}, StackTrace = {}", e.getMessage(), e.getStackTrace());
        return new ApiError("BAD_REQUEST",
                "For the requested operation the conditions are not met.",
                e.getMessage(),
                LocalDateTime.now().toString(),
                Arrays.toString(e.getStackTrace()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiError handleNotCorrectConditionException(final NotCorrectConditionException e) {
        log.info("403 message = {}, StackTrace = {}", e.getMessage(), e.getStackTrace());
        return new ApiError("FORBIDDEN",
                "For the requested operation the conditions are not met.",
                e.getMessage(),
                LocalDateTime.now().toString(),
                Arrays.toString(e.getStackTrace()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleDataIntegrityViolationException(final DataIntegrityViolationException e) {
        log.info("409 message = {}, StackTrace = {}", e.getMessage(), e.getStackTrace());
        return new ApiError("CONFLICT",
                "Integrity constraint has been violated",
                e.getMessage(),
                LocalDateTime.now().toString(),
                Arrays.toString(e.getStackTrace()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleException(final Throwable e) {
        log.info("500 message = {}, StackTrace = {}", e.getMessage(), e.getStackTrace());
        return new ApiError("INTERNAL_SERVER_ERROR",
                "Error occurred",
                e.getMessage(),
                LocalDateTime.now().toString(),
                Arrays.toString(e.getStackTrace()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleException(final Exception e) {
        log.info("400 message = {}, StackTrace = {}", e.getMessage(), e.getStackTrace());
        return new ApiError("BAD_REQUEST",
                "For the requested operation the conditions are not met.",
                e.getMessage(),
                LocalDateTime.now().toString(),
                Arrays.toString(e.getStackTrace()));
    }
}
