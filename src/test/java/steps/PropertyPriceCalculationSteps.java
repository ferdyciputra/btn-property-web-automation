package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import org.junit.Assert;
import pageobjects.PropertyPriceCalculationPage;

import java.math.BigDecimal;

public class PropertyPriceCalculationSteps {

    @Steps
    PropertyPriceCalculationPage propertyPriceCalculationPage;

    BigDecimal totalIncome;
    BigDecimal totalExpenditure;
    Integer timePeriod;

    @Given("the user navigate to property price calculation page")
    public void theUserNavigateToPropertyPriceCalculationPage() {
        propertyPriceCalculationPage.openUrl();
    }

    @When("the user filled total income {string} each month")
    public void theUserFilledTotalIncomeEachMonth(String totalIncome) {
        this.totalIncome = new BigDecimal(totalIncome);
        propertyPriceCalculationPage.inputTotalIncome(totalIncome);
    }

    @And("the user filled total expenditure {string} each month")
    public void theUserFilledTotalExpenditureEachMonth(String totalExpenditure) {
        this.totalExpenditure = new BigDecimal(totalExpenditure);
        propertyPriceCalculationPage.inputTotalExpenditure(totalExpenditure);
    }

    @And("the user select time period {string} years")
    public void theUserSelectTimePeriodYears(String timePeriod) {
        this.timePeriod = Integer.valueOf(timePeriod);
        propertyPriceCalculationPage.selectTimePeriod(timePeriod);
    }

    @And("the user click calculate button")
    public void theUserClickCalculateButton() {
        propertyPriceCalculationPage.clickButtonCalculate();
    }

    @Then("the system should return a price calculated according to the formula")
    public void theSystemShouldReturnAPriceCalculatedAccordingToTheFormula() {
        BigDecimal actualCalculate = propertyPriceCalculationPage.getResultPriceCalculate();
        BigDecimal expectedCalculate = propertyPriceCalculationPage.getResultPriceFromFormula(this.totalIncome, this.totalExpenditure, this.timePeriod);

        Assert.assertEquals(expectedCalculate, actualCalculate);
    }

    @Then("the calculate button should be disabled")
    public void theCalculateButtonShouldBeDisabled() {
        String message = "Button Calculate must be disabled but found enabled.";

        Assert.assertTrue(message, propertyPriceCalculationPage.isCalculateButtonDisabled());
    }

    @Then("the user receive a warning message {string}")
    public void theUserReceiveAWarningMessage(String expectedMessage) {
        Assert.assertEquals(expectedMessage, propertyPriceCalculationPage.getWarningMessageFieldTotalExpenditure());
    }
}
