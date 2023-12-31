package personal.cafe.constant;

public enum CoffeeName {

    AMERICANO(4_500, "아메리카노"), CAFFE_LATTE(5_000, "카페라떼");

    private final Integer price;
    private final String koreanName;

    CoffeeName(Integer price, String koreanName) {

        this.price = price;
        this.koreanName = koreanName;

    }

    public Integer getPrice() {
        return price;
    }

    public String getKoreanName() {
        return koreanName;
    }

}
