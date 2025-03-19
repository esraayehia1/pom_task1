package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class GetPaidPage {
    WebDriver driver;

    public GetPaidPage(WebDriver driver)
    {
        this.driver=driver;
    }

    //function for testcase1
    public List<WebElement> collectFree()
    {  String locFree="//span[@class=\"price-amount\" and contains(text(),'%s')]";
        try{
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> free=driver.findElements(By.xpath(String.format(locFree,"Free")));
        return free;
    }
    catch (TimeoutException e)
    {
        throw new RuntimeException(" can't locate element ", e);

    }


    }
    //function for testcase2
    public List<WebElement>getPrice()
    { //String locPaid="//span[@class=\"price-amount\" and number(translate(text(),\"$\",\"\")) >= 30 and number(translate(text(),\"$\",\"\")) <= 60]";
        String locPaid="//span[@class=\"price-amount\" and number(translate(text(),\"$\",\"\")) >= %d and number(translate(text(),\"$\",\"\")) <= %d]";

            try
    {     WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(locPaid,30,60))));
        List<WebElement> price=driver.findElements(By.xpath(String.format(locPaid,30,60)));
        return price;
    }
    catch (TimeoutException e)
    {
        throw new RuntimeException(" can't locate element ", e);

    }

    }
    //function for testcase2 validate document name
   public String  getDocName()
   {        String locName="//span[@class=\"price-amount\" and number(translate(text(),\"$\",\"\")) >= %d and number(translate(text(),\"$\",\"\")) <= %d]//parent::div//parent::div/div[@class=\"left\"]";
       String text=driver.findElement(By.xpath(String.format(locName,30,60))).getText();
       return text;
   }
    public void close()
    {
        driver.quit();
    }
}
