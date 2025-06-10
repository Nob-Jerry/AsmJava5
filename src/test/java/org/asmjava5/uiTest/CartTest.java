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
import java.util.List;

public class CartTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://localhost:5173/cart");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void testViewCartWithProduct() {
        Assert.assertTrue(driver.getPageSource().contains("Sản phẩm"));
    }

    @Test
    public void testUpdateProductQuantity() {
        driver.navigate().refresh();

        driver.findElement(By.name("username")).sendKeys("jerry");
        driver.findElement(By.name("password")).sendKeys("123456a");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/"));

        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[aria-label='Giỏ hàng']")
        )).click();

        wait.until(ExpectedConditions.urlContains("/cart"));

        List<WebElement> buttons = driver.findElements(By.cssSelector("div.flex button.bg-gray-200"));
        WebElement plusBtn = buttons.get(1);
        plusBtn.click();


        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'Cập nhật thành công')]")
        ));
        Assert.assertTrue(toast.isDisplayed());
    }


    @Test
    public void testRemoveProductFromCart() {
        driver.navigate().refresh();

        driver.findElement(By.name("username")).sendKeys("jerry");
        driver.findElement(By.name("password")).sendKeys("123456a");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/"));

        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[aria-label='Giỏ hàng']")
        )).click();

        wait.until(ExpectedConditions.urlContains("/cart"));

        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("input[type='checkbox'].text-sky-600")
        )).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[text()='Xóa sản phẩm đã chọn']")
        )).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button.swal2-confirm")
        )).click();

        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'Xóa sản phẩm thành công')]")
        ));
        Assert.assertTrue(toast.isDisplayed());
    }


    @Test
    public void testEmptyCart() {
        driver.navigate().refresh();

        driver.findElement(By.name("username")).sendKeys("jerry");
        driver.findElement(By.name("password")).sendKeys("123456a");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/"));

        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[aria-label='Giỏ hàng']")
        )).click();

        wait.until(ExpectedConditions.urlContains("/cart"));

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[text()='Chọn tất cả']")
        )).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[text()='Xóa sản phẩm đã chọn']")
        )).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button.swal2-confirm")
        )).click();

        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'Xóa sản phẩm thành công')]")
        ));
        Assert.assertTrue(driver.getPageSource().contains("Giỏ hàng trống."));
    }
} 