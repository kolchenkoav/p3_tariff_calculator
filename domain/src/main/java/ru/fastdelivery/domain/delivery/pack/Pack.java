package ru.fastdelivery.domain.delivery.pack;

import ru.fastdelivery.domain.common.weight.Weight;

import java.math.BigInteger;

/**
 * Упаковка груза
 *
 * @param weight вес товаров в упаковке
 */
public record Pack(Weight weight) {

    private static final Weight MAXWEIGHT = new Weight(BigInteger.valueOf(150_000));
    private static final int ROUND_TO = 50;

    public Pack {
        if (weight.greaterThan(MAXWEIGHT)) {
            throw new IllegalArgumentException("Package can't be more than " + MAXWEIGHT);
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
}
