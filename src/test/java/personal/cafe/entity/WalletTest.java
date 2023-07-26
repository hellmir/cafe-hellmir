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

public class WalletTest {

    @DisplayName("최초로 생성된 지갑에는 돈이 충전되어 있지 않다.")
    @Test
    void createWallet() {

        // given: None

        // when
        Wallet wallet = Wallet.createWallet();

        // then
        assertThat(wallet.getChargedMoney()).isEqualTo(0);

    }

    @DisplayName("유효한 금액을 충전하면 해당 금액만큼 지갑에 충전된다.")
    @ParameterizedTest
    @CsvSource({
            "1",
            "3_000",
            "10_000"
    })
    void chargeMoneyWithVariousMoneyInputs(Integer money) {

        // given
        Wallet wallet = Wallet.createWallet();

        // when
        Wallet chargedWallet = wallet.chargeMoney(money);

        // then
        assertThat(chargedWallet.getChargedMoney()).isEqualTo(money);

    }

    @DisplayName("유효하지 않은 금액을 충전하려 하면 IllegalArgumentException이 발생한다.")
    @ParameterizedTest
    @CsvSource({
            "-3_000",
            "-1",
            "0"
    })
    void chargeMoneyWithWrongMoneyInputs(Integer money) {

        // given
        Wallet wallet = Wallet.createWallet();


        // when-then
        assertThatThrownBy(() -> wallet.chargeMoney(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("충전 금액은 0 또는 음수값이 될 수 없습니다.");

    }

    @DisplayName("잔액 이하의 금액을 지불하면 해당 금액만큼 지갑에서 차감된다.")
    @ParameterizedTest
    @CsvSource({
            "4_500, 고객1, AMERICANO, TALL, true",
            "30_000, 고객2, CAFFE_LATTE, GRANDE, false",
            "5_500, 고객3, AMERICANO, VENTI, true"
    })
    void payMoneyWithVariousBalance(Integer money, String name, CoffeeName coffeeName,
                                    CoffeeSize coffeeSize, boolean isIced) {

        // given
        Wallet wallet = Wallet.createWallet();
        Wallet chargedWallet = wallet.chargeMoney(money);

        Menu menu = Menu.createMenu(coffeeName, coffeeSize, isIced);

        // when
        Wallet paidWallet = chargedWallet.payMoney(name, menu.getPrice());

        // then
        assertThat(paidWallet.getChargedMoney()).isEqualTo(money - menu.getPrice());

    }

    @DisplayName("잔액을 초과하는 금액을 지불하려 하면 InsufficientBalanceException이 발생한다.")
    @ParameterizedTest
    @CsvSource({
            "3_000, 고객1, AMERICANO, TALL, true",
            "1, 고객2, CAFFE_LATTE, GRANDE, false",
            "5_499, 고객3, AMERICANO, VENTI, true"
    })
    void payMoneyWithInsufficientBalance(Integer money, String name, CoffeeName coffeeName,
                                         CoffeeSize coffeeSize, boolean isIced) {

        // given
        Wallet wallet = Wallet.createWallet();
        Wallet chargedWallet = wallet.chargeMoney(money);

        Menu menu = Menu.createMenu(coffeeName, coffeeSize, isIced);

        // when-then
        assertThatThrownBy(() -> chargedWallet.payMoney(name, menu.getPrice()))
                .isInstanceOf(InsufficientBalanceException.class)
                .hasMessage("잔액이 부족합니다. 결제하시려면 금액을 더 충전해 주세요. (현재 잔액: "
                        + chargedWallet.getChargedMoney() + "원, 주문 금액: " + menu.getPrice() + "원)");

    }

}
