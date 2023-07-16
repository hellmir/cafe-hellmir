package main.java.personal.cafe.entity;

public class Barista {

    private String name;

    public Barista(String name) {
        this.name = name;
    }

    public PickUpTable makeCoffee(String customerName, Menu menu) {

        Coffee coffee = new Coffee(menu.getCoffeeName(), menu.getCoffeeSize(), menu.isIced());

        System.out.printf("%s : '%s 고객님 주문하신 %s 사이즈 %s %s 한 잔 나왔습니다.'\n"
                , name, customerName, coffee.coffeeSize, coffee.isIced ? "아이스" : "핫", coffee.coffeeName.getKoreanName());

        return new PickUpTable(coffee);

    }

}
