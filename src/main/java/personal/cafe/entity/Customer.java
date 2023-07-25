package personal.cafe.entity;

import personal.cafe.constant.CoffeeName;
import personal.cafe.constant.CoffeeSize;
import personal.cafe.handler.CoffeeInputValidationHandler;

public class Customer {

    private String name;
    private Wallet wallet;
    private Coffee coffee;

    public Customer(String name, Wallet wallet) {

        this.name = name;
        this.wallet = wallet;

    }

    public void orderCoffee(Integer money, Barista barista, String coffeeName, String coffeeSize, String iceOption) {

        wallet = wallet.chargeMoney(money);

        new CoffeeInputValidationHandler().validateCoffeeInputFormat(coffeeName, coffeeSize, iceOption);

        Menu menu = Menu.createMenu
                (CoffeeName.valueOf(coffeeName), CoffeeSize.valueOf(coffeeSize), iceOption.equals("ICE"));

        System.out.printf("%s : '%s %s 한 잔 %s(으)로 주세요.'\n"
                , name, menu.getCoffeeSize(), menu.getCoffeeName().getKoreanName(), menu.isIced() ? "아이스" : "핫");

        wallet = wallet.payMoney(name, menu.getPrice());

        PickUpTable pickUpTable = barista.makeCoffee(name, menu);

        System.out.printf("%s : '감사합니다.'", name);

        coffee = pickUpTable.getCoffee();

    }

    public Wallet getWallet() {
        return wallet;
    }

    public Coffee getCoffee() {
        return coffee;
    }
}