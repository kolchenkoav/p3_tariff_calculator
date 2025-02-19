package ru.fastdelivery.usecase;

import ru.fastdelivery.domain.common.price.Price;

/**
 * Интерфейс для предоставления стоимости на основе веса.
 */
public interface WeightPriceProvider {

    /**
     * Возвращает стоимость за килограмм.
     *
     * @return стоимость за килограмм
     */
    Price costPerKg();

    /**
     * Возвращает минимальную стоимость.
     *
     * @return минимальная стоимость
     */
    Price minimalPrice();

    /**
     * Возвращает стоимость за кубический метр.
     *
     * @return стоимость за кубический метр
     */
    Price costPerM3();
}
