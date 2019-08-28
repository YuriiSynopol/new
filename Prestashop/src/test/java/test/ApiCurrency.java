package test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;


public class ApiCurrency {

    @BeforeTest
    public void startAPI() {
        RestAssured.baseURI = "http://T6EKSW6BIMRA1PEEX6URFTUMP5JPJKH1@prestashop/api/currencies";
    }

    @Test(priority = 1)
    public void testStatusCode() {
        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.request(Method.GET);
        int code = response.statusCode();
        Assert.assertEquals(code, 200);

    }

    @Test(priority = 2)
    public void checkCurrencySize() {
        int expected = 3;
        given().
                when().
                get("http://T6EKSW6BIMRA1PEEX6URFTUMP5JPJKH1@prestashop/api/currencies/?io_format=JSON").
                then().
                assertThat().
                statusCode(200).
                and().
                body("currencies.id", hasSize(expected));

    }

    @Test(priority = 3)
    public void checkCurrencyInformationEu() {
      //  BasicConfigurator.configure();
        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.request(Method.GET, "/2/?io_format=JSON");

        JsonPath jsonpath = response.jsonPath();

        System.out.println(jsonpath.getString("currency.id"));
        System.out.println(jsonpath.getString("currency.iso_code"));
        System.out.println(jsonpath.getString("currency.numeric_iso_code"));
        System.out.println(jsonpath.getString("currency.precision"));
        System.out.println(jsonpath.getString("currency.conversion_rate"));
        System.out.println(jsonpath.getString("currency.deleted"));
        System.out.println(jsonpath.getString("currency.active"));
        System.out.println(jsonpath.getString("currency.name"));
        System.out.println(jsonpath.getString("currency.symbol"));

        Assert.assertEquals(jsonpath.getString("currency.id"), "2");
        Assert.assertEquals(jsonpath.getString("currency.iso_code"), "EUR");
        Assert.assertEquals(jsonpath.getString("currency.numeric_iso_code"), "978");
        Assert.assertEquals(jsonpath.getString("currency.precision"), "2");
        Assert.assertEquals(jsonpath.getString("currency.conversion_rate"), "0.800000");
        Assert.assertEquals(jsonpath.getString("currency.deleted"), "0");
        Assert.assertEquals(jsonpath.getString("currency.active"), "1");
        Assert.assertEquals(jsonpath.getString("currency.name"), "євро");
        Assert.assertEquals(jsonpath.getString("currency.symbol"), "€");



    }
}
