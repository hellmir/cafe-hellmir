package main.java.personal.cafe.entity;

public class Wallet {

    String name = "지갑";
    Integer chargedMoney;

    public Wallet(Integer chargedMoney) {
        this.chargedMoney = chargedMoney;
    }

    public Wallet(String name, Integer chargedMoney) {

        if (chargedMoney < 0) {
            throw new IllegalArgumentException("잔액이 부족합니다. 결제하시려면 금액을 충전해 주세요.");
        }


        if (chargedMoney <= 3_000) {
            System.out.printf("%s : '%s 고객님의 잔액이 %d원입니다. 계속 사용하시려면 금액을 충전해야 합니다.'\n"
                    , this.name, name, chargedMoney);
        }

        this.chargedMoney = chargedMoney;

    }

    public Integer getChargedMoney() {
        return chargedMoney;
    }
}
