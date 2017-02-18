package imran.trading.order.board.unit.util;

import org.junit.Test;
import imran.trading.order.board.domain.BuyOrder;
import imran.trading.order.board.domain.Order;
import imran.trading.order.board.domain.SellOrder;
import imran.trading.order.board.domain.builder.BuyOrderBuilder;
import imran.trading.order.board.domain.builder.SellOrderBuilder;
import imran.trading.order.board.util.OrdersUtil;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_LIST;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static imran.trading.order.board.domain.OrderType.BUY;
import static imran.trading.order.board.domain.OrderType.SELL;

public class OrdersUtilTest {

    @Test
    public void testCreate_CreatesSellOrder() {
        Order order = OrdersUtil.create("testUserId", 2.5, 300, SELL);

        assertTrue(SellOrder.class.isAssignableFrom(order.getClass()));
    }

    @Test
    public void testCreate_CreatesBuyOrder() {
        Order order = OrdersUtil.create("testUserId", 2.5, 300, BUY);

        assertTrue(BuyOrder.class.isAssignableFrom(order.getClass()));
    }

    @Test
    public void testMerge_WithEmptyListOfOrders() {
        List<Order> mergedList = OrdersUtil.merge(EMPTY_LIST);

        assertThat(mergedList, is(empty()));
    }

    @Test
    public void testMerge_WithListOfOneOrder() {
        Order order = SellOrderBuilder.builder().userId("testUserId1").quantity(5.0).price(175).build();

        List<Order> mergedList = OrdersUtil.merge(singletonList(order));

        assertThat(mergedList, contains(order));
    }

    @Test
    public void testMerge_WhenThereAreMultipleOrdersWithSamePrice_ReturnsListOfMergedOrders() {
        Order order1 = BuyOrderBuilder.builder().userId("testUserId1").quantity(4.0).price(160).build();
        Order order2 = BuyOrderBuilder.builder().userId("testUserId2").quantity(5.5).price(160).build();
        Order expectedOrder = BuyOrderBuilder.builder().userId("testUserId1").quantity(9.5).price(160).build();

        List<Order> mergedList = OrdersUtil.merge(asList(order1, order2));

        assertThat(mergedList, contains(expectedOrder));
    }

    @Test
    public void testMerge_WhenThereAreMultipleOrdersWithDifferentPrices_ReturnsListWithEachOrder() {
        Order order1 = SellOrderBuilder.builder().userId("testUserId1").quantity(5.0).price(175).build();
        Order order2 = SellOrderBuilder.builder().userId("testUserId2").quantity(3.5).price(180).build();

        List<Order> mergedList = OrdersUtil.merge(asList(order1, order2));

        assertThat(mergedList, containsInAnyOrder(order1, order2));
    }

    @Test
    public void testMerge_WhenThereAreMultipleOrdersWithDifferentTypes_ReturnsListWithEachOrder() {
        Order order1 = SellOrderBuilder.builder().userId("testUserId1").quantity(5.0).price(175).build();
        Order order2 = BuyOrderBuilder.builder().userId("testUserId2").quantity(3.5).price(175).build();

        List<Order> mergedList = OrdersUtil.merge(asList(order1, order2));

        assertThat(mergedList, containsInAnyOrder(order1, order2));
    }

    @Test
    public void testSort_WithEmptyListOfSellOrders() {
        List<Order> sortedList = OrdersUtil.sort(EMPTY_LIST, SELL);

        assertThat(sortedList, is(empty()));
    }

    @Test
    public void testSort_WithListOfOneOrder() {
        Order order = SellOrderBuilder.builder().userId("testUserId1").quantity(5.0).price(175).build();

        List<Order> sortedList = OrdersUtil.sort(singletonList(order), SELL);

        assertThat(sortedList, contains(order));
    }

    @Test
    public void testSort_WhenThereAreMultipleOrdersWithSamePrice_ReturnsListInSameOrders() {
        Order order1 = BuyOrderBuilder.builder().userId("testUserId1").quantity(4.0).price(160).build();
        Order order2 = BuyOrderBuilder.builder().userId("testUserId2").quantity(5.5).price(160).build();

        List<Order> sortedList = OrdersUtil.sort(asList(order1, order2), BUY);

        assertThat(sortedList, contains(order1, order2));
    }

    @Test
    public void testSort_WhenThereAreMultipleBuyOrdersWithDifferentPrice_ReturnsListHighestFirstOrders() {
        Order order1 = BuyOrderBuilder.builder().userId("testUserId1").quantity(4.0).price(160).build();
        Order order2 = BuyOrderBuilder.builder().userId("testUserId2").quantity(5.5).price(200).build();

        List<Order> sortedList = OrdersUtil.sort(asList(order1, order2), BUY);

        assertThat(sortedList, contains(order2, order1));
    }

    @Test
    public void testSort_WhenThereAreMultipleSellOrdersWithDifferentPrice_ReturnsListLowestFirstOrders() {
        Order order1 = SellOrderBuilder.builder().userId("testUserId1").quantity(4.0).price(250).build();
        Order order2 = SellOrderBuilder.builder().userId("testUserId2").quantity(5.5).price(200).build();

        List<Order> sortedList = OrdersUtil.sort(asList(order1, order2), SELL);

        assertThat(sortedList, contains(order2, order1));
    }



}