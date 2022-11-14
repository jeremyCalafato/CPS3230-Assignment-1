package Task2;

import Task1.constructor;
import Task1.pageObjects.scanPageObjects;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class loginSteps
{

    WebDriver driver;

    @Given("^I am a user of marketalertum$")
    public void iAmAUserOfMarketalertum()
    {
        System.setProperty("webdriver.chrome.driver", "C:/Users/jerem/webtesting/chromedriver.exe");
        driver = new ChromeDriver();

        //Go to marketalertum website
        driver.get("https://www.marketalertum.com/");
    }

    @When("^I login using valid credentials$")
    public void iLoginUsingValidCredentials()
    {
        //Go to the login page
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);

        //Find and Enter valid login details into login bar
        WebElement loginBar = driver.findElement(By.name("UserId"));
        loginBar.sendKeys("58ec3bf1-c290-4382-8b12-e2d1fe574e5b");

        //Find and Press submit button
        WebElement sumbitButton = driver.findElement(By.name("__RequestVerificationToken"));
        sumbitButton.submit();
    }

    @Then("^I should see my alerts$")
    public void iShouldSeeMyAlerts()
    {
        String alertDisplay = driver.findElement(By.tagName("h1")).getText();
        Assertions.assertEquals("Latest alerts for Jeremy Calafato", alertDisplay);
    }

    @When("I login using invalid credentials")
    public void iLoginUsingInvalidCredentials()
    {
        //Go to the login page
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);

        //Find and Enter invalid login details into login bar
        WebElement loginBar = driver.findElement(By.name("UserId"));
        loginBar.sendKeys("58ec3bf1-c290-4382-8b12-e2d1fe574e5B");

        //Find and Press submit button
        WebElement sumbitButton = driver.findElement(By.name("__RequestVerificationToken"));
        sumbitButton.submit();
    }

    @Then("I should see the login screen again")
    public void iShouldSeeTheLoginScreenAgain()
    {
        String set = driver.findElement(By.tagName("input")).getAttribute("id");
        Assertions.assertEquals("UserId", set);
    }

    @Given("I am an administrator of the website and I upload {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAlerts(int arg0) throws IOException
    {
        scanPageObjects spo = new scanPageObjects();
        List<constructor> products = new LinkedList<>();

        arg0 = 3;
        int typeOfAlert = 6;

        spo.deleteRequest();

        spo.searchOnWebsite("Smartphone");

        List<WebElement> searchResults = new LinkedList<>();
        searchResults = spo.returnAllProductResults();
        products = spo.getListOfProductInformation(searchResults, arg0, typeOfAlert);

        for (constructor product: products)
        {
            String JsonString = spo.convertListToJSON(product);
            spo.postRequest(JsonString);
        }
    }

    @When("I view a list of alerts")
    public void iViewAListOfAlerts()
    {
        //Go to the login page
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);

        //Find and Enter valid login details into login bar
        WebElement loginBar = driver.findElement(By.name("UserId"));
        loginBar.sendKeys("58ec3bf1-c290-4382-8b12-e2d1fe574e5b");

        //Find and Press submit button
        WebElement sumbitButton = driver.findElement(By.name("__RequestVerificationToken"));
        sumbitButton.submit();

        List<WebElement> productSearchResults = new LinkedList<>();
        productSearchResults = driver.findElements(By.tagName("table"));
        //Assertions.assertEquals(3, productSearchResults.size());
    }

    @Then("each alert should contain an icon")
    public void eachAlertShouldContainAnIcon()
    {
        List<WebElement> icon = new LinkedList<>();
        icon = driver.findElements(By.xpath("/html/body/div/main/table/tbody/tr/td/h4/img"));
        Assertions.assertEquals(3, icon.size());
    }

    @And("each alert should contain a heading")
    public void eachAlertShouldContainAHeading()
    {
        List<WebElement> heading = new LinkedList<>();

        //Find for h4 since the text for 'smartphone' is contained within h4
        heading = driver.findElements(By.xpath("/html/body/div/main/table/tbody/tr/td/h4"));
        Assertions.assertEquals(3, heading.size());
    }

    @And("each alert should contain a description")
    public void eachAlertShouldContainADescription()
    {
        List<WebElement> description = new LinkedList<>();
        description = driver.findElements(By.xpath("/html/body/div/main/table/tbody/tr[3]/td"));
        Assertions.assertEquals(3, description.size());
    }

    @And("each alert should contain an image")
    public void eachAlertShouldContainAnImage()
    {
        List<WebElement> image = new LinkedList<>();
        image = driver.findElements(By.xpath("/html/body/div/main/table/tbody/tr[2]/td/img"));
        Assertions.assertEquals(3, image.size());
    }

    @And("each alert should contain a price")
    public void eachAlertShouldContainAPrice()
    {
        List<WebElement> price = new LinkedList<>();
        price = driver.findElements(By.xpath("/html/body/div/main/table/tbody/tr[4]/td/b"));
        Assertions.assertEquals(3, price.size());
    }

    @And("each alert should contain a link to the original product website")
    public void eachAlertShouldContainALinkToTheOriginalProductWebsite()
    {
        List<WebElement> originalProductWebsite = new LinkedList<>();
        originalProductWebsite = driver.findElements(By.xpath("/html/body/div/main/table/tbody/tr[5]/td/a"));
        Assertions.assertEquals(3, originalProductWebsite.size());
    }

    @Given("I am an administrator of the website and I upload more than {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadMoreThanAlerts(int arg0) throws IOException {
        scanPageObjects spo = new scanPageObjects();
        List<constructor> products = new LinkedList<>();

        int typeOfAlert = 6;

        spo.deleteRequest();

        spo.searchOnWebsite("Smartphone");

        List<WebElement> searchResults = new LinkedList<>();
        searchResults = spo.returnAllProductResults();
        products = spo.getListOfProductInformation(searchResults, arg0, typeOfAlert);

        for (constructor product: products)
        {
            String JsonString = spo.convertListToJSON(product);
            spo.postRequest(JsonString);
        }
    }

    @Then("I should see {int} alerts")
    public void iShouldSeeAlerts(int arg0)
    {
        List<WebElement> numberOfAlerts = new LinkedList<>();
        numberOfAlerts = driver.findElements(By.xpath("/html/body/div/main/table"));
        Assertions.assertEquals(arg0, numberOfAlerts.size());
    }

    @Given("I am an administrator of the website and I upload an alert of type {int}")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAnAlertOfTypeAlertType(int arg0) throws IOException {
        scanPageObjects spo = new scanPageObjects();
        List<constructor> constructor = new LinkedList<>();

        int arg1 = 1;

        spo.deleteRequest();
        spo.searchOnWebsite("smartphone");

        List<WebElement> searchResults = new LinkedList<>();
        searchResults = spo.returnAllProductResults();
        constructor = spo.getListOfProductInformation(searchResults, arg1, arg0);

        for (constructor product: constructor)
        {
            String JsonString = spo.convertListToJSON(product);
            spo.postRequest(JsonString);
        }

    }

    @And("the icon displayed should be {string}")
    public void theIconDisplayedShouldBeIconFileName(String arg0)
    {
        String iconDisplayed;

        iconDisplayed = driver.findElement(By.xpath("/html/body/div/main/table/tbody/tr/td/h4/img")).getAttribute("src");
        iconDisplayed = iconDisplayed.replaceAll("https://www.marketalertum.com/images/", "");
        iconDisplayed = iconDisplayed.replaceAll(".jpg", ".png");
        Assertions.assertEquals(arg0, iconDisplayed);
    }
}
