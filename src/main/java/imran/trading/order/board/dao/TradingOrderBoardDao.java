package imran.trading.order.board.dao;

import imran.trading.order.board.domain.Order;
import imran.trading.order.board.domain.OrderType;

import java.util.Collection;

public interface TradingOrderBoardDao {

    Long save(Order order);

    void remove(Long orderId);

    Collection<Order> getAll(OrderType orderType);

}
