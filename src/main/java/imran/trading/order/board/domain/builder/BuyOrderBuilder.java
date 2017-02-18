package imran.trading.order.board.domain.builder;

import imran.trading.order.board.domain.BuyOrder;

public class BuyOrderBuilder {

    private String userId;
    private Double quantity;
    private Integer price;

    public static BuyOrderBuilder builder() {
        return new BuyOrderBuilder();
    }

    public BuyOrderBuilder userId(String userId) {
        this.userId = userId;
        return this;
    }

    public BuyOrderBuilder quantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public BuyOrderBuilder price(Integer price) {
        this.price = price;
        return this;
    }

    public BuyOrder build() {
        BuyOrder instance = new BuyOrder();
        instance.setUserId(userId);
        instance.setQuantity(quantity);
        instance.setPrice(price);
        return instance;
    }
}
