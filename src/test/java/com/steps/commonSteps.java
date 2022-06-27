package com.steps;

import com.baselibrary.Baseclass;
import com.callsapi.RestFunctions;
import com.dataproviderUtilities.ConfigFileReader;
import com.helperUtilities.Database_Utility;
import com.managersUtilities.CommonFunction;
import com.pagesPF.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;

import java.sql.Connection;
import java.sql.SQLException;

import static com.helperUtilities.SQLQueries.*;

public class commonSteps extends Baseclass {
    LoginPage loginPage;
    public ConfigFileReader configFileReader;
    public RestFunctions restFunctions;
    Logger log = LogManager.getLogger(commonSteps.class);

    public commonSteps() {
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        configFileReader = new ConfigFileReader();
        restFunctions = new RestFunctions();

    }

    @Given("^user is on DIL login page$")
    public void userIsOnDILLoginPage() {
        CommonFunction.deleteAllCookies(driver);
        driver.get(configFileReader.getProperties().getProperty("APP_URL"));
        CommonFunction.maximiseBrowser(driver);
        log.info("--Browser Launched--and user is navigated to DIL Login Page");
    }


    @When("^enter username and password$")
    public void enterUsernameAndPassword() throws InterruptedException {
        loginPage.loginToDIL();
        CommonFunction.waitForSomeTime();
        CommonFunction.scrollToElement(driver, loginPage.dontShowbtn);
        loginPage.dontShowbtn.click();
        log.info("--user name and Password are entered--");

    }

    @Given("Creates Database table for {string}")
    public void createsDatabaseTableFor(String db_Driver) throws SQLException {
        boolean create_table = false;
        Database_Utility database_utility = new Database_Utility();
        Connection connection = database_utility.createJDBCConnection(db_Driver);
        Assert.assertNotNull(connection);
        try {
            database_utility.executeSQL(TABLE_VALIDATION_SQL_QUERY, connection);
        } catch (Exception e) {
            create_table = true;
        }
        if (create_table) {
            try {
                if (db_Driver.equalsIgnoreCase("MARIADB")) {
                    database_utility.executeSQL(CREATE_TABLE_SQL_QUERY, connection);
                    database_utility.execute(INSERT_VALUES_SQL_QUERY, connection);
                } else if (db_Driver.equalsIgnoreCase("DB2")) {
                    database_utility.execute(CREATE_TABLE_SQL_QUERY, connection);
                    database_utility.execute(INSERT_VALUES_SQL_QUERY.replaceAll(";", ""), connection);
                } else if (db_Driver.equalsIgnoreCase("ORACLE")) {
                    database_utility.execute(CREATE_TABLE_SQL_QUERY, connection);
                    database_utility.execute(INSERT_VALUES_SQL_QUERY_ORACLE.replaceAll(";", ""), connection);
                } else {
                    log.info("Invalid driver name");
                    throw new SQLException("Invalid driver name");
                }

            } catch (Exception e) {
                e.printStackTrace();

            }
        } else {
            log.info("Table already exist in " + db_Driver + " database");
        }
        connection.close();

    }
}
