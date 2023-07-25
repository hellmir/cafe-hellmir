package personal.cafe.validation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CoffeeInputFormatValidatorTest {

    @ParameterizedTest
    @CsvSource({
            "AMERICANO",
            "CAFFE_LATTE"
    })
    void isCoffeeNameValidIsTrue(String coffeeName) {

        CoffeeInputFormatValidator coffeeInputFormatValidator = new CoffeeInputFormatValidator();

        assertThat(coffeeInputFormatValidator.isCoffeeNameValid(coffeeName)).isTrue();

    }

    @ParameterizedTest
    @CsvSource({
            ",",
            "아메리카노",
            "카페라떼"
    })
    void isCoffeeNameValidIsFalse(String coffeeName) {

        CoffeeInputFormatValidator coffeeInputFormatValidator = new CoffeeInputFormatValidator();

        assertThat(coffeeInputFormatValidator.isCoffeeNameValid(coffeeName)).isFalse();

        List<String> expectedOptions = Arrays.asList("AMERICANO", "CAFFE_LATTE");

        assertThat(coffeeInputFormatValidator.getCoffeeOptionList()).isEqualTo(expectedOptions);

    }

    @ParameterizedTest
    @CsvSource({
            "TALL",
            "GRANDE",
            "VENTI"
    })
    void isCoffeeSizeValidIsTrue(String coffeeSize) {

        CoffeeInputFormatValidator coffeeInputFormatValidator = new CoffeeInputFormatValidator();

        assertThat(coffeeInputFormatValidator.isCoffeeSizeValid(coffeeSize)).isTrue();

    }

    @ParameterizedTest
    @CsvSource({
            ",",
            "그란데",
            "벤티"
    })
    void isCoffeeSizeValidIsFalse(String coffeeSize) {

        CoffeeInputFormatValidator coffeeInputFormatValidator = new CoffeeInputFormatValidator();

        assertThat(coffeeInputFormatValidator.isCoffeeSizeValid(coffeeSize)).isFalse();

        List<String> expectedOptions = Arrays.asList("TALL", "GRANDE", "VENTI");

        assertThat(coffeeInputFormatValidator.getCoffeeOptionList()).isEqualTo(expectedOptions);

    }

    @ParameterizedTest
    @CsvSource({
            "ICE",
            "HOT"
    })
    void isIcedValidIsTrue(String iceOption) {

        CoffeeInputFormatValidator coffeeInputFormatValidator = new CoffeeInputFormatValidator();

        assertThat(coffeeInputFormatValidator.isIcedValid(iceOption)).isTrue();

    }

    @ParameterizedTest
    @CsvSource({
            ",",
            "아이스",
            "핫"
    })
    void isIcedValidIsFalse(String iceOption) {

        CoffeeInputFormatValidator coffeeInputFormatValidator = new CoffeeInputFormatValidator();

        assertThat(coffeeInputFormatValidator.isIcedValid(iceOption)).isFalse();

        List<String> expectedOptions = Arrays.asList("ICE", "HOT");

        assertThat(coffeeInputFormatValidator.getCoffeeOptionList()).isEqualTo(expectedOptions);

    }

}
