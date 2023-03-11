package onlinerTests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FilterTest {

    // проверяем производится ли выборка по одному производителю
    @Test
    public void testSelectTheManufacturer() {
        System.setProperty("webdriver.chrome.driver", "D:\\selenium_java\\ChromeDriver\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://onliner.by");
        driver.findElement(By.xpath("//span[text()='Фены']")).click();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 250)");
        driver.findElement(By.xpath("//input[@value='philips']/following-sibling::span")).click();
        assertTrue(driver.findElement(By.xpath("//input[@value='philips']")).isSelected());
        driver.quit();
    }
    // проверяем производится ли выборка по нескольким производителям
    @Test
    public void testSelectSeveralManufacturers() {
        ArrayList<String> manufacturers = new ArrayList<>();
        manufacturers.add("philips");
        manufacturers.add("dyson");
        manufacturers.add("rowenta");
        manufacturers.add("baByliss");

        System.setProperty("webdriver.chrome.driver", "D:\\selenium_java\\ChromeDriver\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://onliner.by");
        driver.findElement(By.xpath("//span[text()='Фены']")).click();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 250)");
        driver.findElementByXPath("//input[@value='" + manufacturers.get(0) + "']/following-sibling::span").click();
        driver.findElementByXPath("//input[@value='" + manufacturers.get(1) + "']/following-sibling::span").click();
        driver.findElementByXPath("//input[@value='" + manufacturers.get(2) + "']/following-sibling::span").click();

        assertAll(
                () -> assertTrue(driver.findElement(By.xpath("//input[@value='philips']")).isSelected()),
                () -> assertTrue(driver.findElement(By.xpath("//input[@value='dyson']")).isSelected()),
                () -> assertTrue(driver.findElement(By.xpath("//input[@value='rowenta']")).isSelected())
        );
        driver.quit();
    }
// проверяем принадлежат ли все отфильтрованные элементы одному производителю
    @Test
    public void testCheckElements() {
        System.setProperty("webdriver.chrome.driver", "D:\\selenium_java\\ChromeDriver\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://onliner.by");
        driver.findElement(By.xpath("//span[text()='Фены']")).click();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 250)");
        driver.findElement(By.xpath("//input[@value='philips']/following-sibling::span")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<WebElement> elementList = driver.findElements(By.xpath("//div[@class='schema-product__title']/a"));
                for (WebElement e : elementList
        ) {
            assertTrue(e.getText().contains("Philips"));
        }
        driver.quit();
    }
}
