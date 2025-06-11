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

public class ForgotPasswordTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://localhost:5173/forgot-password");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void testForgotPassword() throws IOException, IOException {
        driver.get("http://localhost:5173/forgot-password");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(5));

        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='email@example.com']")
        ));
        emailInput.sendKeys("trantuan88261@gmail.com");

        WebElement submitBtn = driver.findElement(By.xpath("//button[contains(text(),'Gửi liên kết đặt lại')]"));
        submitBtn.click();

        WebElement successPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'Hãy kiểm tra email để đặt lại mật khẩu')]")
        ));

        Assert.assertTrue(successPopup.isDisplayed());

        WebElement okButton = driver.findElement(By.xpath("//button[text()='OK']"));
        okButton.click();

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File("src/test/java/org/asmjava5/img/forgot_password_success.png");
        FileUtils.copyFile(screenshot, dest);
        Assert.assertTrue(dest.exists());
    }


    @Test
    public void testInvalidEmailInForgotPassword() throws IOException {
        driver.get("http://localhost:5173/forgot-password");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='email@example.com']")
        ));
        emailInput.sendKeys("notfound@email.com");

        WebElement submitBtn = driver.findElement(By.xpath("//button[contains(text(),'Gửi liên kết đặt lại')]"));
        submitBtn.click();

        WebElement errorPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'User is not exist')]")
        ));
        Assert.assertTrue(errorPopup.isDisplayed());

        WebElement okButton = driver.findElement(By.cssSelector("button.swal2-confirm"));
        okButton.click();

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File("src/test/java/org/asmjava5/img/forgot_password_invalid_email.png");
        FileUtils.copyFile(screenshot, dest);
        Assert.assertTrue(dest.exists());
    }
} 