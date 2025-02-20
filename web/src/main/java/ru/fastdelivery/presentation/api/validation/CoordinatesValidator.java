package ru.fastdelivery.presentation.api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import ru.fastdelivery.domain.common.coordinate.Coordinates;
import ru.fastdelivery.presentation.api.config.LocationProperties;


/**
 * Валидатор координат, который проверяет, находятся ли координаты в допустимом диапазоне.
 */
@RequiredArgsConstructor
public class CoordinatesValidator implements ConstraintValidator<ValidCoordinatesRange, Coordinates> {

    private final LocationProperties locationProperties;

    /**
     * Проверяет, находятся ли координаты в допустимом диапазоне.
     *
     * @param value координаты для проверки
     * @param context контекст валидации
     * @return true, если координаты в допустимом диапазоне, иначе false
     */
    @Override
    public boolean isValid(Coordinates value, ConstraintValidatorContext context) {
        if (value == null) return false;

        double lat = value.latitude();
        double lon = value.longitude();

        return lat >= locationProperties.getLatitude().getMin() &&
                lat <= locationProperties.getLatitude().getMax() &&
                lon >= locationProperties.getLongitude().getMin() &&
                lon <= locationProperties.getLongitude().getMax();
    }
}
