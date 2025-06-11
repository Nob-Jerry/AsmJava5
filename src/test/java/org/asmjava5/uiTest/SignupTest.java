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

public class SignupTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://localhost:5173/signup");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void testValidSignup() {
        driver.get("http://localhost:5173/signup");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Tên đăng nhập']")));
        usernameInput.sendKeys("newuser");

        driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("trantuan88261@gmail.com");
        driver.findElement(By.xpath("//input[@placeholder='Mật khẩu']")).sendKeys("Def456");
        driver.findElement(By.xpath("//input[@placeholder='Nhập lại mật khẩu']")).sendKeys("Def456");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebElement processingBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[contains(text(), 'Đang xử lý')]")
        ));

        Assert.assertTrue(processingBtn.getText().contains("Đang xử lý"));
    }


    @Test
    public void testInvalidEmail() {
        driver.get("http://localhost:5173/signup");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.xpath("//input[@placeholder='Tên đăng nhập']")).sendKeys("newuser1");
        driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("newuser1@email");
        driver.findElement(By.xpath("//input[@placeholder='Mật khẩu']")).sendKeys("StrongPass123");
        driver.findElement(By.xpath("//input[@placeholder='Nhập lại mật khẩu']")).sendKeys("StrongPass123");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'Email không hợp lệ.')]")
        ));

        Assert.assertTrue(error.isDisplayed());
    }

    @Test
    public void testWeakPassword() {
        driver.get("http://localhost:5173/signup");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.xpath("//input[@placeholder='Tên đăng nhập']")).sendKeys("tuan123");
        driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("trantuan@gmail.com");
        driver.findElement(By.xpath("//input[@placeholder='Mật khẩu']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@placeholder='Nhập lại mật khẩu']")).sendKeys("123456");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'Mật khẩu phải trên 6 ký tự')]")
        ));

        Assert.assertTrue(error.isDisplayed());
    }

    @Test
    public void testEmailExists() {
        driver.get("http://localhost:5173/signup");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.xpath("//input[@placeholder='Tên đăng nhập']")).sendKeys("newuser1");
        driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("trantuan88261@gmail.com");
        driver.findElement(By.xpath("//input[@placeholder='Mật khẩu']")).sendKeys("StrongPass123");
        driver.findElement(By.xpath("//input[@placeholder='Nhập lại mật khẩu']")).sendKeys("StrongPass123");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'Email already Exist')]")
        ));
        Assert.assertTrue(error.isDisplayed());
    }
} 