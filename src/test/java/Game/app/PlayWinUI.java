package Game.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    public void joinContest(String UIMatchName, String matchtype, int contestId, String teamName, int WK, int batsMan, int bowler, int allRounder) {

        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()=\"" + UIMatchName + "\"]/../..//parent::div//h4[text()=\"" + matchtype + "\"]/../..//parent::div//button[@class=\"join_btn matchbtn ng-scope\"]"))).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'" + contestId + "')]/../..//parent::div//button[@class=\"btn btn-submit ng-scope\" and text()=\"Join\"]"))).click();
        // webDriverWait.until(ExpectedConditions.presenceOfElementLocated(UiLocators.ContestJoin));
        //webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(UiLocators.ContestJoin)).click();
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
        driver.findElement(By.xpath("//div[@class=\"modal-dialog custom_popup\"]//input[@ng-model=\"team_name\"]")).sendKeys("" + teamName + "");
        Select selCaptain = new Select(driver.findElement(By.xpath("//div[@class=\"modal-dialog custom_popup\"]//select[@name=\"captain\"]")));
        Select selViceCaptain = new Select(driver.findElement(By.xpath("//div[@class=\"modal-dialog custom_popup\"]//select[@name=\"viceCaptain\"]")));
        selCaptain.selectByIndex(1);
        selViceCaptain.selectByIndex(1);
        //Click on Create Team
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\"selectCaptain\" and @class=\"modal fade in\"]//button[@ng-click=\"saveTeam()\" and contains(text(),\"CREATE TEAM\")]"))).click();

        //Click on Confirmation
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\"normal_contest\" and @class=\"modal fade ng-scope in\"]//button[@ng-click=\"close_join_popup('normal_contest')\" and contains(text(),\"Join\")]"))).click();

        //Click on Final Confirmation
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\"selectTeamChips\" and @class=\"modal fade in\"]//button[contains(text(),\"JOIN CONTEST\")]"))).click();

        //Click on Successfully jointed Messge
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\"suss_msg\" and @class=\"modal fade in\"]//div[@class=\"modal-content\"]//button[@class=\"close\"]"))).click();
//done
        /*//Click on  Join button
        //webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()=\""+UIMatchName+"\"]/../..//parent::div//button[@class=\"join_btn ng-scope\"]"))).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()=\"" + UIMatchName + "\"]/../..//parent::div//h4[text()="+matchtype+ "]/../..//parent::div//button[@class=\"join_btn matchbtn ng-scope\"]"))).click();
        //Contest selection page  to load
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class=\"btn btn-submit ng-scope\" and text()=\"Join\"]")));
*/

    }

    public void adminLogin() {
        log.info("Launching Application");
        driver.get(Credential.getAdminUrl());
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS); //Page Load Timeout Set
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //Set 5 Seconds for all UI objects
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='app-cross']//div[@class='submit']")));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("identity"))).sendKeys(Credential.getAdminId());
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).sendKeys(Credential.getAdminpwd());
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).submit();
        //webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).sendKeys(Keys.ENTER);
        //driver.findElement(By.xpath("//div[@class='app-cross']//div[@class='submit']")).click();
        //webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='submit']"))).click();
        log.info("Login Successful");
        //driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS); //Page Load Timeout Set
        //webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Users']")));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav[@class='navbar-default navbar-static-side']//span[text()='Users']"))).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav[@class='navbar-default navbar-static-side']//span[text()='Users Detail']"))).click();


    }

    public void addCash(String userName, String amount) {
        try {
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ibox float-e-margins']")));
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ibox float-e-margins']//input[@type='search']"))).sendKeys(userName);
            Thread.sleep(2000);
            //webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@aria-describedby='users_info']//td[text()='"+userName+"']/../..//parent::div//button[text()='View All ']")));
            //webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@aria-describedby='users_info']//td[text()='" + userName + "']/../..//parent::div//button[text()='View All ']")));
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@aria-describedby='users_info']//td[text()='" + userName + "']/../..//parent::div//button[text()='View All ']"))).click();
            Thread.sleep(2000);
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='dropdown open']")));
            //if(driver.findElement(By.xpath("//div[@class='dropdown open']//a[text()='Add Cash']"))==)
            /*if(driver.findElements(By.xpath("//div[@class='dropdown open']//a[text()='Add Cash']")).size()!=1)
            {
                driver.findElement(By.xpath("//table[@aria-describedby='users_info']//td[text()='" + userName + "']/../..//parent::div//button[text()='View All ']")).click();
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='dropdown open']//a[text()='Add Cash']"))).click();
            }*/
            Thread.sleep(2000);
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='dropdown open']//a[text()='Add Cash']"))).click();
            Thread.sleep(2000);
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='addFormAjax']//input[@name='add_cash']"))).sendKeys(amount);
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='addFormAjax']//textarea[@name='message']"))).sendKeys("AutomatedTest");
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='addFormAjax']//button[@id='submit']"))).click();
            webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='addFormAjax']//button[@id='submit']")));

            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
