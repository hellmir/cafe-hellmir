package personal.cafe.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import personal.cafe.constant.CoffeeName;
import personal.cafe.constant.CoffeeSize;
import personal.cafe.exception.InsufficientBalanceException;
import personal.cafe.validation.CoffeeInputFormatValidator;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomerTest {

    @ParameterizedTest
    @CsvSource({
            "10_000, 바리스타1, AMERICANO, TALL, ICE",
            "20_000, 바리스타2, CAFFE_LATTE, GRANDE, HOT",
            "30_000, 바리스타3, AMERICANO, VENTI, ICE"
    })
    void validateOrderCoffeeIsValid(Integer money, Barista barista, String coffeeName, String coffeeSize, String iceOption) {

        Customer customer = new Customer("지갑", Wallet.createWallet());

        assertDoesNotThrow(() -> customer.orderCoffee(money, barista, coffeeName, coffeeSize, iceOption));

    }

    @Test
    void validateOrderCoffeeHasInValidChargingMoney() {

        Customer customer = new Customer("지갑", Wallet.createWallet());

        assertThatThrownBy(() -> customer.orderCoffee
                (0, new Barista("바리스타"), "AMERICANO", "TALL", "ICE"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("충전 금액은 0 또는 음수값이 될 수 없습니다.");

    }

    @Test
    void validateOrderCoffeeHasInValidPayment() {

        Customer customer = new Customer("지갑", Wallet.createWallet());

        assertThatThrownBy(() -> customer.orderCoffee
                (3_000, new Barista("바리스타"), "AMERICANO", "TALL", "ICE"))
                .isInstanceOf(InsufficientBalanceException.class)
                .hasMessage("잔액이 부족합니다. 결제하시려면 금액을 더 충전해 주세요. (현재 잔액: "
                        + customer.getWallet().getChargedMoney() + "원, 주문 금액: "
                        + (int) (CoffeeName.valueOf("AMERICANO").getPrice()
                        + CoffeeSize.valueOf("TALL").getPrice()) + "원)");

    }

    @Test
    void validateOrderCoffeeHasInValidCoffeeFormat() {

        Customer customer = new Customer("지갑", Wallet.createWallet());

        CoffeeInputFormatValidator coffeeInputFormatValidator = mock(CoffeeInputFormatValidator.class);

        List<String> expectedOptions = Arrays.asList("AMERICANO", "CAFFE_LATTE");

        when(coffeeInputFormatValidator.getCoffeeOptionList()).thenReturn(expectedOptions);

        assertThatThrownBy(() -> customer.orderCoffee
                (30_000, new Barista("바리스타"), "아메리카노", "톨", "아이스"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 커피 이름입니다. 현재 주문 가능한 커피 이름 목록: "
                        + coffeeInputFormatValidator.getCoffeeOptionList());

    }


    @ParameterizedTest
    @CsvSource({
            "10_000, 바리스타1, AMERICANO, TALL, ICE",
            "20_000, 바리스타2, CAFFE_LATTE, GRANDE, HOT",
            "30_000, 바리스타3, AMERICANO, VENTI, ICE"
    })
    void checkWalletBalanceAfterOrderCoffee(Integer money, Barista barista, String coffeeName, String coffeeSize, String iceOption) {

        Customer customer = new Customer("지갑", Wallet.createWallet());

        customer.orderCoffee(money, barista, coffeeName, coffeeSize, iceOption);

        assertThat(customer.getWallet().getChargedMoney())
                .isEqualTo(+money - (CoffeeName.valueOf(coffeeName).getPrice()
                        + CoffeeSize.valueOf(coffeeSize).getPrice()));

    }

    @ParameterizedTest
    @CsvSource({
            "10_000, 바리스타1, AMERICANO, TALL, ICE",
            "20_000, 바리스타2, CAFFE_LATTE, GRANDE, HOT",
            "30_000, 바리스타3, AMERICANO, VENTI, ICE"
    })
    void checkCoffeeIsCorrectAfterOrderCoffee(Integer money, Barista barista, String coffeeName, String coffeeSize, String iceOption) {

        Customer customer = new Customer("지갑", Wallet.createWallet());

        customer.orderCoffee(money, barista, coffeeName, coffeeSize, iceOption);

        assertThat(customer.getCoffee().getCoffeeName()).isEqualTo(CoffeeName.valueOf(coffeeName));
        assertThat(customer.getCoffee().getCoffeeSize()).isEqualTo(CoffeeSize.valueOf(coffeeSize));
        assertThat(customer.getCoffee().isIced()).isEqualTo(iceOption.equals("ICE"));

    }

}
