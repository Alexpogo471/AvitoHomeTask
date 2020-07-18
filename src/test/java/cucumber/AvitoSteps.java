package cucumber;

import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

import java.util.List;

public class AvitoSteps {

    WebDriver driver;

    @Before
    public void prepare(){
        System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();

    }

    @Пусть("^открыт ресурс авито")
    public void findAndEnterElement(){
        driver.get("https://www.avito.ru/");
    }

    @ParameterType(".*")
    public Category category(String category){
        return Category.valueOf(category);
    }
    @И("в выпадающем списке категорий выбрана {category}")
    public void selectCategory(Category category){
        driver.findElement(By.id("category")).click();
        driver.findElement(By.xpath(category.value)).click();
    }
    @И("^в поле поиска введено значение (.*)")
    public void inputPrinter(String request) throws InterruptedException {
        Thread.sleep(500);
        WebElement search = driver.findElement(By.id("search"));
        search.sendKeys(request);
    }

    @Тогда("^кликнуть по выпадающему списку региона")
    public void findCityElement()  {
        WebElement selectCity = driver.findElement(By.xpath("//div[@data-marker='search-form/region']"));
        selectCity.click();

    }

    @Тогда("^в поле регион введено значение (.*)")
    public void fieldEnterRegion(String city) throws InterruptedException {
        WebElement fieldCity = driver.findElement(By.xpath("//input[@class='suggest-input-3p8yi']"));
        fieldCity.sendKeys(city);
        Thread.sleep(700);
        WebElement firstElement = driver.findElement(By.xpath("//li[@data-marker='suggest(0)']"));
        firstElement.click();
    }

    @И("нажата кнопка показать объявления")
    public void showAds(){
        WebElement buttonShow = driver.findElement(By.xpath("//button[@data-marker='popup-location/save-button']"));
        buttonShow.click();
    }
    @Тогда("^открылась страница результаты по запросу (.*)")
    public void checkPage(String name){
        String title = driver.findElement(By.xpath("//h1[@data-marker='page-title/text']")).getText();
        if (title.contains(name)){
            System.out.println("Объявления найдены");
        } else {
            throw new NoSuchElementException("Элемента нет");
        }
    }


    @И("^активирован чекбокс только с фотографией")
    public void findAllAds()  {
        WebElement checkBox = driver.findElement(By.xpath("//input[@data-marker='search-form/with-images']/.."));
        if (!checkBox.isSelected()) {
            checkBox.click();
            WebElement allAds = driver.findElement(By.xpath("//button[@data-marker='search-filters/submit-button']"));
            allAds.click();
        }
    }

    @ParameterType(".*")
    public Prise prise(String prise){
        return Prise.valueOf(prise);
    }
    @И("в выпадающем списке сортировка выбрано значение {prise}")
    public void selectSpinner(Prise prise){
        WebElement spinner = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[3]/div[1]/div[2]/select"));
        spinner.click();
        WebElement value = driver.findElement(By.xpath(prise.value));
        value.click();
    }

    @И("^в консоль выведено значение названия и цены (\\d+) первых товаров")
    public void printExpensivePrinter(Integer count){
        List<WebElement> printersName = driver.findElements(By.xpath("//h3[@class='snippet-title']"));
        List<WebElement> printersPrice = driver.findElements(By.xpath("//div[@class='snippet-price-row']"));


        StringBuilder printer1 = new StringBuilder();
        StringBuilder printer2 = new StringBuilder();
        StringBuilder printer3 = new StringBuilder();

            printer1.append(printersPrice.get(2).findElement(By.xpath("./span[@data-marker='item-price']")).getText());
            printer2.append(printersPrice.get(3).findElement(By.xpath("./span[@data-marker='item-price']")).getText());
            printer3.append(printersPrice.get(4).findElement(By.xpath("./span[@data-marker='item-price']")).getText());

            printer1.append(" ").append(printersName.get(2).findElement(By.xpath("./a[@target='_blank']")).getText());
            printer2.append(" ").append(printersName.get(3).findElement(By.xpath("./a[@target='_blank']")).getText());
            printer3.append(" ").append(printersName.get(4).findElement(By.xpath("./a[@target='_blank']")).getText());

        if (count == 3){
            System.out.println(printer1);
            System.out.println(printer2);
            System.out.println(printer3);
        }


        }

    @AfterTest
    public void clear(){
        driver.quit();
    }

}






