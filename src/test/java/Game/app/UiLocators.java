package Game.app;

import org.openqa.selenium.By;

/**
 * Created by vash7003 on 1/5/2019.
 */
public class UiLocators {


    //Sigin Page
   // public final static By SignInButton = By.xpath("//div[@class=\"smallbtn_head\"]//a[contains(text(),\"Sign In\" )]");
    public final static By SignInButton = By.xpath("//a[contains(text(),\"Sign Up\" )]");

    //Login page
    public final static By googleIcon = By.xpath("//a[@class=\"btn btn-social btn-google ng-isolate-scope\"]");

    //Home Page
    //public final static By UserOption = By.xpath("//ul[@class=\"hdr_profile hdr_profile1\"]//a[@class=\"dropdown-toggle ng-binding\"]");
    public final static By UserOption = By.xpath("//span[@class=\"rgtoggle\"]");
    //public final static By logoutOption = By.xpath("//ul[@class=\"dropdown-menu\"]//a[@id=\"logout\"]");
    public final static By logoutOption = By.xpath("//a[@id=\"logout\"]");
    public final static By LogoutYes = By.xpath("//div[@class=\"modal-dialog custom_popup\"]//button[contains(text(),\"Yes\")]");
    //public final static By logoutOption = By.xpath("//ul[@class=\"dropdown-menu\"]//a[@id=\"logout\"]");

    //Contest Selection Page
    public final static By ContestJoin = By.xpath("//button[@class=\"btn btn-submit ng-scope\" and text()=\"Join\"]");
    public final static By CreateTeamPopUp = By.xpath("//div[@id=\"check_team\" and @class=\"modal fade in\"]//button[@ng-click=\"createTeam()\" and contains(text(),\"Create Team\")]");
    //xpath=//div[@class="player_tabs creat_sec custom_team"]
    public final static By PlayerSelectionPage = By.xpath("//div[@class=\"player_tabs creat_sec custom_team\"]");
}
