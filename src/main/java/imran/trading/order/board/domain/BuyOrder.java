package imran.trading.order.board.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BuyOrder extends Order {

    private static final OrderType TYPE = OrderType.BUY;

    @Override
    public OrderType getType() {
        return TYPE;
    }
}
