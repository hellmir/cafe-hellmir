package personal.cafe.constant;

public enum CoffeeSize {

    TALL(0), GRANDE(500), VENTI(1_000);

    private final Integer price;

    CoffeeSize(Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

}
