import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {

    public WebDriver driver;
    public String testURL = "https://www.getyourguide.com";

    @BeforeTest
    public void setupTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to(testURL);
    }

    @Test(priority = 1, dependsOnMethods = "RegistrationTest.userRegistrationTest")
    public void userLoginTest() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement profileButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main-content\"]/section[1]/header/div/nav/ul/li[5]/a")));
        profileButton.click();

        WebElement loginOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main-content\"]/section[1]/header/div/nav/ul/li[5]/div/div/div/div/form/section/ul/li[1]/a")));
        loginOption.click();

        WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("lookupEmail")));
        emailField.sendKeys("testuser2@example.com");
        WebElement continueWithEmailButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main-content\"]/div[1]/div/main/div/div/section/form/button")));
        continueWithEmailButton.click();

        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("form[data-ref='signup'] input[data-test-id='password']")));
        passwordField.sendKeys("TestPassword2!");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main-content\"]/div[1]/div/main/div/div/div/form/button")));
        loginButton.click();


        Thread.sleep(5000);
        if (profileButton.isDisplayed()) {
            profileButton.click();
            WebElement logOutOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main-content\"]/section[1]/header/div/nav/ul/li[6]/div/div/div/div/form/section/ul/li[11]/a")));

            if (logOutOption.isDisplayed()) {
                System.out.println("Prijava je uspješna.");
            } else {
                System.out.println("Prijava je neuspješna.");
            }
        } else {
            System.out.println("Prijava je neuspješna.");
        }

        WebElement exitProfile = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("nav[class='modal-navigation'] span[class='c-icon ic-close']")));
        exitProfile.click();
    }

    @Test(priority = 2, dependsOnMethods = "userLoginTest")
    public void searchTest() {
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        WebElement searchTextBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));

        if (searchTextBox.isDisplayed() && searchTextBox.isEnabled()) {
            searchTextBox.sendKeys("Paris", Keys.RETURN);

            By resultsLocator = By.xpath("//h3[contains(text(),'Paris')]");
            WebElement testLink = wait.until(ExpectedConditions.presenceOfElementLocated(resultsLocator));

            Assert.assertTrue(testLink.getText().toLowerCase().contains("paris"));
            System.out.println("Search result: " + testLink.getText());

        } else {
            System.out.println("Search box not found on the page.");
        }
    }

    @Test(priority = 3, dependsOnMethods = "searchTest")
    public void wishListTest() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        By resultsLocator = By.cssSelector("div[class='vertical-activity-card__wishlist'] span[class='wishlist-icon__heart']");
        WebElement heartIcon = wait.until(ExpectedConditions.presenceOfElementLocated(resultsLocator));
        heartIcon.click();
        WebElement createList = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-test-id='create-wishlist-group-button']")));
        createList.click();
        WebElement submitList = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-test-id='list-wishlist-submit']")));
        submitList.click();
    }

    @Test(priority = 4,dependsOnMethods = "wishListTest")
    public void addToCartTest() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        By resultsLocator = By.xpath("//h3[contains(text(),'Paris')]");
        WebElement testLink = wait.until(ExpectedConditions.presenceOfElementLocated(resultsLocator));
        Thread.sleep(5000);
        testLink.click();

        String originalWindowHandle = driver.getWindowHandle();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        Thread.sleep(10000);
        WebElement dateIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("section[class='ba-dropdown ba-date-picker'] span[class='c-icon ba-date-picker__icon']")));
        dateIcon.click();
        Thread.sleep(2000);
        WebElement chosenDate = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"booking-assistant\"]/div[1]/section[2]/section[2]/section/section/section[1]/div/div[2]/div/div[2]/div[2]/span[33]")));
        chosenDate.click();
        WebElement availabilityButton = wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//*[@id=\"booking-assistant\"]/div[1]/button"))));
        availabilityButton.click();
        WebElement addToCart = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("details[class='activity-option__wrapper activity-option__wrapper--toggled'] button[data-test-id='add-to-cart-button']")));
        Thread.sleep(2000);
        addToCart.click();
        Thread.sleep(5000);
        WebElement checkCart = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"main-content\"]/div/div/section/div/div[1]/h2")));
        if(checkCart.isDisplayed()){
            System.out.println("Added to cart successfully!");
        }
        else{
            System.out.println("Failed to add to cart!");
        }
    }


    @Test(priority = 5, dependsOnMethods = "addToCartTest")
    public void logOutTest() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement logOutOption = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("nav[data-test-id='page-header-nav'] a[title='Profile']")));
        logOutOption.click();
    }

    @AfterTest
    public void teardownTest() {
        if (driver != null) {
            driver.quit();
        }
    }
}
