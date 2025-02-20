package ru.fastdelivery.presentation.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import ru.fastdelivery.domain.common.coordinate.Coordinates;

import java.util.List;

/**
 * Данные для расчета стоимости доставки.
 */
@Schema(description = "Данные для расчета стоимости доставки")
public record CalculatePackagesRequest(
        /**
         * Список упаковок отправления.
         */
        @Schema(description = "Запрос на расчёт стоимости доставки", example = "[\n" +
                "{\n" +
                "\"weight\": 4564, \"length\": 345, \"width\": 589,\n" +
                "\"height\": 234\n" +
                "}\n" +
                "],\n" +
                "\"currencyCode\": \"RUB\",\n" +
                "\"destination\": {\n" +
                "\"latitude\"\n" +
                "\"longitude\"\n" +
                ": 73.398660, : 55.027532,\n" +
                "},\n" +
                "\"departure\"\n" +
                ":\n" +
                "{\n" +
                "\"latitude\n" +
                "\"\n" +
                ": 55.446008,\n" +
                "\"longitud\n" +
                "e\"\n" +
                ": 65.339151,\n" +
                "}")
        @NotNull @NotEmpty
        List<CargoPackage> packages,

        /**
         * Трехбуквенный код валюты.
         */
        @Schema(description = "Трехбуквенный код валюты", example = "RUB")
        @NotNull
        String currencyCode,

        /**
         * Координаты пункта отправления.
         */
        @Schema(description = "Координаты пункта отправления")
        @NotNull @Valid
        Coordinates departure,

        /**
         * Координаты пункта назначения.
         */
        @Schema(description = "Координаты пункта назначения")
        @NotNull @Valid
        Coordinates destination
) {
}
