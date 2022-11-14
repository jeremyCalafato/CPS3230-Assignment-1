package Task1;

import Task1.pageObjects.scanPageObjects;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.json.Json;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String args[]) throws IOException {
        scanPageObjects spo = new scanPageObjects();
        List<constructor> products = new LinkedList<>();

        spo.searchOnWebsite("Smartphone");

        List<WebElement> searchResults = new LinkedList<>();

        searchResults = spo.returnAllProductResults();

        products = spo.getListOfProductInformation(searchResults, 5, 6);

        System.out.println(products);

        for (constructor product: products) {

            String JsonString = spo.convertListToJSON(product);
            spo.postRequest(JsonString);
        }

        //int responseCode = spo.deleteRequest();
    }
}
