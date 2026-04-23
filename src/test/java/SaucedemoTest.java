import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SaucedemoTest {
    WebDriver driver;

    @Test
    public void positif() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        WebElement nama = driver.findElement(By.id("user-name"));
        nama.sendKeys("standard_user");

        WebElement pw = driver.findElement(By.id("password"));
        pw.sendKeys("secret_sauce");

        driver.findElement(By.id("login-button")).submit();
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals("https://www.saucedemo.com/inventory.html", currentUrl);

        driver.quit();
    }

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
}