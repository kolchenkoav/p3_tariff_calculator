package ru.fastdelivery.presentation.api.response;

import ru.fastdelivery.domain.common.price.Price;

import java.math.BigDecimal;

/**
 * Ответ на запрос расчета пакетов.
 * @param totalPrice Общая стоимость.
 * @param minimalPrice Минимальная стоимость.
 * @param currencyCode Код валюты.
 */
public record CalculatePackagesResponse(
        BigDecimal totalPrice,
        BigDecimal minimalPrice,
        String currencyCode
) {
    /**
     * Конструктор для создания объекта CalculatePackagesResponse.
     * @param totalPrice Общая стоимость.
     * @param minimalPrice Минимальная стоимость.
     * @throws IllegalArgumentException если коды валют не совпадают.
     */
    public CalculatePackagesResponse(Price totalPrice, Price minimalPrice) {
        this(totalPrice.amount(), minimalPrice.amount(), totalPrice.currency().getCode());

        if (currencyIsNotEqual(totalPrice, minimalPrice)) {
            throw new IllegalArgumentException("Коды валют должны совпадать");
        }
    }

    /**
     * Проверяет, совпадают ли коды валют.
     * @param priceLeft Первая цена.
     * @param priceRight Вторая цена.
     * @return true, если коды валют не совпадают, иначе false.
     */
    private static boolean currencyIsNotEqual(Price priceLeft, Price priceRight) {
        return !priceLeft.currency().equals(priceRight.currency());
    }
}
