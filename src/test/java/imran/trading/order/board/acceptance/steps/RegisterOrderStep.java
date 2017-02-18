package silverbars.live.order.board.acceptance.steps;

import cucumber.api.java.en.When;
import org.springframework.test.context.ContextConfiguration;
import imran.trading.order.board.acceptance.util.ScenarioContext;
import imran.trading.order.board.api.TradingOrderBoardService;
import imran.trading.order.board.domain.Order;

import javax.inject.Inject;

@ContextConfiguration(locations = {"classpath:cucumber.xml"})
public class RegisterOrderStep {

    @Inject
    private TradingOrderBoardService tradingOrderBoardService;

    @Inject
    private ScenarioContext scenarioContext;

    @When("^the order is registered in the live order board service$")
    public void theOrderIsRegisteredInTheLiveOrderBoardService() throws Throwable {
        scenarioContext.registeredOrder = registerOrder(scenarioContext.actualOrders.get(0));
    }

    @When("^these order are registered in the live order board service$")
    public void theseOrderAreRegisteredInTheLiveOrderBoardService() throws Throwable {
        scenarioContext.actualOrders.forEach(this::registerOrder);
    }

    private Order registerOrder(Order order) {
        return tradingOrderBoardService.registerOrder(order.getUserId(), order.getQuantity(), order.getPrice(), order.getType());
    }
}
