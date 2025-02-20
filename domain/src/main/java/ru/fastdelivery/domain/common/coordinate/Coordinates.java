package ru.fastdelivery.domain.common.coordinate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


/**
 * Координаты точки (широта и долгота)
 */
public record Coordinates(
        /**
         * Широта
         */
        @Schema(description = "Широта", example = "55.027532")
        @org.jetbrains.annotations.NotNull @Min(45) @Max(65)
        Double latitude,

        /**
         * Долгота
         */
        @Schema(description = "Долгота", example = "73.398660")
        @NotNull @Min(30) @Max(96)
        Double longitude
) {
}
