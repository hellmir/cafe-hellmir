package personal.cafe.entity;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import personal.cafe.constant.CoffeeName;
import personal.cafe.constant.CoffeeSize;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MenuTest {

    @ParameterizedTest
    @CsvSource({
            "AMERICANO, TALL, ICE",
            "CAFFE_LATTE, GRANDE, HOT",
            "AMERICANO, VENTI, ICE"
    })
    void createMenu(CoffeeName coffeeName, CoffeeSize coffeeSize, boolean isIced) {

        Menu menu = Menu.createMenu(coffeeName, coffeeSize, isIced);

        assertThat(menu.getCoffeeName()).isEqualTo(coffeeName);
        assertThat(menu.getCoffeeSize()).isEqualTo(coffeeSize);
        assertThat(menu.isIced()).isEqualTo(isIced);
        assertThat(menu.getPrice()).isEqualTo(coffeeName.getPrice() + coffeeSize.getPrice());

    }

}
