package ru.fastdelivery.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Класс для тестирования метода расчета географического расстояния.
 *
 * Проверочный набор данных взят из <a href="https://gis-lab.info/qa/great-circles.html">Great Circle Distance Calculation</a>
 */
public class GeoDistanceCalculatorTest {

    /**
     * Тест расчета расстояния между двумя точками.
     */
    @Test
    public void testCalculateDistance1() {
        double lat1 = 77.1539;
        double lon1 = -139.398;
        double lat2 = -77.1804;
        double lon2 = -139.55;

        double expectedDistance = 17166.02; // Ожидаемое расстояние в километрах
        double actualDistance = GeoDistanceCalculator.calculateDistanceKm(lat1, lon1, lat2, lon2);

        assertEquals(expectedDistance, actualDistance, 0.01); // Разрешаем небольшую дельту для сравнения с плавающей запятой
    }

    /**
     * Тест расчета расстояния между двумя точками.
     */
    @Test
    public void testCalculateDistance2() {
        double lat1 = 77.1539;
        double lon1 = 120.398;
        double lat2 = 77.1804;
        double lon2 = 129.55;

        double expectedDistance = 225.883; // Ожидаемое расстояние в километрах
        double actualDistance = GeoDistanceCalculator.calculateDistanceKm(lat1, lon1, lat2, lon2);

        assertEquals(expectedDistance, actualDistance, 0.01); // Разрешаем небольшую дельту для сравнения с плавающей запятой
    }

    /**
     * Тест расчета расстояния между двумя точками.
     */
    @Test
    public void testCalculateDistance3() {
        double lat1 = 77.1539;
        double lon1 = -120.398;
        double lat2 = 77.1804;
        double lon2 = 129.55;

        double expectedDistance = 2332.669; // Ожидаемое расстояние в километрах
        double actualDistance = GeoDistanceCalculator.calculateDistanceKm(lat1, lon1, lat2, lon2);

        assertEquals(expectedDistance, actualDistance, 0.01); // Разрешаем небольшую дельту для сравнения с плавающей запятой
    }

    /**
     * Тест расчета расстояния между одинаковыми точками.
     */
    @Test
    public void testCalculateDistanceSamePoint() {
        double lat1 = 52.2296756;
        double lon1 = 21.0122287;
        double lat2 = 52.2296756;
        double lon2 = 21.0122287;

        double expectedDistance = 0.0; // Расстояние должно быть нулевым
        double actualDistance = GeoDistanceCalculator.calculateDistanceKm(lat1, lon1, lat2, lon2);

        assertEquals(expectedDistance, actualDistance, 0.0001); // Разрешаем небольшую дельту для сравнения с плавающей запятой
    }
}