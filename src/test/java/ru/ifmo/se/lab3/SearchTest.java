package ru.ifmo.se.lab3;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class SearchTest extends BaseTest {
    private static final int DEFAULT_TIMEOUT = 5;
    @Test
    public void search() {
        for (WebDriver driver : drivers) {
            driver.get("http://wikimapia.org/");
            driver.findElement(By.xpath("//div/input")).click();
            driver.findElement(By.xpath("//div/ul/li[6]")).click();
            WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
            wait.until(textToBePresentInElementLocated(By.xpath("//a"), "высшее учебное заведение (вуз)"));
            assertEquals("высшее учебное заведение (вуз)", driver.findElement(By.xpath("//a")).getText());
            driver.findElement(By.xpath("//form/div/input")).click();
            driver.findElement(By.id("search-input")).sendKeys("итмо");
            driver.findElement(By.xpath("//button/i")).click();
            wait.until(frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@id,'searchFrame')]")));
            wait.until(presenceOfAllElementsLocatedBy(By.xpath("//li/div/strong")));
            assertNotNull(driver.findElement(By.xpath("//li/div/strong[text() = 'Университет ИТМО']")));
            driver.switchTo().defaultContent();
            driver.findElement(By.xpath("//div[2]/div/span/span")).click();
        }
    }
}
