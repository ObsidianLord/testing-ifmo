package ru.ifmo.se.lab3;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.frameToBeAvailableAndSwitchToIt;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

public class AddObjectTest extends BaseTest {
    private static final int DEFAULT_TIMEOUT = 5;
    @Test
    public void addObject() {
        for (WebDriver driver : drivers) {
            driver.get("http://wikimapia.org/");
            driver.findElement(By.xpath("//div[10]/div/div/div")).click();
            WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
            wait.until(textToBePresentInElementLocated(By.xpath("//div[3]/div/div/div"), "Создание контура (справка)"));
            assertEquals("Создание контура (справка)", driver.findElement(By.xpath("//div[3]/div/div/div")).getText());
            WebElement element = driver.findElement(By.xpath("//li/a"));
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
            driver.findElement(By.xpath("//li[4]/a/span[2]")).click();
            wait.until(frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@src,'user/denied')]")));
            assertEquals("Для использования этой функции Вам необходимо войти или зарегистрироваться.", driver.findElement(By.xpath("//form/p")).getText());
            driver.switchTo().defaultContent();
            driver.findElement(By.xpath("//div[16]/div/span")).click();
        }
    }
}
