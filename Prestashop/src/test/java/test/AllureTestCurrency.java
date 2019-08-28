package test;

import data.Currencies;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SelectSomeClothesPage;
import pages.ShoppingCartPage;
import tools.TestRunner;


public class AllureTestCurrency extends TestRunner {
    @DataProvider
    public Object[][] currencyData() {
        // Read from ...
        return new Object[][]{
                {data.Currencies.EURO, "Euro"},
                {data.Currencies.UAH, "Ukrainian Hryvnia"},
                {data.Currencies.USD, "US Dollar"}
        };
    }
    @Description("@Description class AllureTestCurrency; checkCurrency().")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "currencyData", priority = 1)
    public void checkCurrency(Currencies currency, String title) {
        //
        HomePage homePage = new HomePage(driver);

        // Steps
        homePage.chooseCurrency(currency);
        delayExecution(1000); //Only for Presentation

        // Check
        Assert.assertEquals(homePage.getCurrencyTextUa(), "Ukrainian Hryvnia");
        Assert.assertEquals(homePage.getCurrencyTextEu(), "Euro");
        Assert.assertEquals(homePage.getCurrencyTextUsd(), "US Dollar");
    }
    @Description("@Description class AllureTestCurrency; checkCurrencyForOneElement().")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 2)
    public void checkCurrencyForOneElement() throws InterruptedException {
        String expected = "495.21";
        HomePage homePage = new HomePage(driver);
        delayExecution(1000); //Only for presentation
        //e
        // Steps
        homePage.clicktShirt();
        homePage.clickCurrencyMenu();
        homePage.clickCurrencyUa();
        delayExecution(1000); //Only for presentation
        // Check
        Assert.assertEquals(homePage.getProductPriceText(), expected);
    }

    @Description("@Description class AllureTestCurrency; checkExchangeCurrencyUsdToUah().")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 3)
    public void checkExchangeCurrencyUsdToUa() throws Exception {
        double expected = 495.21;
        double UAH = 28.5;
        HomePage homePage = new HomePage(driver);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);

        homePage.getProductsListComponent().getProductComponentByPartialName("Hummingbird Printed T-Shirt").clickToProduct();

        SelectSomeClothesPage clothesPage = new SelectSomeClothesPage(driver);
        clothesPage.clickToAddButton();
        delayExecution(1000); //Only for presentation

        shoppingCartPage.clickOrderButton();
        delayExecution(1000); //Only for presentation
        homePage.clickCurrencyMenu();
        homePage.clickCurrencyUsd();

        double usd = Double.parseDouble(shoppingCartPage.getProductPriceText().substring(1));
        double exchange = Math.round((UAH * usd) * 100.0) / 100.0;

        homePage.clickCurrencyMenu();
        homePage.clickCurrencyUa();
        shoppingCartPage.clickDeleteButton();

        Assert.assertEquals(exchange, expected);

    }

    @Description("@Description class AllureTestCurrency; checkExchangeCurrencyUsdToEu().")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 4)
    public void checkExchangeCurrencyUsdToEu() throws Exception {
        double expected = 15.3;

        HomePage homePage = new HomePage(driver);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);

        homePage.getProductsListComponent().getProductComponentByPartialName("Hummingbird Printed T-Shirt").clickToProduct();

        SelectSomeClothesPage clothesPage = new SelectSomeClothesPage(driver);
        clothesPage.clickToAddButton();
        delayExecution(1000); //Only for presentation

        shoppingCartPage.clickOrderButton();

        delayExecution(1000); //Only for presentation

        homePage.clickCurrencyMenu();
        homePage.clickCurrencyUsd();
        DB db = new DB();
        double usd = Double.parseDouble(shoppingCartPage.getProductPriceText().substring(1));
        double actual = Math.round((db.euro() * usd) * 100.0) / 100.0;

        Assert.assertEquals(actual, expected);
    }




}


