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
    {   try{
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"price-amount\" and contains(text(),\"Free\")]")));
        List<WebElement> free=driver.findElements(By.xpath("//span[@class=\"price-amount\" and contains(text(),\"Free\")]"));
        return free;
    }
    catch (TimeoutException e)
    {
        throw new RuntimeException(" can't locate element ", e);

    }


    }
    //function for testcase2
    public List<WebElement>getPrice()
    { try
    {     WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"price-amount\" and number(translate(text(),\"$\",\"\")) >= 30 and number(translate(text(),\"$\",\"\")) <= 60]")));
        List<WebElement> price=driver.findElements(By.xpath("//span[@class=\"price-amount\" and number(translate(text(),\"$\",\"\")) >= 30 and number(translate(text(),\"$\",\"\")) <= 60]"));
        return price;
    }
    catch (TimeoutException e)
    {
        throw new RuntimeException(" can't locate element ", e);

    }

    }
    //function for testcase2 validate document name
   public String  getDocName()
   {
       String text=driver.findElement(By.xpath("//span[@class=\"price-amount\" and number(translate(text(),\"$\",\"\")) >= 30 and number(translate(text(),\"$\",\"\")) <= 60]//parent::div//parent::div/div[@class=\"left\"]")).getText();
       return text;
   }
    public void close()
    {
        driver.quit();
    }
}
