package imran.trading.order.board.util;

import imran.trading.order.board.domain.Order;
import imran.trading.order.board.domain.OrderType;
import imran.trading.order.board.domain.builder.BuyOrderBuilder;
import imran.trading.order.board.domain.builder.SellOrderBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static imran.trading.order.board.domain.OrderType.SELL;

public class OrdersUtil {

    public static Order create(String userId, Double orderQuantity, Integer pricePerKg, OrderType orderType) {
        switch (orderType) {
            case SELL:
                return SellOrderBuilder.builder().userId(userId).quantity(orderQuantity).price(pricePerKg).build();
            case BUY:
                return BuyOrderBuilder.builder().userId(userId).quantity(orderQuantity).price(pricePerKg).build();
            default:
                throw new IllegalArgumentException(format("Invalid order type %s", orderType));
        }
    }

    public static List<Order> merge(List<Order> orders) {
        List<Order> mergedOrders = new ArrayList<>(orders);

        for (int i=0; i<orders.size(); i++) {
            Order order1 = orders.get(i);
            for (int j=i+1; j<orders.size(); j++) {
                Order order2 = orders.get(j);
                if (order1.getType() == order2.getType() && order1.getPrice().equals(order2.getPrice())) {
                    mergedOrders.remove(order1);
                    mergedOrders.remove(order2);
                    Order mergedOrder = create(order1.getUserId(), order1.getQuantity(), order1.getPrice(), order1.getType());
                    mergedOrder.setQuantity(mergedOrder.getQuantity() + order2.getQuantity());
                    mergedOrders.add(mergedOrder);
                }
            }
        }

        return mergedOrders;
    }

    public static List<Order> sort(List<Order> orders, OrderType orderType) {
        return orders.stream()
                .sorted((o1, o2) -> orderType == SELL ? o1.getPrice() - o2.getPrice() : o2.getPrice() - o1.getPrice())
                .collect(Collectors.toList());
    }
}
