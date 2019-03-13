package Game.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

/**
 * Created by vash7003 on 1/12/2019.
 */
public class PlayWinUI {

    public static String browser = "Google Chrome";
    public static Logger log = LogManager.getLogger("file");
    //Credential credential = new Credential();
    WebDriver driver;
    WebDriverWait webDriverWait;


    public PlayWinUI() {
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        webDriverWait = new WebDriverWait(driver, 60);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS); //Page Load Timeout Set
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void joinContest(String UIMatchName, int WK, int batsMan, int bowler, int allRounder) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()=\"" + UIMatchName + "\"]/../..//parent::div//button[@class=\"join_btn matchbtn ng-scope\"]"))).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(UiLocators.ContestJoin));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(UiLocators.ContestJoin)).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(UiLocators.CreateTeamPopUp));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(UiLocators.CreateTeamPopUp)).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(UiLocators.PlayerSelectionPage));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),\"WK\")]")));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),\"WK\")]"))).click();

        for (int i = 1; i <= WK; i++) {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//table[@class=\"table table-fixed\"]//tr[@ng-if=\"player.play_role=='WICKETKEEPER'\"]/td/a)[" + i + "]"))).click();
        }
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class=\"table table-fixed\"]//tr[@ng-if=\"player.play_role=='WICKETKEEPER'\" and @class=\"active\"]//td/a")));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),\"Batsmen\")]"))).click();
        for (int i = 1; i <= batsMan; i++) {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//table[@class=\"table-fixed\"]//tr[@ng-if=\"player.play_role=='BATSMAN'\"]/td/a)[" + i + "]"))).click();
        }
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class=\"table-fixed\"]//tr[@ng-if=\"player.play_role=='BATSMAN'\" and @class=\"active\"]//td/a")));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),\"All-Rounders\")]"))).click();
        for (int i = 1; i <= allRounder; i++) {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//table[@class=\"table-fixed\"]//tr[@ng-if=\"player.play_role=='ALLROUNDER'\"]//td/a)[" + i + "]"))).click();
        }
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class=\"table-fixed\"]//tr[@ng-if=\"player.play_role=='ALLROUNDER'\" and @class=\"active\"]//td/a")));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),\"Bowlers\")]"))).click();
        for (int i = 1; i <= bowler; i++) {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//table[@class=\"table-fixed\"]//tr[@ng-if=\"player.play_role=='BOWLER'\"]//td/a)[" + i + "]"))).click();
        }
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class=\"table-fixed\"]//tr[@ng-if=\"player.play_role=='BOWLER'\" and @class=\"active\"]//td/a")));

        //Check Team pop up
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"modal-body clearfix select_captain\"]//button[contains(text(),\"CREATE TEAM\")]")));
        driver.findElement(By.xpath("//div[@class=\"modal-dialog custom_popup\"]//input[@ng-model=\"team_name\"]")).sendKeys("Test1");




    }

    public void login(Boolean UserID) {
        try {
            log.info("Launching Application");
            driver.get(Credential.getUiurl());
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS); //Page Load Timeout Set
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //Set 5 Seconds for all UI objects
            //webDriverWait.until(ExpectedConditions.titleIs("PlaywinFantasy Cricket | Play Real Fantasy Cricket | Play Fantasy Kabaddi | Play Fantasy Football"));
            if (UserID) {
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email"))).sendKeys(Credential.getUserId());
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).sendKeys(Credential.getUserpwd());
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='smallbtn']"))).click();
                log.info("Login Successful");
            } else {
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(UiLocators.googleIcon)).click();
                // Store the current window handle
                String winHandleBefore = driver.getWindowHandle();
                // Perform the click operation that opens new window
                // Switch to new window opened
                for (String winHandle : driver.getWindowHandles()) {
                    driver.switchTo().window(winHandle);
                }
                // Perform the actions on new window
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@aria-label=\"Email or phone\"]"))).sendKeys("111shivraj@gmail.com");
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),\"Next\")]"))).click();
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@aria-label=\"Enter your password\"]"))).sendKeys("Suraj@2211");
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),\"Next\")]"))).click();
                // Switch back to original browser (first window)
                driver.switchTo().window(winHandleBefore);
            }
            //webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(UiLocators.UserOption));
            //span[@class="rgtoggle"]
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"rgtoggle\"]")));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Exception Occured while login: " + e.getLocalizedMessage());
            Assert.fail("Exception Occured while login: " + e.getLocalizedMessage());
        }
    }

    public void invalidLogin() {
        try {
            String actualErrorMsg, expcetedErrorMsg;
            expcetedErrorMsg = "*Invalid Email-id or Password";
            log.info("Launching Application");
            driver.get(Credential.getUiurl());
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS); //Page Load Timeout Set
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //Set 5 Seconds for all UI objects
            //webDriverWait.until(ExpectedConditions.titleIs("PlaywinFantasy Cricket | Play Real Fantasy Cricket | Play Fantasy Kabaddi | Play Fantasy Football"));
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email"))).sendKeys(Credential.getUserId());
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).sendKeys(Credential.getUserpwd() + "5");
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='smallbtn']"))).click();
            actualErrorMsg = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"form-group\"]/div[@class=\"form-error ng-binding ng-scope\"]"))).getText();
            Assert.assertTrue(actualErrorMsg.equals(expcetedErrorMsg), "Error msg mismatch");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Exception Occured while login: " + e.getLocalizedMessage());
            Assert.fail("Exception Occured while login: " + e.getLocalizedMessage());
        }
    }

    public void logout() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(UiLocators.UserOption)).click();
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(UiLocators.logoutOption)).click();
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(UiLocators.LogoutYes)).click();
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(UiLocators.SignInButton));

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Exception Occured while logout! Message: " + e.getLocalizedMessage());
            Assert.fail("Exception Occured while logout! Message: " + e.getLocalizedMessage());
        }
    }


    public void closeApp() {
        try {
            if (!driver.toString().contains("(null)")) {
                log.info("Closing Browser");
                driver.quit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Exception Occured while closing browser! Message: " + e.getLocalizedMessage());
            //Assert.fail("Exception Occured while closing browser! Message: " + e.getLocalizedMessage());
        }
    }
}
