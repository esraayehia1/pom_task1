package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
WebDriver driver;
public HomePage(WebDriver driver)
{
    this.driver=driver;
}
    public void open()
    {
        driver.get("https://www.levelset.com/");

    }
    public void goToGetPaid()
    {  //pre validate : wait for element appear then double click on element
        //make object from action class to can use doubleClick method.
        //put inside try and catch , if element doesn't appear catch will print message explain that.
       try {
           WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
           WebElement element=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class=\"btn btn-info btn-outline mob-dropdown-btn\"]")));
           Actions actions = new Actions(driver);
           actions.doubleClick((element)).perform();
       } catch (TimeoutException e) {
           System.out.println("get paid button at home page doesn't appear");
           throw new RuntimeException("get Paid button not found", e);
       }

    }

}
