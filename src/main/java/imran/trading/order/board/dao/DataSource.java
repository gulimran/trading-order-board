package imran.trading.order.board.dao;

import imran.trading.order.board.domain.Order;

import javax.inject.Named;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Named
public class DataSource {

    private static final Map<Long, Order> ORDER_TABLE = new ConcurrentHashMap<>();

    public Map<Long, Order> orderTable() {
        return ORDER_TABLE;
    }

}
