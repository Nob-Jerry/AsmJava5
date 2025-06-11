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
    public void addProductToCart() throws InterruptedException, IOException {
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
        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Sản phẩm")
        )).click();
        wait.until(ExpectedConditions.urlContains("/product"));
        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[.//text()[contains(., 'Thêm vào giỏ')]]")
        ));
        addToCartBtn.click();
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'Đã thêm vào giỏ hàng')]")
        ));
        Assert.assertTrue(toast.isDisplayed());
        WebElement cartBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[aria-label='Giỏ hàng']")
        ));
        cartBtn.click();
        wait.until(ExpectedConditions.urlContains("/cart"));
        Thread.sleep(1000);

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String path = "src/test/java/org/asmjava5/img/addProductToCart_iphone.png";
        File destinationFile = new File(path);
        FileUtils.copyFile(screenshot, destinationFile);

        Assert.assertTrue(destinationFile.exists());
    }

    @Test
    public void testUpdateProductQuantity() {
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

        WebElement cartBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[aria-label='Giỏ hàng']")
        ));
        cartBtn.click();

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
    public void testRemoveProductFromCart() throws InterruptedException, IOException {
        driver.navigate().refresh();

        driver.findElement(By.name("username")).sendKeys("jerry");
        driver.findElement(By.name("password")).sendKeys("123456a");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("swal2-popup")
        ));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("swal2-popup")
        ));

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

        Thread.sleep(1000);

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String path = "src/test/java/org/asmjava5/img/update_iphone.png";
        File destinationFile = new File(path);
        FileUtils.copyFile(screenshot, destinationFile);

        Assert.assertTrue(destinationFile.exists());
    }
    @Test
    public void testRemoveAllProductFromCart() throws InterruptedException, IOException {
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

        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[aria-label='Giỏ hàng']")
        )).click();

        wait.until(ExpectedConditions.urlContains("/cart"));

        WebElement selectAllBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Chọn tất cả']")
        ));
        selectAllBtn.click();

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

        Thread.sleep(1000);

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String path = "src/test/java/org/asmjava5/img/update_iphone.png";
        File destinationFile = new File(path);
        FileUtils.copyFile(screenshot, destinationFile);

        Assert.assertTrue(destinationFile.exists());
    }


    @Test
    public void testEmptyCart() {
        driver.navigate().refresh();

        driver.findElement(By.name("username")).sendKeys("jerry");
        driver.findElement(By.name("password")).sendKeys("123456a");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("swal2-popup")
        ));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("swal2-popup")
        ));

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