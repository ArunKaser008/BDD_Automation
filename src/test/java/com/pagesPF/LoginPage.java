package com.pagesPF;

import com.dataproviderUtilities.ConfigFileReader;
import com.managersUtilities.CommonFunction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class LoginPage {

    public ConfigFileReader configFileReader;
    WebDriver driver;
    Logger log = LogManager.getLogger(LoginPage.class);
    @FindBy(how = How.XPATH, using = "//*[text()='User name']")
    WebElement userName;
    @FindBy(how = How.XPATH, using = "//*[text()='Password']")
    WebElement Password;
    @FindBy(how = How.XPATH, using = "//*[normalize-space()='Login']")
    WebElement loginButton;

    @FindBy(how = How.XPATH, using = "//*[text()='skip']")
    public WebElement dontShowbtn;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        configFileReader = new ConfigFileReader();

    }

    public void loginToDIL() {

        userName.sendKeys(configFileReader.getProperties().getProperty("APP_USERNAME"));
        CommonFunction.waitForElementToAppear(driver, Password);
        Password.sendKeys(configFileReader.getProperties().getProperty("APP_PASSWORD"));
        CommonFunction.waitForElementToAppear(driver, loginButton);
        CommonFunction.clickForceFully(driver, loginButton);
        log.info("User is logged into UI with userId-->" + configFileReader.getProperties().getProperty("APP_USERNAME"));

    }

}
