package ru.ifmo.se.lab3;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.frameToBeAvailableAndSwitchToIt;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

public class DetailsTest extends BaseTest {
    private static final long DEFAULT_PAGE_LOAD_WAITING_TIME = 10000L;
    private static final int DEFAULT_TIMEOUT = 5;
    @Test
    public void details() {
        for (WebDriver driver : drivers) {
            driver.get("http://wikimapia.org/");
            driver.manage().window().setSize(new Dimension(1582, 702));
            driver.manage().timeouts().implicitlyWait(DEFAULT_PAGE_LOAD_WAITING_TIME, TimeUnit.MILLISECONDS);
            Actions actions = new Actions(driver);
            WebElement mapTile = driver.findElement(By.xpath("//*[@id='tile-13']"));
            actions.moveByOffset(851, 200).click().perform();
            WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
            wait.until(frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@src,'street')]")));
            assertTrue("наб. Обводного канала (Санкт-Петербург)".equals(driver.findElement(By.xpath("//h1")).getText())
                || "Рижский просп. (Санкт-Петербург)".equals(driver.findElement(By.xpath("//h1")).getText()));
            driver.findElement(By.xpath("//li/a[contains(text(), 'Гараж')]")).click();
            wait.until(textToBePresentInElementLocated(By.xpath("//h1"), "Гаражи"));
            assertEquals("Гаражи (Санкт-Петербург)", driver.findElement(By.xpath("//h1")).getText());
            driver.switchTo().defaultContent();
            driver.findElement(By.xpath("//div[2]/div/span/span")).click();
        }
    }
}
