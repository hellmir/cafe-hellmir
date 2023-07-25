package personal.cafe.constant;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CoffeeNameTest {

    @Test
    void getPrice() {

        CoffeeName coffeeName = CoffeeName.valueOf("AMERICANO");
        assertThat(coffeeName.getPrice()).isEqualTo(4_500);

        coffeeName = CoffeeName.valueOf("CAFFE_LATTE");
        assertThat(coffeeName.getPrice()).isEqualTo(5_000);

    }

    @Test
    void getKoreanName() {

        CoffeeName coffeeName = CoffeeName.valueOf("AMERICANO");
        assertThat(coffeeName.getKoreanName()).isEqualTo("아메리카노");

        coffeeName = CoffeeName.valueOf("CAFFE_LATTE");
        assertThat(coffeeName.getKoreanName()).isEqualTo("카페라떼");

    }

}
