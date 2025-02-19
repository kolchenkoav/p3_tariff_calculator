package ru.fastdelivery.usecase;

import lombok.RequiredArgsConstructor;
import ru.fastdelivery.domain.common.price.Price;
import ru.fastdelivery.domain.delivery.shipment.Shipment;

import javax.inject.Named;

@Named
@RequiredArgsConstructor
public class TariffCalculateUseCase {
    private final WeightPriceProvider weightPriceProvider;

    public Price calc(Shipment shipment) {
        var weightAllPackagesKg = shipment.weightAllPackages().kilograms();
        var minimalPrice = weightPriceProvider.minimalPrice();
        var costByWeight = weightPriceProvider.costPerKg().multiply(weightAllPackagesKg);

        var volumeAllPackagesM3 = shipment.volumeAllPackages();
        var costByVolume = weightPriceProvider.costPerM3().multiply(volumeAllPackagesM3);

        return costByWeight.max(costByVolume).max(minimalPrice);
    }

    public Price minimalPrice() {
        return weightPriceProvider.minimalPrice();
    }
}
