package ru.fastdelivery.properties.provider;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.fastdelivery.domain.common.currency.CurrencyPropertiesProvider;

import java.util.List;

/**
 * Класс для хранения и предоставления свойств валют.
 */
@Configuration
@ConfigurationProperties("currency")
@Setter
public class CurrencyProperties implements CurrencyPropertiesProvider {

    /**
     * Список доступных валют.
     */
    private List<String> available;

    /**
     * Проверяет, доступна ли валюта с указанным кодом.
     *
     * @param code код валюты
     * @return true, если валюта доступна, иначе false
     */
    @Override
    public boolean isAvailable(String code) {
        return available.contains(code);
    }
}
