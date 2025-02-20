package ru.fastdelivery.usecase;

import lombok.RequiredArgsConstructor;
import ru.fastdelivery.domain.common.coordinate.Coordinates;
import ru.fastdelivery.domain.common.price.Price;
import ru.fastdelivery.domain.delivery.shipment.Shipment;
import ru.fastdelivery.util.GeoDistanceCalculator;


import javax.inject.Named;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Класс для расчета тарифа доставки.
 */
@Named
@RequiredArgsConstructor
public class TariffCalculateUseCase {

    private final WeightPriceProvider weightPriceProvider;

    private static final BigDecimal MIN_DISTANCE_KM = new BigDecimal(450); // Минимальная тарифная дистанция

    /**
     * Метод для расчета стоимости доставки с учетом веса, объема и расстояния.
     *
     * @param shipment   объект доставки
     * @param departure  координаты пункта отправления
     * @param destination координаты пункта назначения
     * @return стоимость доставки
     */
    public Price calc(Shipment shipment, Coordinates departure, Coordinates destination) {
        // Расчёт расстояния
        double distanceKm = GeoDistanceCalculator.calculateDistanceKm(
                departure.latitude(), departure.longitude(),
                destination.latitude(), destination.longitude());

        // Округляем расстояние вверх до минимального шага 450 км
        BigDecimal distanceFactor = new BigDecimal(distanceKm)
                .divide(MIN_DISTANCE_KM, 0, RoundingMode.CEILING);

        // Базовая стоимость по весу и объему
        var weightAllPackagesKg = shipment.weightAllPackages().kilograms();
        var minimalPrice = weightPriceProvider.minimalPrice();
        var costByWeight = weightPriceProvider.costPerKg().multiply(weightAllPackagesKg);
        var volumeAllPackagesM3 = shipment.volumeAllPackages();
        var costByVolume = weightPriceProvider.costPerM3().multiply(volumeAllPackagesM3);

        // Стоимость доставки с учетом расстояния
        Price basePrice = costByWeight.max(costByVolume).max(minimalPrice);
        Price finalPrice = basePrice.multiply(distanceFactor);

        return finalPrice;
    }

    /**
     * Метод для получения минимальной стоимости доставки.
     *
     * @return минимальная стоимость доставки
     */
    public Price minimalPrice() {
        return weightPriceProvider.minimalPrice();
    }
}
