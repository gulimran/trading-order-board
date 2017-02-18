package imran.trading.order.board.api;

import imran.trading.order.board.domain.Order;
import imran.trading.order.board.domain.OrderSummary;
import imran.trading.order.board.domain.OrderType;

import java.util.List;

/**
 * Silver Bars Marketplace 'Live Order Board' displays to its users the demand for silver bars on the market.
 * It provides users with the order summary with orders with the same price merged together and
 * sorted lowest price first for sell orders and highest price first for buy orders.
 */
public interface TradingOrderBoardService {


    /**
     * Registers a new @{link Order} to the live order board.
     *
     * @param userId - the user id of the order to be registered.
     * @param orderQuantity - the quantity in Kg of the order to be registered.
     * @param pricePerKg - the price in GBP of the order to be registered.
     * @param orderType - the @{link OrderType} of the order to be registered.
     * @return - the @{link Order} if the registration is successful.
     */
    Order registerOrder(String userId, Double orderQuantity, Integer pricePerKg, OrderType orderType);

    /**
     * Cancels an existing @{link Order} and removes it from the live order board.
     *
     * @param orderId - the order id of the order to be removed.
     */
    void cancelOrder(Long orderId);

    /**
     * Provides @{link List} of @{link OrderSummary} for given @{link OrderType}. The order summary has orders
     * with the same price merged together and is sorted lowest price first for sell orders and
     * highest price first for buy orders.
     *
     * @param orderType - the @{link OrderType} oto get the summary of existing orders of that type.
     */
    List<OrderSummary> getOrderSummary(OrderType orderType);
}
