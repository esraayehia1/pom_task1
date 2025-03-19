package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pom.GetPaidPage;
import pom.HomePage;

import java.util.List;

public class TestClass {
    SoftAssert soft = new SoftAssert();
    WebDriver driver;
    GetPaidPage getPaidPage;
    HomePage home;

    @BeforeTest
    void open() {
        //open chrome
        driver = new ChromeDriver();
        home = new HomePage(driver);
        //navigate to website
        home.open();
        //navigate from home  page to get_paid page.
        home.goToGetPaid();
        getPaidPage=new GetPaidPage(driver);

    }

//test case1: make sure the count of free document are two.
    @Test
    void tcOne()
    {

        List<WebElement> freeDoc= getPaidPage.collectFree();
        //validate numbers of free documents are two
        soft.assertEquals(freeDoc.size(),2,"number of document not equal two");
        //validate all text contain free
        boolean allContainFree = true; // flag to track validation
        for (WebElement f : freeDoc) {
            String text = f.getText();
            if (!text.contains("Free")) {
                allContainFree = false; // mark false if any text does not match
            }
        }
        soft.assertTrue(allContainFree,"some text doesn't contain free");
        soft.assertAll();
    }
    //test case2: get document with price from 30$ to 60$.
    @Test
    void tcTwo()
    {   //get list of document between 30 and 60.
        List<WebElement>priceDoc= getPaidPage.getPrice();
        //validate count
        int count=priceDoc.size();
        boolean flag=true; //flag for validate have document at range
        if(count==0)
        {flag=false;}//false flag if it doesn't have any document at price range(30 to 60)
        soft.assertTrue(flag,"no document at price range(30 to 60)");
        //validate document name
       String docText= getPaidPage.getDocName();
        boolean pflag=false;
            if(docText.contains("Warning"))
            {pflag=true;}
        soft.assertTrue(pflag);
        soft.assertAll();

    }
    @AfterTest
    void close()
    {
        getPaidPage.close();

    }
}
