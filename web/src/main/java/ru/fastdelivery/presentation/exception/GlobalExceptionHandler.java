package ru.fastdelivery.presentation.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Глобальный обработчик исключений для контроллеров.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Обрабатывает исключение IllegalArgumentException.
     *
     * @param e исключение IllegalArgumentException
     * @return ResponseEntity с объектом ApiError и соответствующим HTTP статусом
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgument(IllegalArgumentException e) {
        ApiError apiError = ApiError.badRequest(e.getMessage());
        return new ResponseEntity<>(apiError, apiError.httpStatus());
    }

    /**
     * Обрабатывает исключение ConstraintViolationException.
     *
     * @param e исключение ConstraintViolationException
     * @return ResponseEntity с объектом ApiError и соответствующим HTTP статусом
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleValidationException(ConstraintViolationException e) {
        ApiError apiError = ApiError.badRequest("Ошибка валидации: " + e.getMessage());
        return new ResponseEntity<>(apiError, apiError.httpStatus());
    }
}
