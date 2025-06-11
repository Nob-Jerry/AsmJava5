package org.asmjava5.uiTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://localhost:5173/login");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void testCorrectUsernameAndPassword() {
        driver.navigate().refresh();
        driver.findElement(By.name("username")).sendKeys("jerry");
        driver.findElement(By.name("password")).sendKeys("123456a");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("/"));
    }

    @Test
    public void testCorrectUsernameWrongPassword() {
        driver.navigate().refresh();

        driver.findElement(By.name("username")).sendKeys("jerry");
        driver.findElement(By.name("password")).sendKeys("wrongPass");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".text-red-600")));

        Assert.assertTrue(error.isDisplayed());
    }

    @Test
    public void testWrongUsernameCorrectPassword() {
        driver.navigate().refresh();

        driver.findElement(By.name("username")).sendKeys("wrongUser");
        driver.findElement(By.name("password")).sendKeys("correctPass");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".text-red-600")));

        Assert.assertTrue(error.isDisplayed());
    }
} 