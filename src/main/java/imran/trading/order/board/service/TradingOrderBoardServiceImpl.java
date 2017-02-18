package imran.trading.order.board.service;

import imran.trading.order.board.api.TradingOrderBoardService;
import imran.trading.order.board.dao.TradingOrderBoardDao;
import imran.trading.order.board.domain.Order;
import imran.trading.order.board.domain.OrderSummary;
import imran.trading.order.board.domain.OrderType;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static imran.trading.order.board.util.OrdersUtil.create;
import static imran.trading.order.board.util.OrdersUtil.merge;
import static imran.trading.order.board.util.OrdersUtil.sort;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.springframework.util.Assert.notNull;

@Named
public class TradingOrderBoardServiceImpl implements TradingOrderBoardService {

    @Inject
    private TradingOrderBoardDao orderBoardDao;

    @Override
    public Order registerOrder(String userId, Double orderQuantity, Integer pricePerKg, OrderType orderType) {
        notNull(orderType, "Order type cannot be null");

        Order order = create(userId, orderQuantity, pricePerKg, orderType);
        Long orderId = orderBoardDao.save(order);
        order.setId(orderId);

        return order;
    }

    @Override
    public void cancelOrder(Long orderId) {
        notNull(orderId, "Order id cannot be null");

        orderBoardDao.remove(orderId);
    }

    @Override
    public List<OrderSummary> getOrderSummary(OrderType orderType) {
        notNull(orderType, "Order type cannot be null");

        Collection<Order> orders = orderBoardDao.getAll(orderType);

        if (orders.isEmpty()) {
            return emptyList();
        }

        Collection<Order> mergedOrders = sort(merge(new ArrayList<>(orders)), orderType);

        return mergedOrders.stream()
                .map(order -> OrderSummary.builder().quantity(order.getQuantity()).price(order.getPrice()).build())
                .collect(toList());
    }
}
