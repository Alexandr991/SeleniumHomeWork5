package aplication;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;


public class MainClass {
    public static void main(String[] args) {


        System.setProperty("webdriver.chrome.driver", "D:\\selenium_java\\ChromeDriver\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://onliner.by");
        driver.findElement(By.xpath("//span[text()='Фены']")).click();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 250)");
        driver.findElement(By.xpath("//input[@value='philips']/following-sibling::span")).click();

        if (driver.findElement(By.xpath("//input[@value='philips']")).isSelected()) {
            System.out.println("Still selected");
        }
        driver.quit();
    }
}



