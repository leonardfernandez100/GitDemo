package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.Iterator;
import java.util.Set;

public class GreenCartStepDefinition {
    public WebDriver driver;
    public String offerPageProductName, landingPageProductName;


    @Given("User in on GreenCart Landing page")
    public void user_in_on_green_cart_landing_page() {
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
        driver.manage().window().maximize();

    }

    @When("user searched with Shortname {string} and extracted actual name of product")
    public void user_searched_with_shortname_and_extracted_actual_name_of_product(String shortName) throws InterruptedException {
        driver.findElement(By.xpath("//input[@type='search']")).sendKeys(shortName);
        Thread.sleep(2000);
        landingPageProductName = driver.findElement(By.cssSelector("h4.product-name")).getText().split("-")[0].trim();
        System.out.println(landingPageProductName + " is extracted from Home page");
    }

    @Then("user searched for {string} shortname in offers page")
    public void user_searched_for_same_shortname_in_offers_page_to_check_if_product_exist(String shortName)
                throws InterruptedException {
        driver.findElement(By.linkText("Top Deals")).click();

        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();
        String parentWindows = i1.next();
        String childWindows = i1.next();

        System.out.println(parentWindows);
        System.out.println(childWindows);

        driver.switchTo().window(childWindows);
        driver.findElement(By.xpath("//input[@type='search']")).sendKeys(shortName);
        Thread.sleep(2000);
        offerPageProductName =driver.findElement(By.cssSelector("tr td:nth-child(1)")).getText();
        System.out.println(offerPageProductName + " is extracted from Offer page");
    }

    @And("validate product name in offer page matches with Landing Page")
    public void validate_product_name_in_offer_page_matches_with_Landing_Page() {
        Assert.assertEquals(offerPageProductName,landingPageProductName);
        driver.quit();
    }

}
