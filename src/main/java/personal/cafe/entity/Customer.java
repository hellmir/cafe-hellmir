package main.java.personal.cafe.entity;

import main.java.personal.cafe.constant.CoffeeName;
import main.java.personal.cafe.constant.CoffeeSize;

public class Customer {

    private String name;
    private Wallet wallet;
    private Coffee coffee;

    public Customer(String name, Wallet wallet) {

        this.name = name;
        this.wallet = wallet;

    }

    private void chargeWallet(Integer money) {
        wallet = new Wallet(name, wallet.getChargedMoney() + money);
    }

    private void payWallet(Integer price) {
        wallet = new Wallet(name, wallet.getChargedMoney() - price);
    }

    public void orderCoffee(Integer money, Barista barista, String coffeeName, String coffeeSize, String isIced) {

        chargeWallet(money);

        Menu menu = new Menu(CoffeeName.valueOf(coffeeName), CoffeeSize.valueOf(coffeeSize), isIced.equals("아이스"));

        System.out.printf("%s : '%s %s 한 잔 %s(으)로 주세요.'\n"
                , name, menu.getCoffeeSize(), menu.getCoffeeName().getKoreanName(), menu.isIced() ? "아이스" : "핫");

        payWallet(menu.getPrice());

        PickUpTable pickUpTable = barista.makeCoffee(name, menu);

        System.out.printf("%s : '감사합니다.'", name);

        coffee = pickUpTable.getCoffee();

    }

}
