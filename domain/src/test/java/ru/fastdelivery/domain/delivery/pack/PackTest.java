package ru.fastdelivery.domain.delivery.pack;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import ru.fastdelivery.domain.common.weight.Weight;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RequiredArgsConstructor
class PackTest {

    @Test
    @DisplayName("When weight is more than max weight then throw exception")
    void whenWeightMoreThanMaxWeight_thenThrowException() {
        var weight = new Weight(BigInteger.valueOf(150_001));
        assertThatThrownBy(() -> new Pack(weight))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("When weight is less than max weight then object created")
    void whenWeightLessThanMaxWeight_thenObjectCreated() {
        var actual = new Pack(new Weight(BigInteger.valueOf(1_000)));
        assertThat(actual.weight()).isEqualTo(new Weight(BigInteger.valueOf(1_000)));
    }

    @Test
    @DisplayName("When value is multiple of 50 then return same value")
    void whenValueIsMultipleOf50_thenReturnSameValue() {
        Pack pack = new Pack(new Weight(BigInteger.valueOf(2500))); // Assuming Pack constructor doesn't require Weight
        assertThat(pack.roundToNearest50(100)).isEqualTo(100);
        assertThat(pack.roundToNearest50(150)).isEqualTo(150);
    }

    @ParameterizedTest(name = "Value {0} rounds to {1}")
    @CsvSource({
            "24, 0",
            "25, 50",
            "49, 50",
            "51, 50",
            "74, 50",
            "75, 100",
            "99, 100",
            "101, 100",
            "124, 100",
            "125, 150"
    })
    @DisplayName("When value is not multiple of 50 then round to nearest 50")
    void whenValueIsNotMultipleOf50_thenRoundToNearest50(int value, int expected) {
        Pack pack = new Pack(new Weight(BigInteger.valueOf(2500))); // Assuming Pack constructor doesn't require Weight
        assertThat(pack.roundToNearest50(value)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Value is exactly 25 away from the next multiple of 50")
    void whenValueIsExactly25Away_thenRoundUp() {
        Weight weight = new Weight(BigInteger.valueOf(2500));
        Pack pack = new Pack(weight);
        assertThat(pack.roundToNearest50(75)).isEqualTo(100);
        assertThat(pack.roundToNearest50(125)).isEqualTo(150);
    }
}