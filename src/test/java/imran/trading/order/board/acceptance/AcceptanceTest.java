package imran.trading.order.board.acceptance;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"},
        tags = {"~@ignore"},
        glue = {"imran.trading.order.board"})
public class AcceptanceTest { }
