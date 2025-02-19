package ru.fastdelivery.domain.delivery.pack;

import ru.fastdelivery.domain.common.weight.Weight;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Упаковка груза
 *
 * @param weight вес товаров в упаковке
 */
public record Pack(Weight weight, int length, int width, int height) {

    private static final Weight MAXWEIGHT = new Weight(BigInteger.valueOf(150_000));
    private static final int ROUND_TO = 50;
    private static final int MAX_DIMENSION = 1500;

    public Pack {
        if (weight.greaterThan(MAXWEIGHT)) {
            throw new IllegalArgumentException("Package can't be more than " + MAXWEIGHT);
        }
        if (length <= 0 || width <= 0 || height <= 0 ||
                length > MAX_DIMENSION || width > MAX_DIMENSION || height > MAX_DIMENSION) {
            throw new IllegalArgumentException("Invalid package dimensions");
        }
    }

    /**
     * Округляет значение до ближайшего числа, кратного 50.
     *
     * @param value значение для округления
     * @return округленное значение
     */
    int roundToNearest50(int value) {
        int remainder = value % ROUND_TO;
        if (remainder < 25) {
            return value - remainder;
        } else {
            return value + (ROUND_TO - remainder);
        }
    }

    public BigDecimal volume() {
        int roundedLength = roundToNearest50(length);
        int roundedWidth = roundToNearest50(width);
        int roundedHeight = roundToNearest50(height);

        return BigDecimal.valueOf(roundedLength)
                .multiply(BigDecimal.valueOf(roundedWidth))
                .multiply(BigDecimal.valueOf(roundedHeight))
                .divide(BigDecimal.valueOf(1_000_000_000), 4, BigDecimal.ROUND_HALF_UP);
    }
}
