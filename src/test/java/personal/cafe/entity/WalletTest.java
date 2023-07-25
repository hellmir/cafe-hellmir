package personal.cafe.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import personal.cafe.constant.CoffeeName;
import personal.cafe.constant.CoffeeSize;
import personal.cafe.exception.InsufficientBalanceException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WalletTest {

    @Test
    void createWallet() {
        Wallet wallet = Wallet.createWallet();
        assertThat(wallet.getChargedMoney()).isEqualTo(0);
    }

    @ParameterizedTest
    @CsvSource({
            "1",
            "3_000",
            "10_000"
    })
    void chargeMoneyWithVariousMoneyInputs(Integer money) {

        Wallet wallet = Wallet.createWallet();
        Wallet chargedWallet = wallet.chargeMoney(money);

        assertThat(chargedWallet.getChargedMoney()).isEqualTo(money);

    }

    @ParameterizedTest
    @CsvSource({
            "-3_000",
            "-1",
            "0"
    })
    void chargeMoneyWithWrongMoneyInputs(Integer money) {

        Wallet wallet = Wallet.createWallet();

        assertThatThrownBy(() -> wallet.chargeMoney(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("충전 금액은 0 또는 음수값이 될 수 없습니다.");

    }

    @ParameterizedTest
    @CsvSource({
            "4_500, 고객1, AMERICANO, TALL, true",
            "30_000, 고객2, CAFFE_LATTE, GRANDE, false",
            "5_500, 고객3, AMERICANO, VENTI, true"
    })
    void payMoneyWithVariousBalance(Integer money, String name, CoffeeName coffeeName,
                                    CoffeeSize coffeeSize, boolean isIced) {

        Wallet wallet = Wallet.createWallet();
        Wallet chargedWallet = wallet.chargeMoney(money);

        Menu menu = Menu.createMenu(coffeeName, coffeeSize, isIced);
        Wallet paidWallet = chargedWallet.payMoney(name, menu.getPrice());

        assertThat(paidWallet.getChargedMoney()).isEqualTo(money - menu.getPrice());

    }

    @ParameterizedTest
    @CsvSource({
            "3_000, 고객1, AMERICANO, TALL, true",
            "1, 고객2, CAFFE_LATTE, GRANDE, false",
            "5_499, 고객3, AMERICANO, VENTI, true"
    })
    void payMoneyWithInsufficientBalance(Integer money, String name, CoffeeName coffeeName,
                                         CoffeeSize coffeeSize, boolean isIced) {

        Wallet wallet = Wallet.createWallet();
        Wallet chargedWallet = wallet.chargeMoney(money);

        Menu menu = Menu.createMenu(coffeeName, coffeeSize, isIced);

        assertThatThrownBy(() -> chargedWallet.payMoney(name, menu.getPrice()))
                .isInstanceOf(InsufficientBalanceException.class)
                .hasMessage("잔액이 부족합니다. 결제하시려면 금액을 더 충전해 주세요. (현재 잔액: "
                        + chargedWallet.getChargedMoney() + "원, 주문 금액: " + menu.getPrice() + "원)");

    }

}
