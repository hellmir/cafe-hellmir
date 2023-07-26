package personal.cafe.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import personal.cafe.constant.CoffeeName;
import personal.cafe.constant.CoffeeSize;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PickUpTableTest {

    @DisplayName("픽업테이블에 놓인 커피는 입력된 커피와 종류가 동일하다.")
    @ParameterizedTest
    @CsvSource({
            "AMERICANO, TALL, true",
            "CAFFE_LATTE, GRANDE, false",
            "AMERICANO, VENTI, false"
    })
    void getCoffee(CoffeeName coffeeName, CoffeeSize coffeeSize, boolean isIced) {

        // given
        Coffee coffee = Coffee.createCoffee(coffeeName, coffeeSize, isIced);

        // when
        PickUpTable pickUpTable = new PickUpTable(coffee);

        // then
        assertThat(pickUpTable.getCoffee().getCoffeeName()).isEqualTo(coffeeName);

        assertThat(pickUpTable.getCoffee().getCoffeeSize()).isEqualTo(coffeeSize);

        assertThat(pickUpTable.getCoffee().isIced()).isEqualTo(isIced);

    }

}
