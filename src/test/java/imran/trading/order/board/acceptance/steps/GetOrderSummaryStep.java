package imran.trading.order.board.acceptance.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.springframework.test.context.ContextConfiguration;
import imran.trading.order.board.acceptance.util.ScenarioContext;
import imran.trading.order.board.api.TradingOrderBoardService;
import imran.trading.order.board.domain.OrderSummary;
import imran.trading.order.board.domain.OrderType;

import javax.inject.Inject;
import java.util.List;

import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@ContextConfiguration(locations = {"classpath:cucumber.xml"})
public class GetOrderSummaryStep {

    @Inject
    private TradingOrderBoardService tradingOrderBoardService;

    @Inject
    private ScenarioContext scenarioContext;

    @Then("^the live order board should provide the following summary for \"([^\"]*)\" orders:$")
    public void theLiveOrderBoardShouldProvideTheFollowingSummaryForOrders(String orderType, List<OrderSummary> orderSummaries) throws Throwable {
        scenarioContext.actualOrderSummaries = tradingOrderBoardService.getOrderSummary(OrderType.get(orderType));
        scenarioContext.expectedOrderSummaries = orderSummaries;
        assertThat(scenarioContext.expectedOrderSummaries, is(scenarioContext.actualOrderSummaries));
    }

    @Then("^the live order board should provide blank summary for \"([^\"]*)\" orders$")
    public void theLiveOrderBoardShouldProvideBlankSummaryForOrders(String orderType) throws Throwable {
        List<OrderSummary> orderSummary = tradingOrderBoardService.getOrderSummary(OrderType.get(orderType));
        assertThat(orderSummary, is(empty()));
    }

    @And("^sell orders are ordered with lowest price first$")
    public void sellOrdersAreOrderedWithLowestPriceFirst() throws Throwable {
        assertThat(scenarioContext.expectedOrderSummaries, is(scenarioContext.actualOrderSummaries));
    }

    @And("^buy orders are ordered with highest price first$")
    public void buyOrdersAreOrderedWithHighestPriceFirst() throws Throwable {
        assertThat(scenarioContext.expectedOrderSummaries, is(scenarioContext.actualOrderSummaries));
    }
}
