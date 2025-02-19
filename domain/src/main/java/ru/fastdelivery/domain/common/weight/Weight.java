package ru.fastdelivery.domain.common.weight;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * Класс, представляющий вес в граммах.
 */
public record Weight(BigInteger weightGrams) implements Comparable<Weight> {

    /**
     * Конструктор класса Weight.
     *
     * @param weightGrams вес в граммах.
     * @throws IllegalArgumentException если вес меньше нуля.
     */
    public Weight {
        if (isLessThanZero(weightGrams)) {
            throw new IllegalArgumentException("Вес не может быть меньше нуля!");
        }
    }

    /**
     * Проверяет, меньше ли заданное значение нуля.
     *
     * @param price значение для проверки.
     * @return true, если значение меньше нуля, иначе false.
     */
    private static boolean isLessThanZero(BigInteger price) {
        return BigInteger.ZERO.compareTo(price) > 0;
    }

    /**
     * Возвращает объект Weight с нулевым весом.
     *
     * @return объект Weight с нулевым весом.
     */
    public static Weight zero() {
        return new Weight(BigInteger.ZERO);
    }

    /**
     * Возвращает вес в килограммах.
     *
     * @return вес в килограммах.
     */
    public BigDecimal kilograms() {
        return new BigDecimal(weightGrams)
                .divide(BigDecimal.valueOf(1000), 100, RoundingMode.HALF_UP);
    }

    /**
     * Добавляет указанный вес к текущему.
     *
     * @param additionalWeight вес для добавления.
     * @return новый объект Weight с суммарным весом.
     */
    public Weight add(Weight additionalWeight) {
        return new Weight(this.weightGrams.add(additionalWeight.weightGrams));
    }

    /**
     * Сравнивает текущий объект с другим объектом.
     *
     * @param o объект для сравнения.
     * @return true, если объекты равны, иначе false.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Weight weight = (Weight) o;
        return weightGrams.compareTo(weight.weightGrams) == 0;
    }

    /**
     * Сравнивает текущий объект с другим объектом.
     *
     * @param w объект для сравнения.
     * @return отрицательное число, ноль или положительное число в зависимости от того, меньше, равен или больше текущий объект.
     */
    @Override
    public int compareTo(Weight w) {
        return w.weightGrams().compareTo(weightGrams());
    }

    /**
     * Проверяет, больше ли текущий вес, чем указанный.
     *
     * @param w вес для сравнения.
     * @return true, если текущий вес больше указанного, иначе false.
     */
    public boolean greaterThan(Weight w) {
        return weightGrams().compareTo(w.weightGrams()) > 0;
    }
}
