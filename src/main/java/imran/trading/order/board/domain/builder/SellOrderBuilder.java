package imran.trading.order.board.domain.builder;

import imran.trading.order.board.domain.SellOrder;

public class SellOrderBuilder {

    private String userId;
    private Double quantity;
    private Integer price;

    public static SellOrderBuilder builder() {
        return new SellOrderBuilder();
    }

    public SellOrderBuilder userId(String userId) {
        this.userId = userId;
        return this;
    }

    public SellOrderBuilder quantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public SellOrderBuilder price(Integer price) {
        this.price = price;
        return this;
    }

    public SellOrder build() {
        SellOrder instance = new SellOrder();
        instance.setUserId(userId);
        instance.setQuantity(quantity);
        instance.setPrice(price);
        return instance;
    }
}
