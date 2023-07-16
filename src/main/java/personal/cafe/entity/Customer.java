package main.java.personal.cafe.entity;

import main.java.personal.cafe.constant.CoffeeName;
import main.java.personal.cafe.constant.CoffeeSize;
import main.java.personal.cafe.exception.InsufficientBalanceException;
import main.java.personal.cafe.handler.CoffeeInputValidationHandler;

public class Customer {

    private String name;
    private Wallet wallet;
    private Coffee coffee;

    public Customer(String name, Wallet wallet) {

        this.name = name;
        this.wallet = wallet;

    }

    private void chargeWallet(Integer money) {

        if (money <= 0) {
            throw new IllegalArgumentException("충전 금액은 0 또는 음수값이 될 수 없습니다.");
        }

        wallet = new Wallet(wallet.getChargedMoney() + money);
    }

    private void payWallet(Integer price) {

        if (price > wallet.getChargedMoney()) {
            throw new InsufficientBalanceException
                    ("잔액이 부족합니다. 결제하시려면 금액을 더 충전해 주세요. (현재 잔액: "
                            + wallet.getChargedMoney() + "원, 주문 금액: " + price + "원)");
        }

        wallet = new Wallet(name, wallet.getChargedMoney() - price);

    }

    public void orderCoffee(Integer money, Barista barista, String coffeeName, String coffeeSize, String iceOption) {

        chargeWallet(money);

        new CoffeeInputValidationHandler().validateCoffeeInputFormat(coffeeName, coffeeSize, iceOption);

        Menu menu = new Menu(CoffeeName.valueOf(coffeeName), CoffeeSize.valueOf(coffeeSize), iceOption.equals("ICE"));

        System.out.printf("%s : '%s %s 한 잔 %s(으)로 주세요.'\n"
                , name, menu.getCoffeeSize(), menu.getCoffeeName().getKoreanName(), menu.isIced() ? "아이스" : "핫");

        payWallet(menu.getPrice());

        PickUpTable pickUpTable = barista.makeCoffee(name, menu);

        System.out.printf("%s : '감사합니다.'", name);

        coffee = pickUpTable.getCoffee();

    }

}
