package personal.cafe.entity;

public class Customer {

    private String name;
    private Wallet wallet;
    private Coffee coffee;

    public Customer(String name, Wallet wallet) {

        this.name = name;
        this.wallet = wallet;

    }

    public void orderCoffee(Integer money, Barista barista, Menu menu) {

        wallet = wallet.chargeMoney(money);

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