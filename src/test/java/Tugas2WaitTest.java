import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Tugas2WaitTest {

    WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Gagal TC1: Murni Tanpa Wait (NoSuchElementException)")
    public void testCase1_Error() {
        driver.findElement(By.id("add_btn")).click();

        assertThrows(NoSuchElementException.class, () -> {
            driver.findElement(By.xpath("//div[@id='row2']/input"));
        });

//        SS MERAH NO SUCH ELEMENT
//         1. Tambahkan // di depan baris assertThrows sampai }); di atas.
//         2. Hapus // pada baris di bawah ini, lalu Run:

        // driver.findElement(By.xpath("//div[@id='row2']/input"));
    }

    @Test
    @DisplayName("Sukses TC1: Diperbaiki pakai Thread.Sleep (Statis)")
    public void testCase1_Fixed_With_Sleep() throws InterruptedException {
        driver.findElement(By.id("add_btn")).click();

        Thread.sleep(6000); // Menunggu mutlak 6 detik

        WebElement row2Input = driver.findElement(By.xpath("//div[@id='row2']/input"));
        assertTrue(row2Input.isDisplayed());
    }

    @Test
    @DisplayName("Sukses TC1: Diperbaiki pakai Implicit Wait (Global)")
    public void testCase1_Fixed_With_Implicit() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.id("add_btn")).click();

        WebElement row2Input = driver.findElement(By.xpath("//div[@id='row2']/input"));
        assertTrue(row2Input.isDisplayed());
    }

    @Test
    @DisplayName("Sukses TC1: Diperbaiki pakai Explicit Wait (Spesifik)")
    public void testCase1_Fixed_With_Explicit() {
        driver.findElement(By.id("add_btn")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement row2Input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));

        assertTrue(row2Input.isDisplayed());
    }


    @Test
    @DisplayName("Gagal TC5: Timeout Terlalu Cepat (TimeoutException)")
    public void testCase5_Error() {
        driver.findElement(By.id("add_btn")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        assertThrows(TimeoutException.class, () -> {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));
        });

//         SS MERAH TIMEOUT EXCEPTION:
//         1. Tambahkan // di depan baris assertThrows sampai }); di atas.
//          2. Hapus // pada baris di bawah ini, lalu Run:
//
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));
    }
}