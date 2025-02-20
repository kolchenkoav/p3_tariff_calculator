package ru.fastdelivery.presentation.api.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Класс конфигурации для свойств местоположения.
 */
@Configuration
@ConfigurationProperties("location")
@Getter
@Setter
public class LocationProperties {
    private Latitude latitude;
    private Longitude longitude;

    /**
     * Класс для представления широты.
     */
    @Getter
    @Setter
    public static class Latitude {
        private double min;
        private double max;
    }

    /**
     * Класс для представления долготы.
     */
    @Getter
    @Setter
    public static class Longitude {
        private double min;
        private double max;
    }
}
