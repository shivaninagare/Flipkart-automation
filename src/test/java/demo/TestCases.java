package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.ExpectedExceptions;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import demo.wrappers.Wrappers;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    static ChromeDriver driver;

    @Test
    public void testCase01() throws InterruptedException {
        System.out.println("Begining Test Case 01");

        double starRating = 4.0;
        driver.get("http://www.flipkart.com/");
        Thread.sleep(3000);
        Wrappers.enterTextWrapper(driver, By.xpath("//input[@title='Search for Products, Brands and More']"),
                "Washing Machine");
        Thread.sleep(2000);
        Wrappers.clickOnElementWrapper(driver, By.xpath("//div[contains(text(),'Popularity')]"));
        Boolean status = Wrappers.searchStarRatingAndPrintCount(driver,
                By.xpath("//span[contains(@id,'productRating')]/div"), starRating);
        Assert.assertTrue(status);
        System.out.println("End Test Case 01");

    }
   

    
    // @Test
    // public void testCase02() throws InterruptedException {
    //     System.out.println("Begining Test Case 02");
    //     Boolean status=false;
    //     //int discount = 17;
    //     driver.get("http://www.flipkart.com/");
    //     Thread.sleep(3000);
    //     Wrappers.enterTextWrapper(driver, By.xpath("//input[@title='Search for Products, Brands and More']"), "iphone");
    //     Thread.sleep(3000);
        
    //     status = Wrappers.printTitleAndDiscountIphone(driver, By.xpath("//div[contains(@class,'yKfJKb')]"), 17);
    //     Assert.assertTrue(status,"Failed to print product along with discount");
    //     System.out.println("End Test Case 02");
    // }

    @Test
    public void testCase02() throws InterruptedException {
        System.out.println("Start of testCase02");
        Boolean status = false;
        //wrappers = new Wrappers();
       // wait = new WebDriverWait(driver, Duration.ofSeconds(4l));
        driver.get("https://www.flipkart.com/");
        Thread.sleep(3000);
        
        Wrappers.enterTextWrapper(driver, By.xpath("//input[@title='Search for Products, Brands and More']"), "iPhone");
        // driver.navigate().refresh();
        
        
        Thread.sleep(3000);
        status = Wrappers.validateSearchInput(driver);
        // Boolean status = wrappers.printTitleAndDiscount(driver,
        // By.xpath("//div[contains(@class,'yKfJKb')]"), 15);
        status = Wrappers.printProductAndDiscount(driver, 17);
        System.out.println("Searched product is iPhone " + status);
        System.out.println("End of testCase02");
        //Assert.assertTrue(status, "Failed to print product along with discount");

    }

    // @Test
    // public void testCase03() throws InterruptedException {
    //     System.out.println("Start of testCase03");
    //     //wrappers = new Wrappers();
    //     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4l));
    //     driver.get("https://www.flipkart.com/");
    //     Thread.sleep(3000);
    //     Wrappers.enterTextWrapper(driver, By.xpath("//input[@title='Search for Products, Brands and More']"), "Coffe Mug");        // Thread.sleep(3000);
    //     Thread.sleep(3000);
    //     Wrappers.clickOnElementWrapper(driver, By.xpath("(//div[@class='XqNaEv'])[1]"));
    //     Thread.sleep(3000);
    //     Boolean status = Wrappers.printTopFiveProductTitleAndImageURLWithHighestNumberOfRating(driver,
    //             By.xpath("//div[@class='slAVV4']"));
    //     System.out.println("End of testCase03");
    //     Assert.assertTrue(status, "Failed to print top 5 product with img url based on number of review");

   // }
    @Test
    public static void testCase03() throws InterruptedException{
        System.out.println("Begining Test Case 03");
        driver.get("http://www.flipkart.com/");
        Thread.sleep(3000);
        
        Wrappers.enterTextWrapper(driver, By.xpath("//input[@title='Search for Products, Brands and More']"), "Coffee Mug");
        Thread.sleep(5000);
        Wrappers.clickOnElementWrapper(driver, By.xpath("(//div[@class='XqNaEv'])[1]"));
        Thread.sleep(5000);
         Wrappers.printTitleAndImageUrlOfCoffeeMug(driver, By.xpath("//div[@class='slAVV4']//span[@clas='Wphh3N']"));
        //Assert.assertTrue(status, "Failed to print top 5 product with img url based on number of review");
        System.out.println("End Test Case 03");
    }
    























    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow `testCase01` `testCase02`... format or what is provided in
     * instructions
     */

    /*
     * Do not change the provided methods unless necessary, they will help in
     * automation and assessment
     */
    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest() {
        // driver.close();
        // driver.quit();

    }
}