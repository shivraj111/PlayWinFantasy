package Game.app;

import Game.function.functionLiberary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by vash7003 on 1/5/2019.
 */
public class DB {
    public static String SeriesName, LocalTeam, VisitorTeam, MatchDate, MatchNum, LocalTeamShortName, VisitorTeamShortName;
    public static Logger log = LogManager.getLogger("file");
    Connection connection;
    Credential credential = new Credential();
    functionLiberary function;


    public DB() {

        try {

            function = new functionLiberary();
         /*   File file = new File("src\\test\\resources\\credentials.properties");
            FileInputStream fileInput = new FileInputStream(file);
            Properties prop = new Properties();
            prop.load(fileInput);
            fileInput.close();*/
            String server = credential.getServer();
            String databaseName = credential.getDatabaseName();
            String userId = credential.getDbUser();
            String password = credential.getDbpwd();

            //Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbDetails = "jdbc:mysql://" + server + "/" + databaseName
                    + "," + userId + "," + password;
            log.info("DbDetails " + dbDetails);
            connection = DriverManager.getConnection("jdbc:mysql://" + server + "/" + databaseName + "?characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL", userId, password);
            //connection= DriverManager.getConnection("jdbc:mysql://172.104.42,120:2083/playwinf_sportz11","playwinf_sportz","i{+W8e4&AfOs");
            log.info("Database Connection Established!");
            testData();
        } catch (Exception e) {
            log.error("Database Connection Failed! Message: " + e.getLocalizedMessage());
            org.testng.Assert.fail("Database Connection Failed! Message: " + e.getLocalizedMessage());
        }
    }

    public void close() {
        try {
            connection.close();
            //log.info("Database Connection Closed!");
        } catch (Exception e) {
            log.error("Connection could not be closed! Message: " + e.getLocalizedMessage());
        }
    }

    public Object[] shortName(String teamName) {

        String team_short_name, team_name;
        team_short_name = "";
        String query = "SELECT team_name,team_short_name FROM team WHERE team_name LIKE \"" + teamName + "\"";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                team_name = rs.getString("team_name");
                team_short_name = rs.getString("team_short_name");

            }

        } catch (Exception e)
        {
            log.error("Exception Occured while fetching testData! Message: " + e.getLocalizedMessage());
            Assert.fail("Exception Occured while fetching testData ! Message: " + e.getLocalizedMessage());

        }
        if (team_short_name.length() > 3 || team_short_name.length() == 0) {
            //Object[] obj= new Object[2];
            Object[] obj = {false, null};
            return obj;
        } else {
            Object[] obj = {true, team_short_name};
            return obj;
        }
    }

    public void testData() {
        try {
            String query = "SELECT match_date,match_time,match_num,localteam_id,localteam,visitorteam_id,visitorteam,s.name AS 'Series Name' FROM matches m,series s WHERE sid=series_id AND m.play_status=b'1' AND STATUS='open' AND match_date BETWEEN '" + function.dateFormatter(1) + "' AND '" + function.dateFormatter(3) + "' ORDER BY match_date limit 5;";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);


            /*while (rs.next()) {
                MatchDate = rs.getString("match_date");
                MatchNum = rs.getString("match_num");
                LocalTeam = rs.getString("localteam");
                VisitorTeam = rs.getString("visitorteam");
                SeriesName = rs.getString("Series Name");
                Object obj = shortName(LocalTeam);
                Boolean b = obj[0];
                if (shortName(LocalTeam) == true || shortName(VisitorTeam) == true) {
                    LocalTeamShortName =
                    break;
                }
            }*/


        } catch (Exception e) {
            log.error("Exception Occured while fetching testData! Message: " + e.getLocalizedMessage());
            Assert.fail("Exception Occured while fetching testData ! Message: " + e.getLocalizedMessage());

        }


    }


    public void query() {
        try {
            String query = "select * from users ";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println("Query results " + rs.getString("id"));
            }
        } catch (Exception e) {
            log.error("Exception Occured while fetching Program Type! Message: " + e.getLocalizedMessage());
            Assert.fail("Exception Occured while fetching Program Type! Message: " + e.getLocalizedMessage());
        }

    }


}
