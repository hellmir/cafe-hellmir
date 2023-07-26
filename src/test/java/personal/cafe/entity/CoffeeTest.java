package personal.cafe.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import personal.cafe.constant.CoffeeName;
import personal.cafe.constant.CoffeeSize;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CoffeeTest {

    @DisplayName("제조된 커피의 종류는 입력된 조건과 같다.")
    @ParameterizedTest
    @CsvSource({
            "AMERICANO, TALL, true",
            "CAFFE_LATTE, GRANDE, false",
            "AMERICANO, VENTI, true"
    })
    void createCoffeeWithVariousParameters(CoffeeName coffeeName, CoffeeSize coffeeSize, boolean isIced) {

        // given from @CsvSource

        // when
        Coffee coffee = Coffee.createCoffee(coffeeName, coffeeSize, isIced);

        // then
        assertThat(coffee.getCoffeeName()).isEqualTo(coffeeName);

        assertThat(coffee.getCoffeeSize()).isEqualTo(coffeeSize);

        assertThat(coffee.isIced()).isEqualTo(isIced);

    }

}
