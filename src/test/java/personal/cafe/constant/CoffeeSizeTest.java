package personal.cafe.constant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CoffeeSizeTest {

    @DisplayName("커피의 크기는 자신의 고유한 가격을 가지고 있다.")
    @Test
    void getPrice() {

        // given: None

        // when
        CoffeeSize coffeeSize = CoffeeSize.valueOf("TALL");

        // then
        assertThat(coffeeSize.getPrice()).isEqualTo(0);

        // when
        coffeeSize = CoffeeSize.valueOf("GRANDE");

        // then
        assertThat(coffeeSize.getPrice()).isEqualTo(500);

        // when
        coffeeSize = CoffeeSize.valueOf("VENTI");

        // then
        assertThat(coffeeSize.getPrice()).isEqualTo(1_000);

    }

}
