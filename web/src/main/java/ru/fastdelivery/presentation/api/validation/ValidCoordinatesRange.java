package ru.fastdelivery.presentation.api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для валидации координат, чтобы убедиться, что они находятся в допустимых пределах.
 */
@Constraint(validatedBy = CoordinatesValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCoordinatesRange {
    /**
     * Сообщение об ошибке, если координаты выходят за допустимые границы.
     */
    String message() default "Координаты выходят за допустимые границы";

    /**
     * Группы, к которым относится данная аннотация.
     */
    Class<?>[] groups() default {};

    /**
     * Полезная нагрузка, связанная с данной аннотацией.
     */
    Class<? extends Payload>[] payload() default {};
}
