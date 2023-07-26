package personal.cafe.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CoffeeInputFormatValidatorTest {

    @DisplayName("올바른 커피 이름이 입력되면 true를 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "AMERICANO",
            "CAFFE_LATTE"
    })
    void isCoffeeNameValidIsTrue(String coffeeName) {

        // given
        CoffeeInputFormatValidator coffeeInputFormatValidator = new CoffeeInputFormatValidator();

        // when-then
        assertThat(coffeeInputFormatValidator.isCoffeeNameValid(coffeeName)).isTrue();

    }

    @DisplayName("올바른 커피 이름이 입력되지 않으면 false를 반환하고, 올바른 커피 이름의 리스트를 저장한다.")
    @ParameterizedTest
    @CsvSource({
            ",",
            "아메리카노",
            "카페라떼"
    })
    void isCoffeeNameValidIsFalse(String coffeeName) {

        // given
        CoffeeInputFormatValidator coffeeInputFormatValidator = new CoffeeInputFormatValidator();
        List<String> expectedOptions = Arrays.asList("AMERICANO", "CAFFE_LATTE");

        // when-then
        assertThat(coffeeInputFormatValidator.isCoffeeNameValid(coffeeName)).isFalse();

        assertThat(coffeeInputFormatValidator.getCoffeeOptionList()).isEqualTo(expectedOptions);

    }

    @DisplayName("올바른 커피 크기가 입력되면 true를 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "TALL",
            "GRANDE",
            "VENTI"
    })
    void isCoffeeSizeValidIsTrue(String coffeeSize) {

        // given
        CoffeeInputFormatValidator coffeeInputFormatValidator = new CoffeeInputFormatValidator();

        // when-then
        assertThat(coffeeInputFormatValidator.isCoffeeSizeValid(coffeeSize)).isTrue();

    }

    @DisplayName("올바른 커피 크기가 입력되지 않으면 false를 반환하고, 올바른 커피 크기의 리스트를 저장한다.")
    @ParameterizedTest
    @CsvSource({
            ",",
            "그란데",
            "벤티"
    })
    void isCoffeeSizeValidIsFalse(String coffeeSize) {

        // given
        CoffeeInputFormatValidator coffeeInputFormatValidator = new CoffeeInputFormatValidator();
        List<String> expectedOptions = Arrays.asList("TALL", "GRANDE", "VENTI");

        // when-then
        assertThat(coffeeInputFormatValidator.isCoffeeSizeValid(coffeeSize)).isFalse();

        assertThat(coffeeInputFormatValidator.getCoffeeOptionList()).isEqualTo(expectedOptions);

    }

    @DisplayName("올바른 얼음 옵션이 입력되면 true를 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "ICE",
            "HOT"
    })
    void isIcedValidIsTrue(String iceOption) {

        // given
        CoffeeInputFormatValidator coffeeInputFormatValidator = new CoffeeInputFormatValidator();

        // when-then
        assertThat(coffeeInputFormatValidator.isIcedValid(iceOption)).isTrue();

    }

    @DisplayName("올바른 얼음 옵션이 입력되지 않으면 false를 반환하고, 올바른 얼음 옵션의 리스트를 저장한다.")
    @ParameterizedTest
    @CsvSource({
            ",",
            "아이스",
            "핫"
    })
    void isIcedValidIsFalse(String iceOption) {

        // given
        CoffeeInputFormatValidator coffeeInputFormatValidator = new CoffeeInputFormatValidator();
        List<String> expectedOptions = Arrays.asList("ICE", "HOT");

        // when-then
        assertThat(coffeeInputFormatValidator.isIcedValid(iceOption)).isFalse();

        assertThat(coffeeInputFormatValidator.getCoffeeOptionList()).isEqualTo(expectedOptions);

    }

}
