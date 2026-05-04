import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SaucedemoTest {
    WebDriver driver;
//
//    @Test
//    public void positif() {
//        driver = new ChromeDriver();
//        driver.get("https://www.saucedemo.com/");
//
//        WebElement nama = driver.findElement(By.id("user-name"));
//        nama.sendKeys("standard_user");
//
//        WebElement pw = driver.findElement(By.id("password"));
//        pw.sendKeys("secret_sauce");
//
//        driver.findElement(By.id("login-button")).submit();
//        String currentUrl = driver.getCurrentUrl();
//        Assertions.assertEquals("https://www.saucedemo.com/inventory.html", currentUrl);
//
//        driver.quit();
//    }

    @Test
    public void negatif() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("user_ngawur");

//        input Password yang salah
        driver.findElement(By.id("password")).sendKeys("salah_pw");

        driver.findElement(By.id("login-button")).click();

        WebElement pesanError = driver.findElement(By.xpath("//h3[@data-test='error']"));
        String teksError = pesanError.getText();

        Assertions.assertTrue(teksError.contains("Username and password do not match"));

        driver.quit();
    }
    @Test
    public void positif2() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        //menggunakan Locator NAME
        WebElement nama = driver.findElement(By.name("user-name"));
        nama.sendKeys("standard_user");

        //menggunakan Locator XPATH dengan pendekatan AXES
        //Axes: parent:: (naik ke pembungkus) dan following-sibling:: (cari saudara sebelahnya)
        WebElement pw = driver.findElement(By.xpath("//input[@name='user-name']/parent::div/following-sibling::div/input"));
        pw.sendKeys("secret_sauce");

        //menggunakan Locator CLASS
        WebElement btnLogin = driver.findElement(By.className("submit-button"));
        btnLogin.click();

        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals("https://www.saucedemo.com/inventory.html", currentUrl);

        //menggunakan Locator XPATH dengan pendekatan FUNCTION
        WebElement logoElement = driver.findElement(By.xpath("//div[contains(text(), 'Swag Labs')]"));
        Assertions.assertTrue(logoElement.isDisplayed(), "Teks Swag Labs tidak ditemukan!");

        //menggunakan Locator TAG
        WebElement gambarProduk = driver.findElement(By.tagName("img"));
        Assertions.assertTrue(gambarProduk.isDisplayed(), "Gambar produk tidak dirender!");

        driver.quit();
    }
}
