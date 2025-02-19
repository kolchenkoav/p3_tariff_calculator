package ru.fastdelivery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.fastdelivery.domain.common.currency.CurrencyFactory;
import ru.fastdelivery.domain.common.currency.CurrencyPropertiesProvider;
import ru.fastdelivery.usecase.TariffCalculateUseCase;
import ru.fastdelivery.usecase.WeightPriceProvider;

/**
 * Конфигурационный класс для создания бинов.
 */
@Configuration
public class Beans {

    /**
     * Создает бин CurrencyFactory.
     *
     * @param currencyProperties провайдер свойств валюты
     * @return экземпляр CurrencyFactory
     */
    @Bean
    public CurrencyFactory currencyFactory(CurrencyPropertiesProvider currencyProperties) {
        return new CurrencyFactory(currencyProperties);
    }

    /**
     * Создает бин TariffCalculateUseCase.
     *
     * @param weightPriceProvider провайдер веса и цены
     * @return экземпляр TariffCalculateUseCase
     */
    @Bean
    public TariffCalculateUseCase tariffCalculateUseCase(WeightPriceProvider weightPriceProvider) {
        return new TariffCalculateUseCase(weightPriceProvider);
    }
}
