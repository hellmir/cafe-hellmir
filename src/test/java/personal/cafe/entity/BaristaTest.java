package personal.cafe.entity;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import personal.cafe.constant.CoffeeName;
import personal.cafe.constant.CoffeeSize;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BaristaTest {

    @ParameterizedTest
    @CsvSource({
            "AMERICANO, TALL, true",
            "CAFFE_LATTE, GRANDE, false",
            "AMERICANO, VENTI, false"
    })
    void makeCoffeeWithVariousParameters(CoffeeName coffeeName, CoffeeSize coffeeSize, boolean isIced) {

        Barista barista = new Barista("바리스타");

        Menu menu = Menu.createMenu(coffeeName, coffeeSize, isIced);

        PickUpTable pickUpTable = barista.makeCoffee("고객", menu);

        assertThat(pickUpTable.getCoffee().getCoffeeName())
                .isEqualTo(coffeeName);
        assertThat(pickUpTable.getCoffee().getCoffeeSize())
                .isEqualTo(coffeeSize);
        assertThat(pickUpTable.getCoffee().isIced()).isEqualTo(isIced);

    }

}
