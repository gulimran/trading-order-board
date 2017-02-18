package imran.trading.order.board.acceptance.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.springframework.test.context.ContextConfiguration;
import imran.trading.order.board.acceptance.util.ScenarioContext;
import imran.trading.order.board.api.TradingOrderBoardService;
import imran.trading.order.board.dao.DataSource;
import imran.trading.order.board.domain.BuyOrder;
import imran.trading.order.board.domain.SellOrder;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@ContextConfiguration(locations = {"classpath:cucumber.xml"})
public class LiveOrderBoardServiceStep {

    @Inject
    private TradingOrderBoardService tradingOrderBoardService;

    @Inject
    private DataSource dataSource;

    @Inject
    private ScenarioContext scenarioContext;

    @Given("^the live order board service is available$")
    public void theLiveOrderBoardServiceIsAvailable() throws Throwable {
        assertNotNull(tradingOrderBoardService);
    }

    @And("^there are no orders on the live order board$")
    public void thereAreNoOrdersOnTheLiveOrderBoard() throws Throwable {
        dataSource.orderTable().clear();
        scenarioContext.clear();
    }

    @Given("^a sell order with:$")
    public void aSellOrderWith(List<SellOrder> orders) throws Throwable {
        scenarioContext.actualOrders = orders;
    }

    @Given("^a buy order with:$")
    public void aBuyOrderWith(List<BuyOrder> orders) throws Throwable {
        scenarioContext.actualOrders = orders;
    }

    @Given("^multiple sell orders with:$")
    public void multipleSellOrdersWith(List<SellOrder> orders) throws Throwable {
        scenarioContext.actualOrders = orders;
    }

    @Given("^multiple buy orders with:$")
    public void multipleBuyOrdersWith(List<BuyOrder> orders) throws Throwable {
        scenarioContext.actualOrders = orders;
    }
}
