package imran.trading.order.board.domain;

public enum OrderType {
    BUY,
    SELL;

    public static OrderType get(String orderType) {
        return OrderType.valueOf(orderType.toUpperCase());
    }

}
