import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class AvitoTest {

    WebDriver driver;

    @BeforeClass
    public void prepare(){
        System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();

    }

    @Test(priority = 0)
    public void findAndEnterElement(){
        driver.get("https://www.avito.ru/");
        WebElement search = driver.findElement(By.id("search"));
        search.sendKeys("Принтер");
    }

    @Test(priority = 1)
    public void findCityElement() throws InterruptedException {
        WebElement selectCity = driver.findElement(By.xpath("//div[@data-marker='search-form/region']"));
        selectCity.click();
        WebElement fieldCity = driver.findElement(By.xpath("//input[@class='suggest-input-3p8yi']"));
        fieldCity.sendKeys("Владивосток");
        Thread.sleep(700);
        WebElement firstElement = driver.findElement(By.xpath("//li[@data-marker='suggest(0)']"));
        firstElement.click();
        WebElement buttonShow = driver.findElement(By.xpath("//button[@data-marker='popup-location/save-button']"));
        buttonShow.click();
    }


    /**
     * У меня чек бокса именно на авито-доставку почему-то нет(возможно сайт обновился),
     * поэтому я сделал проверку на чек бокс "только с фото"
     */
    @Test(priority = 2)
    public void findAllAds()  {
        WebElement checkBox = driver.findElement(By.xpath("//input[@data-marker='search-form/with-images']/.."));
        if (!checkBox.isSelected()) {
            checkBox.click();
            WebElement allAds = driver.findElement(By.xpath("//button[@data-marker='search-filters/submit-button']"));
            allAds.click();
        }
    }

    @Test(priority = 3)
    public void selectSpinner(){
        WebElement spinner = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[3]/div[1]/div[2]/select"));
        spinner.click();
        WebElement value = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[3]/div[1]/div[2]/select/option[3]"));
        value.click();
    }

    @Test(priority = 4)
    public void printExpensivePrinter(){
        List<WebElement> printersName = driver.findElements(By.xpath("//h3[@class='snippet-title']"));
        List<WebElement> printersPrice = driver.findElements(By.xpath("//div[@class='snippet-price-row']"));

        StringBuilder printer1 = new StringBuilder();
        StringBuilder printer2 = new StringBuilder();
        StringBuilder printer3 = new StringBuilder();

        for (WebElement printer: printersPrice) {
            printer1.append(printersPrice.get(2).findElement(By.xpath("./span[@data-marker='item-price']")).getText());
            printer2.append(printersPrice.get(3).findElement(By.xpath("./span[@data-marker='item-price']")).getText());
            printer3.append(printersPrice.get(4).findElement(By.xpath("./span[@data-marker='item-price']")).getText());
            break;

        }

        for (WebElement printer: printersName) {
            printer1.append(" ").append(printersName.get(2).findElement(By.xpath("./a[@target='_blank']")).getText());
            printer2.append(" ").append(printersName.get(3).findElement(By.xpath("./a[@target='_blank']")).getText());
            printer3.append(" ").append(printersName.get(4).findElement(By.xpath("./a[@target='_blank']")).getText());
            break;
        }

        System.out.println(printer1);
        System.out.println(printer2);
        System.out.println(printer3);

        }

    @AfterTest
    public void clear(){
        driver.quit();
    }

}






