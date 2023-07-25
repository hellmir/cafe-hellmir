package personal.cafe.constant;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CoffeeSizeTest {

    @Test
    void getPrice() {

        CoffeeSize coffeeSize = CoffeeSize.valueOf("TALL");
        assertThat(coffeeSize.getPrice()).isEqualTo(0);

        coffeeSize = CoffeeSize.valueOf("GRANDE");
        assertThat(coffeeSize.getPrice()).isEqualTo(500);

        coffeeSize = CoffeeSize.valueOf("VENTI");
        assertThat(coffeeSize.getPrice()).isEqualTo(1_000);

    }

}
