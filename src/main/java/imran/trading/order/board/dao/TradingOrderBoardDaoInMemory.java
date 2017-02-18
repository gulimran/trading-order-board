package imran.trading.order.board.dao;

import imran.trading.order.board.domain.Order;
import imran.trading.order.board.domain.OrderType;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.stream.Collectors.toList;
import static org.springframework.util.Assert.notNull;

@Named
public class TradingOrderBoardDaoInMemory implements TradingOrderBoardDao {

    private static final AtomicLong ORDER_ID_GENERATOR = new AtomicLong();

    @Inject
    private DataSource dataSource;


    @Override
    public Long save(Order order) {
        notNull(order, "Order cannot be null");

        Long orderId = generatedOrderId();
        dataSource.orderTable().put(orderId, order);

        return orderId;
    }

    @Override
    public void remove(Long orderId) {
        notNull(orderId, "Order id cannot be null");

        dataSource.orderTable().remove(orderId);
    }

    @Override
    public Collection<Order> getAll(OrderType orderType) {
        notNull(orderType, "Order type cannot be null");

        return dataSource.orderTable().values().stream().filter(p -> p.getType() == orderType).collect(toList());
    }

    private Long generatedOrderId() {
        return ORDER_ID_GENERATOR.incrementAndGet();
    }
}
