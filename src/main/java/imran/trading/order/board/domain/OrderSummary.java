package imran.trading.order.board.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class OrderSummary {

    Integer price;
    Double quantity;
}
