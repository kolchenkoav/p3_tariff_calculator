package ru.fastdelivery.presentation.api.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.fastdelivery.domain.common.coordinate.Coordinates;
import ru.fastdelivery.presentation.api.config.LocationProperties;
import ru.fastdelivery.presentation.api.validation.CoordinatesValidator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class CalculatePackagesRequestTest {

    @Mock(strictness = Mock.Strictness.LENIENT)
    private LocationProperties locationProperties;

    @InjectMocks
    private CoordinatesValidator validator;

    @BeforeEach
    void setUp() {
        LocationProperties.Latitude latitude = new LocationProperties.Latitude();
        latitude.setMin(45.0);
        latitude.setMax(65.0);

        LocationProperties.Longitude longitude = new LocationProperties.Longitude();
        longitude.setMin(30.0);
        longitude.setMax(96.0);

        lenient().when(locationProperties.getLatitude()).thenReturn(latitude);
        lenient().when(locationProperties.getLongitude()).thenReturn(longitude);
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
