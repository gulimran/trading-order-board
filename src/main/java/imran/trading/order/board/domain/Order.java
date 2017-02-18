package imran.trading.order.board.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public abstract class Order {

    protected Long id;
    protected String userId;
    protected Double quantity;
    protected Integer price;
    protected OrderType type;

    public abstract OrderType getType();
}
