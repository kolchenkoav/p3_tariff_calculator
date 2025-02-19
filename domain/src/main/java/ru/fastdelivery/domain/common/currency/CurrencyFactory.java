package ru.fastdelivery.domain.common.currency;

import lombok.RequiredArgsConstructor;

/**
 * Фабрика для создания объектов валюты.
 */
@RequiredArgsConstructor
public class CurrencyFactory {

    private final CurrencyPropertiesProvider propertiesProvider;

    /**
     * Создает объект валюты по указанному коду.
     *
     * @param code код валюты
     * @return объект валюты
     * @throws IllegalArgumentException если код валюты не доступен
     */
    public Currency create(String code) {
        if (code == null || !propertiesProvider.isAvailable(code)) {
            throw new IllegalArgumentException("Currency code contains not available value");
        }
        return new Currency(code);
    }
}
