package ru.ifmo.se.lab3;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.URL;
import java.util.HashSet;

public class BaseTest {
    private static final String FIREFOX_DRIVER = "firefox";

    protected final HashSet<WebDriver> drivers = new HashSet<>();

    @Before
    public void createDriver() {
        ClassLoader classLoader = BaseTest.class.getClassLoader();
        URL chromeExecutable = classLoader.getResource("chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", chromeExecutable.getPath());
        URL firefoxExecutable = classLoader.getResource("geckodriver.exe");
        System.setProperty("webdriver.gecko.driver", firefoxExecutable.getPath());
        drivers.add(new ChromeDriver());
        drivers.add(new FirefoxDriver());
    }

    @After
    public void quitDrivers() {
        for (WebDriver driver: drivers) {
            driver.quit();
        }
    }
}
