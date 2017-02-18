package imran.trading.order.board.acceptance.steps;

import cucumber.api.java.en.And;
import org.springframework.test.context.ContextConfiguration;
import imran.trading.order.board.acceptance.util.ScenarioContext;
import imran.trading.order.board.api.TradingOrderBoardService;

import javax.inject.Inject;

@ContextConfiguration(locations = {"classpath:cucumber.xml"})
public class CancelOrderStep {

    @Inject
    private TradingOrderBoardService tradingOrderBoardService;

    @Inject
    private ScenarioContext scenarioContext;


    @And("^when I cancel this order$")
    public void whenICancelThisOrder() throws Throwable {
        tradingOrderBoardService.cancelOrder(scenarioContext.registeredOrder.getId());
    }
}
