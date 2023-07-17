package main.java.personal.cafe.entity;

import main.java.personal.cafe.exception.InsufficientBalanceException;

public class Wallet {

    private String name = "지갑";
    private Integer chargedMoney;

    private Wallet(Integer money) {
        chargedMoney = money;
    }

    public static Wallet createWallet() {
        return new Wallet(0);
    }

    public Wallet chargeMoney(Integer money) {

        if (money <= 0) {
            throw new IllegalArgumentException("충전 금액은 0 또는 음수값이 될 수 없습니다.");
        }

        return new Wallet(chargedMoney + money);

    }

    public Wallet payMoney(String name, Integer price) {

        if (price > chargedMoney) {
            throw new InsufficientBalanceException
                    ("잔액이 부족합니다. 결제하시려면 금액을 더 충전해 주세요. (현재 잔액: "
                            + chargedMoney + "원, 주문 금액: " + price + "원)");
        }

        Integer paidMoney = chargedMoney - price;

        if (paidMoney <= 3_000) {
            System.out.printf("%s : '%s 고객님의 잔액이 %d원입니다. 계속 사용하시려면 금액을 충전해야 합니다.'\n"
                    , this.name, name, chargedMoney);
        }

        return new Wallet(paidMoney);

    }
}