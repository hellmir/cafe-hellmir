package personal.cafe.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import personal.cafe.constant.CoffeeName;
import personal.cafe.constant.CoffeeSize;
import personal.cafe.exception.InsufficientBalanceException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CustomerTest {


    @DisplayName("고객이 유효하지 않은 금액을 가지고 커피를 주문하려 하면 IllegalArgumentException이 발생한다.")
    @Test
    void validateOrderCoffeeHasInValidChargingMoney() {

        // given
        Customer customer = new Customer("지갑", Wallet.createWallet());

        Menu menu = Menu.createMenu
                (CoffeeName.valueOf("AMERICANO"), CoffeeSize.valueOf("TALL"), true);

        // when-then
        assertThatThrownBy(() -> customer.orderCoffee
                (0, new Barista("바리스타"), menu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("충전 금액은 0 또는 음수값이 될 수 없습니다.");

    }

    @DisplayName("고객이 주문하기에 충분하지 않은 금액을 가지고 커피를 주문하려 하면 InsufficientBalanceException이 발생한다.")
    @Test
    void validateOrderCoffeeHasInValidPayment() {

        // given
        Customer customer = new Customer("지갑", Wallet.createWallet());

        Menu menu = Menu.createMenu
                (CoffeeName.valueOf("CAFFE_LATTE"), CoffeeSize.valueOf("VENTI"), true);

        // when-then
        assertThatThrownBy(() -> customer.orderCoffee
                (3_000, new Barista("바리스타"), menu))
                .isInstanceOf(InsufficientBalanceException.class)
                .hasMessage("잔액이 부족합니다. 결제하시려면 금액을 더 충전해 주세요. (현재 잔액: "
                        + customer.getWallet().getChargedMoney() + "원, 주문 금액: "
                        + (int) (CoffeeName.valueOf("CAFFE_LATTE").getPrice()
                        + CoffeeSize.valueOf("VENTI").getPrice()) + "원)");

    }

    @DisplayName("커피를 주문한 후 고객의 지갑에는 '충전 금액 - 주문 가격'만큼의 잔액이 남아 있다.")
    @ParameterizedTest
    @CsvSource({
            "10_000, 바리스타1, AMERICANO, TALL, ICE",
            "20_000, 바리스타2, CAFFE_LATTE, GRANDE, HOT",
            "30_000, 바리스타3, AMERICANO, VENTI, ICE"
    })
    void checkWalletBalanceAfterOrderCoffee
            (Integer money, Barista barista, String coffeeName, String coffeeSize, String iceOption) {

        // given
        Customer customer = new Customer("지갑", Wallet.createWallet());

        Menu menu = Menu.createMenu
                (CoffeeName.valueOf(coffeeName), CoffeeSize.valueOf(coffeeSize), iceOption.equals("ICE"));

        // when
        customer.orderCoffee(money, barista, menu);

        // then
        assertThat(customer.getWallet().getChargedMoney())
                .isEqualTo(+money - (menu.getCoffeeName().getPrice()
                        + menu.getCoffeeSize().getPrice()));

    }

    @DisplayName("커피를 주문한 후 고객은 입력된 메뉴판의 커피 종류와 동일한 커피를 가지고 있다.")
    @ParameterizedTest
    @CsvSource({
            "10_000, 바리스타1, AMERICANO, TALL, ICE",
            "20_000, 바리스타2, CAFFE_LATTE, GRANDE, HOT",
            "30_000, 바리스타3, AMERICANO, VENTI, ICE"
    })
    void checkCoffeeIsCorrectAfterOrderCoffee
            (Integer money, Barista barista, String coffeeName, String coffeeSize, String iceOption) {

        // given
        Customer customer = new Customer("지갑", Wallet.createWallet());

        Menu menu = Menu.createMenu
                (CoffeeName.valueOf(coffeeName), CoffeeSize.valueOf(coffeeSize), iceOption.equals("ICE"));

        // when
        customer.orderCoffee(money, barista, menu);

        // then
        assertThat(customer.getCoffee().getCoffeeName()).isEqualTo(CoffeeName.valueOf(coffeeName));

        assertThat(customer.getCoffee().getCoffeeSize()).isEqualTo(CoffeeSize.valueOf(coffeeSize));

        assertThat(customer.getCoffee().isIced()).isEqualTo(iceOption.equals("ICE"));

    }

}
