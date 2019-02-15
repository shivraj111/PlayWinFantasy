package Game.app;

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
    public static Logger log = LogManager.getLogger("file");
    Connection connection;
    Credential credential = new Credential();

    public DB()
    {

        try
        {
         /*   File file = new File("src\\test\\resources\\credentials.properties");
            FileInputStream fileInput = new FileInputStream(file);
            Properties prop = new Properties();
            prop.load(fileInput);
            fileInput.close();*/
            String server=credential.getServer();
            String databaseName=credential.getDatabaseName();
            String userId=credential.getDbUser();
            String password=credential.getDbpwd();

            //Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbDetails="jdbc:mysql://" + server + "/" + databaseName
                   + "," +userId +","+password;
            log.info("DbDetails "+ dbDetails);
            connection = DriverManager.getConnection("jdbc:mysql://" + server + "/" + databaseName+"?characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL", userId, password);
            //connection= DriverManager.getConnection("jdbc:mysql://172.104.42,120:2083/playwinf_sportz11","playwinf_sportz","i{+W8e4&AfOs");
            log.info("Database Connection Established!");
        }
        catch (Exception e)
        {
            log.error("Database Connection Failed! Message: " + e.getLocalizedMessage());
            org.testng.Assert.fail("Database Connection Failed! Message: " + e.getLocalizedMessage());
        }
    }

    public void close()
    {
        try
        {
            connection.close();
            //log.info("Database Connection Closed!");
        }
        catch (Exception e)
        {
            log.error("Connection could not be closed! Message: " + e.getLocalizedMessage());
        }
    }

    public void query()
    {
        try
        {
            String query = "select * from users ";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
            {
                System.out.println("Query results "+ rs.getString("id"));
            }
        }
        catch (Exception e)
        {
            log.error("Exception Occured while fetching Program Type! Message: " + e.getLocalizedMessage());
            Assert.fail("Exception Occured while fetching Program Type! Message: " + e.getLocalizedMessage());
        }

    }


}
