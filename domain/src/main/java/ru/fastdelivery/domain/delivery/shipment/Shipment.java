package ru.fastdelivery.domain.delivery.shipment;

import ru.fastdelivery.domain.common.currency.Currency;
import ru.fastdelivery.domain.common.weight.Weight;
import ru.fastdelivery.domain.delivery.pack.Pack;

import java.math.BigDecimal;
import java.util.List;

/**
 * Запись, представляющая отправление.
 * @param packages список упаковок в отправлении
 * @param currency валюта отправления
 */
public record Shipment(
        List<Pack> packages,
        Currency currency
) {
    /**
     * Возвращает общий вес всех упаковок в отправлении.
     * @return общий вес всех упаковок
     */
    public Weight weightAllPackages() {
        return packages.stream()
                .map(Pack::weight)
                .reduce(Weight.zero(), Weight::add);
    }

    /**
     * Возвращает общий объем всех упаковок в отправлении.
     * @return общий объем всех упаковок
     */
    public BigDecimal volumeAllPackages() {
        return packages.stream()
                .map(Pack::volume)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
