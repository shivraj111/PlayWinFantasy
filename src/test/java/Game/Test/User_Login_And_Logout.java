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
        dataBase = new DB();

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
    public void test4() {
        uiObj = new PlayWinUI();
        uiObj.login(true);
        uiObj.joinContest("MAH VS KAR", 1, 4, 4, 2);
        //uiObj.logout();
    }

    @Test(description = "Date Formater")
    public void test5() {
        System.out.println(DB.SeriesName + DB.LocalTeam +DB.VisitorTeam+DB.MatchDate+DB.MatchNum);
        //test();
    }

public void test()
{
    String a[]={"1","3","5"};
    String b[]=new String[]{"1","3","5"};
    System.out.println(b[2]);
}



}
