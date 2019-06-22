package Game.Test;

import Game.app.DB;
import Game.app.PlayWinUI;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by vash7003 on 3/21/2019.
 */
public class Admin {
    DB dataBase;
    PlayWinUI uiObj;

    @BeforeClass
    public void before() {
        //dataBase = new DB();

    }


    @AfterMethod
    public void afterTestCase() {
        uiObj.closeApp();
    }

    @Test()
    public void AddCash()
    {
        /*for(int i=12;i<=20;i++) {
            uiObj = new PlayWinUI();
            uiObj.adminLogin();
            uiObj.addCash("test"+i+"@gmail.com", "1000");
            uiObj.closeApp();
        }*/
        uiObj = new PlayWinUI();
        uiObj.adminLogin();
        //for(int i=501;i<=1199;i++) {
        //Adding cash to only one player.
            for(int i=501;i<=1199;i++) {
            Boolean flag=uiObj.addCash("test"+i+"@gmail.com", "1000");
            if(flag==false)
                continue;
            else
            System.out.println("Successfully added cash for user: test"+i );

        }
    }


}
