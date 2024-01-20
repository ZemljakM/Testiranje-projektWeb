import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class RegistrationTest {

    public WebDriver driver;
    public String testURL = "https://www.getyourguide.com";

    @BeforeMethod
    public void setupTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to(testURL);
    }

    @Test
    public void userRegistrationTest() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement profileButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main-content\"]/section[1]/header/div/nav/ul/li[5]/a")));
        profileButton.click();

        WebElement registerOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main-content\"]/section[1]/header/div/nav/ul/li[5]/div/div/div/div/form/section/ul/li[1]/a")));
        registerOption.click();

        WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("lookupEmail")));
        emailField.sendKeys("testuser2@example.com");
        WebElement continueWithEmailButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main-content\"]/div[1]/div/main/div/div/section/form/button")));
        continueWithEmailButton.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement fullNameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("full-name")));
        fullNameField.sendKeys("Test User2");
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("new-password")));
        passwordField.sendKeys("TestPassword2!");

        WebElement createAccountButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main-content\"]/div[1]/div/main/div/div/div/form/button")));
        createAccountButton.click();


        Thread.sleep(10000);
        if (profileButton.isDisplayed()) {
            profileButton.click();
            WebElement logOutOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main-content\"]/section[1]/header/div/nav/ul/li[6]/div/div/div/div/form/section/ul/li[11]/a")));

            if (logOutOption.isDisplayed()) {
                System.out.println("Registracija je uspješna.");
            } else {
                System.out.println("Registracija je neuspješna.");
            }
        } else {
            System.out.println("Registracija je neuspješna.");
        }
    }

    @AfterMethod
    public void teardownTest() {
        driver.quit();
    }
}
