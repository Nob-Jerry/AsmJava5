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

public class ChangePasswordTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://localhost:5173/change-password");
    }
    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void changePasswordSuccess() throws InterruptedException, IOException {
        driver.navigate().refresh();

        driver.findElement(By.name("username")).sendKeys("jerry");
        driver.findElement(By.name("password")).sendKeys("012345a");
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

        WebElement changePwdBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Đổi mật khẩu')]")
        ));
        changePwdBtn.click();

        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Tên đăng nhập']")
        ));
        usernameInput.sendKeys("jerry");

        WebElement oldPwdInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Mật khẩu cũ']")
        ));
        oldPwdInput.sendKeys("012345a");

        WebElement newPwdInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Mật khẩu mới']")
        ));
        newPwdInput.sendKeys("123456a");

        driver.findElement(By.xpath("//input[@placeholder='Nhập lại mật khẩu mới']")).sendKeys("123456a");

        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='submit' and contains(text(), 'Đổi mật khẩu')]")
        ));
        submitBtn.click();
        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[contains(text(), 'Password updated successfully')]")
        ));
        Assert.assertTrue(successMsg.isDisplayed());
        Thread.sleep(1000);

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String path = "src/test/java/org/asmjava5/img/change_password-success.png";
        File destinationFile = new File(path);
        FileUtils.copyFile(screenshot, destinationFile);

        Assert.assertTrue(destinationFile.exists());
    }
}
