package main.java.personal.cafe.constant;

public enum CoffeeName {

    AMERICANO(4_500, "아메리카노"), CAFE_LATTE(5_000, "카페라떼");

    private final Integer price;
    private final String koreanName;

    CoffeeName(Integer price, String koreanName) {

        this.price = price;
        this.koreanName = koreanName;

    }

    public int getPrice() {
        return price;
    }

    public String getKoreanName() {
        return koreanName;
    }

}
