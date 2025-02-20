package ru.fastdelivery.util;

/**
 * Класс для вычисления расстояния между двумя точками на поверхности Земли.
 */
public class GeoDistanceCalculator {
    private static final double EARTH_RADIUS_KM = 6_372_795.0;

    /**
     * Метод для вычисления расстояния между двумя точками на поверхности Земли.
     *
     * @param lat1 Широта первой точки в градусах.
     * @param lon1 Долгота первой точки в градусах.
     * @param lat2 Широта второй точки в градусах.
     * @param lon2 Долгота второй точки в градусах.
     * @return Расстояние между двумя точками в километрах.
     * @see <a href="https://gis-lab.info/qa/great-circles.html">Great Circle Distance Calculation</a>
     */
    public static double calculateDistanceKm(double lat1, double lon1, double lat2, double lon2) {

        // Преобразование широты и долготы из градусов в радианы
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Формула Хаверсина
        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (EARTH_RADIUS_KM * c)/1000;
    }
}
