package ru.fastdelivery.domain.common.price;

import ru.fastdelivery.domain.common.currency.Currency;

import java.math.BigDecimal;

/**
 * Класс, представляющий цену.
 */
public record Price(
        BigDecimal amount,
        Currency currency) {

    /**
     * Конструктор, который проверяет, что сумма цены не меньше нуля.
     *
     * @param amount сумма цены
     * @param currency валюта цены
     * @throws IllegalArgumentException если сумма цены меньше нуля
     */
    public Price {
        if (isLessThanZero(amount)) {
            throw new IllegalArgumentException("Сумма цены не может быть меньше нуля!");
        }
    }

    /**
     * Проверяет, что сумма цены меньше нуля.
     *
     * @param price сумма цены
     * @return true, если сумма цены меньше нуля, иначе false
     */
    private static boolean isLessThanZero(BigDecimal price) {
        return BigDecimal.ZERO.compareTo(price) > 0;
    }

    /**
     * Умножает текущую цену на заданную сумму.
     *
     * @param amount сумма для умножения
     * @return новая цена, равная произведению текущей цены и заданной суммы
     */
    public Price multiply(BigDecimal amount) {
        return new Price(this.amount.multiply(amount), this.currency);
    }

    /**
     * Возвращает максимальную цену из двух.
     *
     * @param price другая цена для сравнения
     * @return максимальная из двух цен
     * @throws IllegalArgumentException если валюты цен различаются
     */
    public Price max(Price price) {
        if (!currency.equals(price.currency)) {
            throw new IllegalArgumentException("Нельзя сравнивать цены в разных валютах!");
        }
        return new Price(this.amount.max(price.amount), this.currency);
    }
}
