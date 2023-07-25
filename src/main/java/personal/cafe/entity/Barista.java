package personal.cafe.entity;

public class Barista {

    private String name;

    public Barista(String name) {
        this.name = name;
    }

    public PickUpTable makeCoffee(String customerName, Menu menu) {

        Coffee coffee = Coffee.createCoffee(menu.getCoffeeName(), menu.getCoffeeSize(), menu.isIced());

        System.out.printf("%s : '%s 고객님 주문하신 %s 사이즈 %s %s 한 잔 나왔습니다.'\n"
                , name, customerName, coffee.getCoffeeSize(), coffee.isIced()
                        ? "아이스" : "핫", coffee.getCoffeeName().getKoreanName());

        return new PickUpTable(coffee);

    }

}
