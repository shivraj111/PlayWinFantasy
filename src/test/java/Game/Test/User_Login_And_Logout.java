package Game.Test;

import Game.app.DB;
import Game.app.PlayWinUI;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by vash7003 on 1/5/2019.
 */
public class User_Login_And_Logout {
    DB dataBase;
    PlayWinUI uiObj;

    @BeforeClass
    public void before() {
        //dataBase = new DB();

    }


    @AfterMethod
    public void afterTestCase() {
        //uiObj.closeApp();
    }


    @Test(description = "1. Login with invalid credentials")
    public void test1() {
        uiObj = new PlayWinUI();
        uiObj.invalidLogin();
    }


    @Test(description = "2. Login and logout with email id")
    public void test2() {
        uiObj = new PlayWinUI();
        uiObj.login(true);
        uiObj.logout();
    }

    @Test(description = "3. Login and Logout with google account")
    public void test3() {
        uiObj = new PlayWinUI();
        uiObj.login(false);
        uiObj.logout();
    }

    @Test(description = "4. Join Public Contest")
    public void test4() throws InterruptedException {
        DB dbObj = new DB();
        uiObj = new PlayWinUI();
        uiObj.login(true);
        uiObj.joinContest("SA VS SL", "t20-1st T20I", dbObj.getAssignedContestid("AutomatedContest019"), "Test019", 1, 4, 4, 2);

        //uiObj.logout();
    }

    @Test(description = "Multiple user joint multiple contest")
    public void test6() throws InterruptedException {
        Boolean loginFlag, contestFlag;
        //DB dbObj= new DB();
        for (int i = 429; i <= 1199; i++) {
            uiObj = new PlayWinUI();
            loginFlag = uiObj.multipletLogin("Test" + i + "@gmail.com", "Test@" + i);
            if (loginFlag == false) {
                uiObj.closeApp();
                continue;
            }
            //uiObj.joinContest("SLW VS EW", "t20-1st T20I", dbObj.getAssignedContestid("Win 50000 /Team 5000 /Pay 11.5"), "Test019", 1, 4, 4, 2);
            //SLW VS EW approx 460 Contest jointed
            else {
                contestFlag = uiObj.joinContest("SA VS SL", "t20-3rd T20I",    17, "Test1K01", 1, 4, 4, 2);
                if (contestFlag = false) {
                    uiObj.closeApp();
                    continue;
                }
                uiObj.closeApp();
                System.out.println("User Successfully jointed contest "+ "Test" + i + "@gmail.com");
            }
        }
        //uiObj.joinContest("SA VS SL","t20-1st T20I" , dbObj.getAssignedContestid("AutomatedContest019"),"Test019",1, 4, 4, 2);
        //uiObj.logout();
    }


    @Test(description = "Date Formater")
    public void test5() {
        System.out.println(DB.SeriesName + DB.LocalTeam + DB.VisitorTeam + DB.MatchDate + DB.MatchNum);
        //test();
    }

    public void test() {
        String a[] = {"1", "3", "5"};
        String b[] = new String[]{"1", "3", "5"};
        System.out.println(b[2]);
    }


}
