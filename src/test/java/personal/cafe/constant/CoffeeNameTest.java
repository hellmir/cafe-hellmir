package personal.cafe.constant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CoffeeNameTest {

    @DisplayName("커피의 이름은 자신의 고유한 가격을 가지고 있다.")
    @Test
    void getPrice() {

        // given: None

        // when
        CoffeeName coffeeName = CoffeeName.valueOf("AMERICANO");

        // then
        assertThat(coffeeName.getPrice()).isEqualTo(4_500);

        // when
        coffeeName = CoffeeName.valueOf("CAFFE_LATTE");

        // then
        assertThat(coffeeName.getPrice()).isEqualTo(5_000);

    }

    @DisplayName("커피의 이름은 자신의 고유한 한국 이름을 가지고 있다.")
    @Test
    void getKoreanName() {

        // given: None

        // when
        CoffeeName coffeeName = CoffeeName.valueOf("AMERICANO");

        // then
        assertThat(coffeeName.getKoreanName()).isEqualTo("아메리카노");

        // when
        coffeeName = CoffeeName.valueOf("CAFFE_LATTE");

        // then
        assertThat(coffeeName.getKoreanName()).isEqualTo("카페라떼");

    }

}
