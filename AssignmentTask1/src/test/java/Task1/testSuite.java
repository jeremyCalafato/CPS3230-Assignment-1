package Task1;

import Task1.Spies.timesDeleteRequestIsMadeControllerSpy;
import Task1.Spies.timesPostRequestIsMadeControllerSpy;
import Task1.Spies.timesWebsiteIsOpenedControllerSpy;
import Task1.Stubs.*;
import Task1.pageObjects.scanPageObjects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class testSuite
{

    //Test Dummy 1
    @Test
    public void testIfProductResultsAreConvertedToJSON() throws IOException {
        //Setup
        constructor c = new constructor(6, "Smartphone", "Samsung Galaxy A23 5G 128GB 4GB Dual Sim Black", "https://www.scanmalta.com/shop/samsung-galaxy-a23-5g-128gb-4gb-dual-sim-black.html", "https://www.scanmalta.com/shop/pub/media/catalog/product/cache/b084519189a7c7b3054def1f3dcab96f/8/1/81rmcqlevzl._ac_sl1500__1.jpg", "58ec3bf1-c290-4382-8b12-e2d1fe574e5b", "26900");
        scanPageObjects spo = new scanPageObjects();

        //Exercise
        String JsonString = spo.convertListToJSON(c);

        //Verify
        Assertions.assertEquals("{\"alertType\":6,\"heading\":\"Smartphone\",\"description\":\"Samsung Galaxy A23 5G 128GB 4GB Dual Sim Black\",\"url\":\"https://www.scanmalta.com/shop/samsung-galaxy-a23-5g-128gb-4gb-dual-sim-black.html\",\"imageUrl\":\"https://www.scanmalta.com/shop/pub/media/catalog/product/cache/b084519189a7c7b3054def1f3dcab96f/8/1/81rmcqlevzl._ac_sl1500__1.jpg\",\"postedBy\":\"58ec3bf1-c290-4382-8b12-e2d1fe574e5b\",\"priceInCents\":\"26900\"}", JsonString);
    }

    //Test Dummy 2
    @Test
    public void testIfPriceOfItemIsConvertedToCents()
    {
        //Setup
        scanPageObjects spo = new scanPageObjects();

        //Exercise
        String princeInCents = spo.getPriceInCents("€100.95");

        //Verify
        Assertions.assertEquals("10095", princeInCents);
    }

    //Test Stub 1
    @Test
    public void testIfWebsiteIsOpened()
    {
        //Setup
        scanPageObjects spo = new scanPageObjects();
        spo.setFindWhetherWebsiteWasOpenedController(new websiteOpened());

        //Exercise
        boolean resultFound = spo.getResultOnWhetherPageWasOpenedOrNot();

        //Verify
        Assertions.assertTrue(resultFound);
    }

    //Test Stub 2
    @Test
    public void testIfWebsiteIsNotOpened()
    {
        //Setup
        scanPageObjects spo = new scanPageObjects();
        spo.setFindWhetherWebsiteWasOpenedController(new websiteNotOpened());

        //Exercise
        boolean resultNotFound = spo.getResultOnWhetherPageWasOpenedOrNot();

        //Verify
        Assertions.assertFalse(resultNotFound);
    }

    //Test Stub 3
    @Test
    public void testIfPostRequestIsMade() throws IOException {
        //Setup
        scanPageObjects spo = new scanPageObjects();
        spo.setFindWhetherPostRequestWasMadeController(new postRequestMade());

        //Exercise
        int responseCode = spo.postRequest("{\"alertType\":6,\"heading\":\"Smartphone\",\"description\":\"Samsung Galaxy A23 5G 128GB 4GB Dual Sim Black\",\"url\":\"https://www.scanmalta.com/shop/samsung-galaxy-a23-5g-128gb-4gb-dual-sim-black.html\",\"imageUrl\":\"https://www.scanmalta.com/shop/pub/media/catalog/product/cache/b084519189a7c7b3054def1f3dcab96f/8/1/81rmcqlevzl._ac_sl1500__1.jpg\",\"postedBy\":\"58ec3bf1-c290-4382-8b12-e2d1fe574e5b\",\"priceInCents\":\"26900\"}");
        boolean resultFound = spo.getResultOnWhetherPostRequestWasMadeOrNot(responseCode);

        //Verify
        //Assertions.assertEquals(201, responseCode);
        Assertions.assertTrue(resultFound);
    }

    //Test Stub 4
    @Test
    public void testIfPostRequestIsNotMade() throws IOException {
        //Setup
        scanPageObjects spo = new scanPageObjects();
        spo.setFindWhetherPostRequestWasMadeController(new postRequestNotMade());

        //Exercise
        int responseCode = spo.postRequest("");
        boolean resultFound = spo.getResultOnWhetherPostRequestWasMadeOrNot(responseCode);

        //Verify
        //Assertions.assertEquals(400, responseCode);
        Assertions.assertFalse(resultFound);
    }

    //Test Stub 5
    @Test
    public void testIfDeleteRequestIsMade() throws IOException {
        //Setup
        scanPageObjects spo = new scanPageObjects();
        spo.findWhetherDeleteRequestWasMadeController(new deleteRequestMade());

        //Exercise
        int responseCode = spo.deleteRequest();
        boolean resultFound = spo.getResultOnWhetherDeleteRequestWasMadeOrNot(responseCode);

        //Verify
        //Assertions.assertEquals(200, responseCode);
        Assertions.assertTrue(resultFound);
    }

    //Test Stub 6
    @Test
    public void testIfDeleteRequestIsNotMade() throws IOException {
        //Setup
        scanPageObjects spo = new scanPageObjects();
        spo.findWhetherDeleteRequestWasMadeController(new deleteRequestNotMade());

        //Exercise
        int responseCode = 400;
        boolean resultFound = spo.getResultOnWhetherDeleteRequestWasMadeOrNot(responseCode);

        //Verify
        //Assertions.assertEquals(400, responseCode);
        Assertions.assertFalse(resultFound);
    }

    //Test Spy 1
    @Test
    public void testThatOnePageIsOpened()
    {
        //Setup
        timesWebsiteIsOpenedControllerSpy timesOpenedControllerSpy = new timesWebsiteIsOpenedControllerSpy();
        scanPageObjects spo = new scanPageObjects();
        spo.setTimesWebsiteIsOpenedController(timesOpenedControllerSpy);

        //Exercise
        int numberOfTimesWebsiteIsOpened = spo.getNumberOfTimesWebsiteIsOpened();

        //Verify
        Assertions.assertEquals(1, numberOfTimesWebsiteIsOpened);
    }

    //Test Spy 2
    @Test
    public void testThatOnePostRequestIsMade()
    {
        //Setup
        timesPostRequestIsMadeControllerSpy timesPostRequestIsMadeControllerSpy = new timesPostRequestIsMadeControllerSpy();
        scanPageObjects spo = new scanPageObjects();
        spo.setTimesPostRequestIsMadeController(timesPostRequestIsMadeControllerSpy);

        //Exercise
        int numberOfTimesPostRequestIsMade = spo.getNumberOfTimesPostRequestIsMade();

        //Verify
        Assertions.assertEquals(1, numberOfTimesPostRequestIsMade);
    }

    //Test Spy 3
    @Test
    public void testThatOneDeleteRequestIsMade()
    {
        //Setup
        timesDeleteRequestIsMadeControllerSpy timesDeleteRequestIsMadeControllerSpy = new timesDeleteRequestIsMadeControllerSpy();
        scanPageObjects spo = new scanPageObjects();
        spo.setTimesDeleteRequestIsMadeController(timesDeleteRequestIsMadeControllerSpy);

        //Exercise
        int numberOfTimesDeleteRequestIsMade = spo.getNumberOfTimesDeleteRequestIsMade();

        //Verify
        Assertions.assertEquals(1, numberOfTimesDeleteRequestIsMade);
    }
}
