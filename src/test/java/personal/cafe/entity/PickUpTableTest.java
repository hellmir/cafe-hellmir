package personal.cafe.entity;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import personal.cafe.constant.CoffeeName;
import personal.cafe.constant.CoffeeSize;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PickUpTableTest {

    @ParameterizedTest
    @CsvSource({
            "AMERICANO, TALL, true",
            "CAFFE_LATTE, GRANDE, false",
            "AMERICANO, VENTI, false"
    })
    void getCoffee(CoffeeName coffeeName, CoffeeSize coffeeSize, boolean isIced) {

        Coffee coffee = Coffee.createCoffee(coffeeName, coffeeSize, isIced);

        PickUpTable pickUpTable = new PickUpTable(coffee);

        assertThat(pickUpTable.getCoffee().getCoffeeName()).isEqualTo(coffeeName);
        assertThat(pickUpTable.getCoffee().getCoffeeSize()).isEqualTo(coffeeSize);
        assertThat(pickUpTable.getCoffee().isIced()).isEqualTo(isIced);

    }

}
