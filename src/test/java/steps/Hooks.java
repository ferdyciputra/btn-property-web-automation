package steps;

import io.cucumber.java.After;
import net.serenitybdd.core.pages.PageObject;

public class Hooks extends PageObject {

    /**
     * Will be executed after each Scenario.
     */
    @After
    public void closeBrowser() {
        getDriver().quit();
    }
}
