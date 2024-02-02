package pageobjects;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.BaseTest;

import java.math.BigDecimal;

public class PropertyPriceCalculationPage extends PageObject {
    private final String URL_PROPERTY_PRICE_CALCULATION_PAGE = BaseTest.getBaseUrl() + "/tools/hitung-harga-properti";
    private final By SELECTOR_INPUT_TOTAL_INCOME = By.cssSelector("input[placeholder='Penghasilan Total']");
    private final By SELECTOR_INPUT_TOTAL_EXPENDITURE = By.cssSelector("input[placeholder='Pengeluaran']");
    private final By SELECTOR_OPTION_TIME_PERIOD = By.id("waktu");
    private final By SELECTOR_BUTTON_CALCULATE = By.xpath("//button[contains(text(),'Hitung')]");
    private final By SELECTOR_RESULT_PRICE_CALCULATE = By.xpath("//div[@id='harga_hasil']//h3");
    private final By SELECTOR_MESSAGE_INPUT_TOTAL_EXPENDITURE = By.xpath("//input[@placeholder='Pengeluaran']/../following-sibling::p");

    public void openUrl(){
        openUrl(URL_PROPERTY_PRICE_CALCULATION_PAGE);
        waitFor(ExpectedConditions.urlToBe(URL_PROPERTY_PRICE_CALCULATION_PAGE));
    }

    public void inputTotalIncome(String totalIncome){
        waitFor(ExpectedConditions.visibilityOfElementLocated(SELECTOR_INPUT_TOTAL_INCOME));
        typeInto(find(SELECTOR_INPUT_TOTAL_INCOME), totalIncome);
    }

    public void inputTotalExpenditure(String totalExpenditure){
        waitFor(ExpectedConditions.visibilityOfElementLocated(SELECTOR_INPUT_TOTAL_EXPENDITURE));
        typeInto(find(SELECTOR_INPUT_TOTAL_EXPENDITURE), totalExpenditure);
    }

    public void selectTimePeriod(String timePeriod){
        waitFor(ExpectedConditions.visibilityOfElementLocated(SELECTOR_OPTION_TIME_PERIOD));
        find(SELECTOR_OPTION_TIME_PERIOD).select().byValue(timePeriod);
    }

    public void clickButtonCalculate(){
        waitFor(ExpectedConditions.elementToBeClickable(SELECTOR_BUTTON_CALCULATE));
        clickOn(find(SELECTOR_BUTTON_CALCULATE));
    }

    public BigDecimal getResultPriceCalculate(){
        waitFor(ExpectedConditions.visibilityOfElementLocated(SELECTOR_RESULT_PRICE_CALCULATE));

        // format result untuk menghilangkan Rp dan tanda titik
        String resultPrice = find(SELECTOR_RESULT_PRICE_CALCULATE).getText()
                                .replace("Rp", "")
                                .replace(".","")
                                .trim();

        return new BigDecimal(resultPrice);
    }

    public BigDecimal getResultPriceFromFormula(BigDecimal totalIncome, BigDecimal totalExpenditure, Integer timePeriod){
        int totalMonthPeriod = 12 * timePeriod;

        return (totalIncome.subtract(totalExpenditure)).multiply(BigDecimal.valueOf(totalMonthPeriod)).divide(BigDecimal.valueOf(3));
    }

    public Boolean isCalculateButtonDisabled(){
        waitFor(ExpectedConditions.visibilityOfElementLocated(SELECTOR_BUTTON_CALCULATE));

        return find(SELECTOR_BUTTON_CALCULATE).isDisabled();
    }

    public String getWarningMessageFieldTotalExpenditure(){
        return find(SELECTOR_MESSAGE_INPUT_TOTAL_EXPENDITURE).getText();
    }
}
