package ru.fastdelivery.domain.common.currency;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * Класс, представляющий валюту.
 */
@Value
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Currency {
    /**
     * Код валюты.
     */
    String code;
}
