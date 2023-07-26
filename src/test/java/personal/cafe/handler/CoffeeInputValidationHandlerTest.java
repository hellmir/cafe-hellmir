package personal.cafe.handler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import personal.cafe.validation.CoffeeInputFormatValidator;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CoffeeInputValidationHandlerTest {

    @DisplayName("올바른 커피 형식이 입력되면 예외가 발생하지 않는다.")
    @ParameterizedTest
    @CsvSource({
            "AMERICANO, TALL, ICE",
            "CAFFE_LATTE, GRANDE, HOT",
            "AMERICANO, VENTI, ICE"
    })
    void validateCoffeeInputFormatWithValidCoffeeOptions(String coffeeName, String coffeeSize, String iceOption) {

        // given
        CoffeeInputValidationHandler coffeeInputValidationHandler = new CoffeeInputValidationHandler();

        // when-then
        assertDoesNotThrow(() -> coffeeInputValidationHandler.validateCoffeeInputFormat(
                coffeeName, coffeeSize, iceOption));

    }

    @DisplayName("올바른 커피 이름이 입력되지 않으면 IllegalArgumentException이 발생한다.")
    @ParameterizedTest
    @CsvSource({
            ", TALL, ICE",
            "아메리카노, GRANDE, HOT",
            "LATTE, VENTI, ICE"
    })
    void validateCoffeeInputFormatWithInvalidCoffeeName(String coffeeName, String coffeeSize, String iceOption) {

        // given
        CoffeeInputValidationHandler coffeeInputValidationHandler = new CoffeeInputValidationHandler();

        CoffeeInputFormatValidator coffeeInputFormatValidator = mock(CoffeeInputFormatValidator.class);

        List<String> expectedOptions = Arrays.asList("AMERICANO", "CAFFE_LATTE");

        when(coffeeInputFormatValidator.getCoffeeOptionList()).thenReturn(expectedOptions);

        // when-then
        assertThatThrownBy(() -> coffeeInputValidationHandler.validateCoffeeInputFormat
                (coffeeName, coffeeSize, iceOption))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 커피 이름입니다. 현재 주문 가능한 커피 이름 목록: "
                        + coffeeInputFormatValidator.getCoffeeOptionList());

    }

    @DisplayName("올바른 커피 크기가 입력되지 않으면 IllegalArgumentException이 발생한다.")
    @ParameterizedTest
    @CsvSource({
            "AMERICANO, , ICE",
            "CAFFE_LATTE, 그란데, HOT",
            "AMERICANO, BIG, ICE"
    })
    void validateCoffeeInputFormatWithInvalidCoffeeSize(String coffeeName, String coffeeSize, String iceOption) {

        // given
        CoffeeInputValidationHandler coffeeInputValidationHandler = new CoffeeInputValidationHandler();

        CoffeeInputFormatValidator coffeeInputFormatValidator = mock(CoffeeInputFormatValidator.class);

        List<String> expectedOptions = Arrays.asList("TALL", "GRANDE", "VENTI");

        when(coffeeInputFormatValidator.getCoffeeOptionList()).thenReturn(expectedOptions);

        // when-then
        assertThatThrownBy(() -> coffeeInputValidationHandler.validateCoffeeInputFormat
                (coffeeName, coffeeSize, iceOption))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 커피 사이즈입니다. 현재 주문 가능한 커피 사이즈 목록: "
                        + coffeeInputFormatValidator.getCoffeeOptionList());

    }

    @DisplayName("올바른 얼음 옵션이 입력되지 않으면 IllegalArgumentException이 발생한다.")
    @ParameterizedTest
    @CsvSource({
            "AMERICANO, TALL, 참",
            "CAFFE_LATTE, GRANDE, 거짓",
            "AMERICANO, VENTI,"
    })
    void validateCoffeeInputFormatWithInvalidIceOption(String coffeeName, String coffeeSize, String iceOption) {

        // given
        CoffeeInputValidationHandler coffeeInputValidationHandler = new CoffeeInputValidationHandler();

        CoffeeInputFormatValidator coffeeInputFormatValidator = mock(CoffeeInputFormatValidator.class);

        List<String> expectedOptions = Arrays.asList("ICE", "HOT");

        when(coffeeInputFormatValidator.getCoffeeOptionList()).thenReturn(expectedOptions);

        // when-then
        assertThatThrownBy(() -> coffeeInputValidationHandler.validateCoffeeInputFormat
                (coffeeName, coffeeSize, iceOption))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 얼음 옵션입니다. 현재 주문 가능한 얼음 옵션 목록: "
                        + coffeeInputFormatValidator.getCoffeeOptionList());

    }

}
