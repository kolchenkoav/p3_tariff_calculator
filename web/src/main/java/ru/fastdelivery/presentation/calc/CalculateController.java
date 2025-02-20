package ru.fastdelivery.presentation.calc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fastdelivery.domain.common.currency.CurrencyFactory;
import ru.fastdelivery.domain.common.weight.Weight;
import ru.fastdelivery.domain.delivery.pack.Pack;
import ru.fastdelivery.domain.delivery.shipment.Shipment;
import ru.fastdelivery.presentation.api.request.CalculatePackagesRequest;
import ru.fastdelivery.presentation.api.response.CalculatePackagesResponse;
import ru.fastdelivery.usecase.TariffCalculateUseCase;

/**
 * Контроллер для расчета стоимости доставки.
 */
@RestController
@RequestMapping("/api/v1/calculate/")
@RequiredArgsConstructor
@Tag(name = "Расчеты стоимости доставки")
public class CalculateController {
    private final TariffCalculateUseCase tariffCalculateUseCase;
    private final CurrencyFactory currencyFactory;

    /**
     * Метод для расчета стоимости по упаковкам груза.
     *
     * @param request запрос с данными о упаковках груза
     * @return ответ с рассчитанной стоимостью и минимальной стоимостью
     */
    @PostMapping
    @Operation(summary = "Расчет стоимости доставки с учетом расстояния")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешная операция"),
        @ApiResponse(responseCode = "400", description = "Неверный ввод")
    })
    public CalculatePackagesResponse calculate(
            @Valid @RequestBody CalculatePackagesRequest request) {
        var packsWeights = request.packages().stream()
                .map(pkg -> new Pack(
                        new Weight(pkg.weight()),
                        pkg.length(),
                        pkg.width(),
                        pkg.height()
                ))
                .toList();

        var shipment = new Shipment(packsWeights, currencyFactory.create(request.currencyCode()));
        var calculatedPrice = tariffCalculateUseCase.calc(shipment, request.departure(), request.destination());
        var minimalPrice = tariffCalculateUseCase.minimalPrice();
        return new CalculatePackagesResponse(calculatedPrice, minimalPrice);
    }
}
