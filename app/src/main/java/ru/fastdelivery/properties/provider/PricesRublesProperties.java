package ru.fastdelivery.properties.provider;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import ru.fastdelivery.domain.common.currency.CurrencyFactory;
import ru.fastdelivery.domain.common.price.Price;
import ru.fastdelivery.usecase.WeightPriceProvider;

import java.math.BigDecimal;

/**
 * Класс для настройки цен в рублях.
 */
@ConfigurationProperties("cost.rub")
@Setter
public class PricesRublesProperties implements WeightPriceProvider {

    /**
     * Стоимость за килограмм.
     */
    private BigDecimal perKg;

    /**
     * Минимальная стоимость.
     */
    private BigDecimal minimal;

    /**
     * Стоимость за кубический метр.
     */
    private BigDecimal perM3;

    /**
     * Фабрика для создания валют.
     */
    @Autowired
    private CurrencyFactory currencyFactory;

    /**
     * Возвращает стоимость за килограмм.
     *
     * @return стоимость за килограмм
     */
    @Override
    public Price costPerKg() {
        return new Price(perKg, currencyFactory.create("RUB"));
    }

    /**
     * Возвращает минимальную стоимость.
     *
     * @return минимальная стоимость
     */
    @Override
    public Price minimalPrice() {
        return new Price(minimal, currencyFactory.create("RUB"));
    }

    /**
     * Возвращает стоимость за кубический метр.
     *
     * @return стоимость за кубический метр
     */
    @Override
    public Price costPerM3() {
        return new Price(perM3, currencyFactory.create("RUB"));
    }
}
