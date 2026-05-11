import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tugas1ActionTest {

    WebDriver driver;
    Actions action;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Inisiasi action builder
        action = new Actions(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName(" Hover gambar pertama")
    public void testHoverImage() {
        // Navigasi ke web
        driver.get("https://the-internet.herokuapp.com/hovers");

        // mencari gambar pertama
        WebElement firstImage = driver.findElements(By.className("figure")).get(0);

        //  hover ke gambar tersebut
        action.moveToElement(firstImage).perform();

        // Verifikasi teks yang muncul
        WebElement nameText = driver.findElement(By.xpath("(//div[@class='figcaption']/h5)[1]"));
        assertEquals("name: user1", nameText.getText(), "teks saat hover tidak sesuai dengan yang diharapkan!");
    }

    @Test
    @DisplayName("key Presses ")
    public void testKeyPresses() {
        driver.get("https://the-internet.herokuapp.com/key_presses");

        WebElement inputField = driver.findElement(By.id("target"));

        // menekan shift diinput
        action.sendKeys(inputField, Keys.SHIFT).perform();

        // verif teks
        WebElement resultText = driver.findElement(By.id("result"));
        assertEquals("You entered: SHIFT", resultText.getText(), "Deteksi tombol keyboard tidak sesuai!");
    }

    @Test
    @DisplayName(" Drag and Drop")
    public void testDragAndDrop() {
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");

        // mendefinisikan kotak A (sumber) dan kotak B (target)
        WebElement boxA = driver.findElement(By.id("column-a"));
        WebElement boxB = driver.findElement(By.id("column-b"));

        // melakukan aksi drag and drop dari sumber ke target
        action.dragAndDrop(boxA, boxB).perform();

        // memvalidasi kotak B sudah bertukar dengan kotak A
        // jika berhasil header pada elemen dengan ID column-b akan berubah menjadi "A"
        WebElement newBoxBHeader = driver.findElement(By.xpath("//div[@id='column-b']/header"));
        assertEquals("A", newBoxBHeader.getText(), "Kotak gagal bertukar posisi!");
    }
}