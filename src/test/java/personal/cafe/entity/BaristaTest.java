package personal.cafe.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import personal.cafe.constant.CoffeeName;
import personal.cafe.constant.CoffeeSize;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BaristaTest {

    @DisplayName("바리스타가 제조한 커피는 주문 받은 메뉴판에 있는 커피와 종류가 동일하다.")
    @ParameterizedTest
    @CsvSource({
            "AMERICANO, TALL, true",
            "CAFFE_LATTE, GRANDE, false",
            "AMERICANO, VENTI, false"
    })
    void makeCoffeeWithVariousParameters(CoffeeName coffeeName, CoffeeSize coffeeSize, boolean isIced) {

        // given
        Barista barista = new Barista("바리스타");

        Menu menu = Menu.createMenu(coffeeName, coffeeSize, isIced);

        // when
        PickUpTable pickUpTable = barista.makeCoffee("고객", menu);

        // then
        assertThat(pickUpTable.getCoffee().getCoffeeName()).isEqualTo(coffeeName);

        assertThat(pickUpTable.getCoffee().getCoffeeSize()).isEqualTo(coffeeSize);

        assertThat(pickUpTable.getCoffee().isIced()).isEqualTo(isIced);

    }

}
