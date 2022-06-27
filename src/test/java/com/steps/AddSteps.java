package com.steps;

import com.baselibrary.Baseclass;
import com.dataproviderUtilities.ConfigFileReader;
import com.helperUtilities.Constant;
import com.managersUtilities.CommonFunction;
import com.pagesPF.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author - Arun Kaser
 */

public class AddSteps extends Baseclass {
    public ConfigFileReader configFileReader;
    CalculationPage calculationPage;
    Logger log = LogManager.getLogger(AddSteps.class);
    public static Constant constant = new Constant();

    public AddSteps() {

        calculationPage = PageFactory.initElements(driver, CalculationPage.class);
        configFileReader = new ConfigFileReader();

    }

   // Create Steps defs here
}