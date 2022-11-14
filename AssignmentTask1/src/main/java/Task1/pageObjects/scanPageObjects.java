package Task1.pageObjects;

import Task1.Interfaces.*;
import Task1.constructor;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class scanPageObjects
{
    //Variables
    WebDriver driver;
    findWhetherWebsiteWasOpenedController findController;
    findWhetherPostRequestWasMadeController findWhetherPostRequestWasMadeController;
    timesWebsiteIsOpenedController timesOpenedController;
    timesPostRequestIsMadeController timesPostRequestIsMadeController;
    findWhetherDeleteRequestWasMadeController findWhetherDeleteRequestWasMadeController;
    timesDeleteRequestIsMadeController timesDeleteRequestIsMadeController;
    int numberOfTimesWebsiteIsOpened;
    int numberOfTimesPostRequestIsMade;
    int numberOfTimesDeleteRequestIsMade;

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //Setter Injection 1
    public void setFindWhetherWebsiteWasOpenedController(findWhetherWebsiteWasOpenedController findController)
    {
        this.findController = findController;
    }

    //Setter Injection 2
    public void setFindWhetherPostRequestWasMadeController(findWhetherPostRequestWasMadeController findWhetherPostRequestWasMadeController)
    {
        this.findWhetherPostRequestWasMadeController = findWhetherPostRequestWasMadeController;
    }

    //Setter Injection 3
    public void findWhetherDeleteRequestWasMadeController(findWhetherDeleteRequestWasMadeController findWhetherDeleteRequestWasMadeController)
    {
        this.findWhetherDeleteRequestWasMadeController = findWhetherDeleteRequestWasMadeController;
    }

    //Setter Injection 4
    public void setTimesWebsiteIsOpenedController(timesWebsiteIsOpenedController timesOpenedController)
    {
        this.timesOpenedController = timesOpenedController;
    }

    //Setter Injection 5
    public void setTimesPostRequestIsMadeController(timesPostRequestIsMadeController timesPostRequestIsMadeController)
    {
        this.timesPostRequestIsMadeController = timesPostRequestIsMadeController;
    }

    //Setter Injection 6
    public void setTimesDeleteRequestIsMadeController(timesDeleteRequestIsMadeController timesDeleteRequestIsMadeController)
    {
        this.timesDeleteRequestIsMadeController = timesDeleteRequestIsMadeController;
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //Give true or false depending on whether the desired page is opened or not
    public boolean getResultOnWhetherPageWasOpenedOrNot()
    {
        if(!findController.checkWhetherWebsiteIsOpened())
        {
            return false;
        }

        return true;
    }

    //Give true or false depending on whether the post request was made or not
    public boolean getResultOnWhetherPostRequestWasMadeOrNot(int responseCode)
    {
        if (responseCode != 201 && !findWhetherPostRequestWasMadeController.checkWhetherPostRequestWasMade())
        {
            return false;
        }

        else
        {
            return true;
        }
    }

    //Give true or false depending on whether the delete request was made or not
    public boolean getResultOnWhetherDeleteRequestWasMadeOrNot(int responseCode)
    {
        if(findWhetherDeleteRequestWasMadeController.checkWhetherDeleteRequestWasMade())
        {
            if (responseCode != 200)
            {
                return false;
            }

            else
            {
                return true;
            }
        }

        return false;
    }

    //Give the number of times the desired website was loaded
    public int getNumberOfTimesWebsiteIsOpened()
    {
        if(timesOpenedController != null)
        {
            numberOfTimesWebsiteIsOpened = timesOpenedController.openPage();
        }

        return numberOfTimesWebsiteIsOpened;
    }

    //Give the number of times the post request was made
    public int getNumberOfTimesPostRequestIsMade()
    {
        if(timesPostRequestIsMadeController != null)
        {
            numberOfTimesPostRequestIsMade = timesPostRequestIsMadeController.makePostRequest();
        }

        return numberOfTimesPostRequestIsMade;
    }

    //Give the number of times the delete request was made
    public int getNumberOfTimesDeleteRequestIsMade()
    {
        if(timesDeleteRequestIsMadeController != null)
        {
            numberOfTimesDeleteRequestIsMade = timesDeleteRequestIsMadeController.makeDeleteRequest();
        }

        return numberOfTimesDeleteRequestIsMade;
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //Generic WebDriver Method 1
    public void openWebsite()
    {
        System.setProperty("webdriver.chrome.driver", "C:/Users/jerem/webtesting/chromedriver.exe");
        driver = new ChromeDriver();

        driver.get("https://www.scanmalta.com/shop/");
    }

    //Generic WebDriver Method 2
    public void searchOnWebsite(String item)
    {
        openWebsite();

        //Search for laptops in scan website
        WebElement searchBar = driver.findElement(By.name("q"));
        searchBar.sendKeys(item);
        searchBar.submit();
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //Retrieve list of results for the product searched for (List contains around 200 items)
    public List<WebElement> returnAllProductResults()
    {
        List<WebElement> productSearchResults = new LinkedList<>();
        productSearchResults = driver.findElements(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[5]/div[2]/ol/li"));
        return productSearchResults;
    }

    //Obtain required details regarding product searched for
    public List<constructor> getListOfProductInformation(List<WebElement> productSearchResults, int numberOfAlerts, int alertType)
    {
        String productHeading;
        String productDescription;
        String productURL;
        String productImage;
        String postedBy;
        String productPrice;

        List<constructor> productList = new LinkedList<>();

        for(int i=0; i<numberOfAlerts; i++)
        {
            productHeading = "Smartphone";
            productDescription = productSearchResults.get(i).findElement(By.className("product-item-link")).getText();
            productURL = productSearchResults.get(i).findElement(By.className("product-item-link")).getAttribute("href");
            productImage = productSearchResults.get(i).findElement(By.className("product-image-photo")).getAttribute("src");
            postedBy = "58ec3bf1-c290-4382-8b12-e2d1fe574e5b";
            productPrice = productSearchResults.get(i).findElement(By.className("price")).getText();
            productPrice = getPriceInCents(productPrice);

            productList.add(new constructor(alertType, productHeading, productDescription, productURL, productImage, postedBy, productPrice));
        }

        return productList;
    }

    //Convert the product price from euro to cents
    public String getPriceInCents(String productPrice)
    {
        productPrice = productPrice.replace("€", ""); //Remove € symbol from the price retrieved
        productPrice = productPrice.replace(".", ""); //Remove . symbol from the price retrieved
        return productPrice;
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //Convert contents of productResults into JSON format
    public String convertListToJSON(constructor productResults) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String JsonString = mapper.writeValueAsString(productResults);
        System.out.println(JsonString);
        mapper.writeValue(new File("C:\\Users\\jerem\\Desktop\\University\\Third Year\\Fundamentals of Software Testing\\JSON Items\\item.json"), productResults);
        return JsonString;
    }

    //Make a post request
    public int postRequest(String JsonString) throws IOException
    {
        URL url = new URL("https://api.marketalertum.com/Alert");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");

        byte[] out = JsonString.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        int responseCode = http.getResponseCode();
        System.out.print(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
        return responseCode;
    }

    //Make a delete request
    public int deleteRequest() throws IOException {
        URL url = new URL("https://api.marketalertum.com/Alert?userId=58ec3bf1-c290-4382-8b12-e2d1fe574e5b");
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestProperty("Content-Type", "application/json");
        httpCon.setRequestMethod("DELETE");
        int responseCode = httpCon.getResponseCode();
        httpCon.connect();
        return responseCode;
    }
}
