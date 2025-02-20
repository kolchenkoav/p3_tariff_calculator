package ru.fastdelivery.presentation.api.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ru.fastdelivery.presentation.api.config.LocationProperties;
import ru.fastdelivery.presentation.api.validation.CoordinatesValidator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class CalculatePackagesRequestTest {

    @MockBean
    private LocationProperties locationProperties;

    private CoordinatesValidator validator;

    @BeforeEach
    void setUp() {
        when(locationProperties.getLatitude().getMin()).thenReturn(45.0);
        when(locationProperties.getLatitude().getMax()).thenReturn(65.0);
        when(locationProperties.getLongitude().getMin()).thenReturn(30.0);
        when(locationProperties.getLongitude().getMax()).thenReturn(96.0);

        validator = new CoordinatesValidator(locationProperties);
    }

    @Test
    void whenCoordinatesValid_thenValidationPasses() {
        Coordinates validCoordinates = new Coordinates(55.0, 73.0);
        assertThat(validator.isValid(validCoordinates, null)).isTrue();
    }

    @Test
    void whenLatitudeOutOfRange_thenValidationFails() {
        Coordinates invalidCoordinates = new Coordinates(70.0, 73.0);
        assertThat(validator.isValid(invalidCoordinates, null)).isFalse();
    }

    @Test
    void whenLongitudeOutOfRange_thenValidationFails() {
        Coordinates invalidCoordinates = new Coordinates(55.0, 100.0);
        assertThat(validator.isValid(invalidCoordinates, null)).isFalse();
    }
}
