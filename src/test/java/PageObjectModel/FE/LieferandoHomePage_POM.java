package PageObjectModel.FE;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utilities.TestBase;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LieferandoHomePage_POM extends TestBase {

    WebDriverWait wait = new WebDriverWait(getDriver(),10);
    WebDriver driver = getDriver();
    public void navigateToSearchURL() {
        driver.navigate().to("https://www.lieferando.de/en/");
    }

    public void enterAddress(String address)
    {
        driver.findElement(By.id("combobox-input_78")).sendKeys(address);
    }

    public void chooseFirstSuggestedOption() {
        driver.findElement(By.id("suggestion-list-item_120")).click();
        Assert.assertEquals(driver.getCurrentUrl() , "https://www.lieferando.de/en/delivery/food/berlin-10557");
    }
}


