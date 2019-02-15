package Game.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by vash7003 on 1/15/2019.
 */
public class Credential {
    public static Logger log = LogManager.getLogger("file");
    private static Properties properties = getData();
    public final static String dbUser = properties.getProperty("dbUser");
    public final static String dbpwd = properties.getProperty("dbpwd");
    public final static String server = properties.getProperty("server");
    public final static String databaseName = properties.getProperty("databaseName");
    public final static String userId = properties.getProperty("userId");
    public final static String userpwd = properties.getProperty("userpwd");
    public final static String adminId = properties.getProperty("adminId");
    public final static String adminpwd = properties.getProperty("adminpwd");
    public final static String uiurl = properties.getProperty("uiurl");


    private static Properties getData() {
        Properties properties = null;
        String propFileName = "src\\test\\resources\\credentials.properties";
        try {
            FileInputStream inputStream = new FileInputStream(new File(propFileName));
            properties = new Properties();
            if (inputStream != null) {
                properties.load(inputStream);
                inputStream.close();

            } else {
                log.error("Failed to load property file");
            }
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return properties;
    }

    public static String getDbUser() {
        return dbUser;
    }

    public static String getDbpwd() {
        return dbpwd;
    }

    public static String getUserpwd() {
        return userpwd;
    }

    public static String getAdminId() {
        return adminId;
    }

    public static String getAdminpwd() {
        return adminpwd;
    }

    public static String getUiurl() {
        return uiurl+"#/login";
    }

    public static String getServer() {
        return server;
    }

    public static String getDatabaseName() {
        return databaseName;
    }

    public static String getUserId() {
        return userId;
    }


}
