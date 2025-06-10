package org.asmjava5.uiTest;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class SearchTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://localhost:5173/");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void testValidKeyword() throws IOException, InterruptedException {
        driver.get("http://localhost:5173/product");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Nhập tên sản phẩm...']")
        ));
        searchInput.sendKeys("iphone");

        Thread.sleep(1000);

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String path = "src/test/java/org/asmjava5/img/search_iphone.png";
        File destinationFile = new File(path);
        FileUtils.copyFile(screenshot, destinationFile);

        Assert.assertTrue(destinationFile.exists());
    }

    @Test
    public void testNoResult() throws InterruptedException, IOException {
        driver.get("http://localhost:5173/product");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Nhập tên sản phẩm...']")
        ));
        searchInput.sendKeys("Nồi cơm điện");

        Thread.sleep(1000);

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String path = "src/test/java/org/asmjava5/img/no_result.png";
        File destinationFile = new File(path);
        FileUtils.copyFile(screenshot, destinationFile);

        Assert.assertTrue(destinationFile.exists());
    }

} 