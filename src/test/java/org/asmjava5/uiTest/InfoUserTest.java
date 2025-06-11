package org.asmjava5.uiTest;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class InfoUserTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://localhost:5173/detail");
    }
    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void changeNameInfoUser() throws InterruptedException, IOException {
        driver.navigate().refresh();

        driver.findElement(By.name("username")).sendKeys("jerry");
        driver.findElement(By.name("password")).sendKeys("1");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("swal2-popup")
        ));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("swal2-popup")
        ));

        WebElement detailLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a[href='/detail']")
        ));
        detailLink.click();

        WebElement fullnameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("fullname")
        ));
        fullnameInput.clear();
        fullnameInput.sendKeys("Trần Hoàng Tuấn");

        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='submit' and contains(text(),'Lưu thay đổi')]")
        ));
        saveButton.click();

        WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Lưu']")
        ));
        confirmButton.click();

        WebElement alertText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Đã lưu thay đổi')]")
        ));
        Assert.assertTrue(alertText.isDisplayed());

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(), 'Lưu thành công!')]")
        ));
        Assert.assertTrue(successMessage.isDisplayed());

        Thread.sleep(1000);

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String path = "src/test/java/org/asmjava5/img/update_name.png";
        File destinationFile = new File(path);
        FileUtils.copyFile(screenshot, destinationFile);

        Assert.assertTrue(destinationFile.exists());
    }

    @Test
    public void changeInvalidPhone() throws InterruptedException, IOException {
        driver.navigate().refresh();

        driver.findElement(By.name("username")).sendKeys("jerry");
        driver.findElement(By.name("password")).sendKeys("1");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("swal2-popup")
        ));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("swal2-popup")
        ));

        WebElement detailLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a[href='/detail']")
        ));
        detailLink.click();

        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("phone")
        ));
        phoneInput.clear();
        phoneInput.sendKeys("0123");

        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='submit' and contains(text(),'Lưu thay đổi')]")
        ));
        saveButton.click();

        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'Số điện thoại phải bắt đầu bằng 0')]")
        ));
        Assert.assertTrue(errorMsg.isDisplayed());

        Thread.sleep(1000);

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String path = "src/test/java/org/asmjava5/img/update_invalidphone.png";
        File destinationFile = new File(path);
        FileUtils.copyFile(screenshot, destinationFile);

        Assert.assertTrue(destinationFile.exists());
    }
}
