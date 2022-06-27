package com.runnerUtilies;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(strict = true,
        features = {"@target/FailedRerun.txt"},
        glue = {"com/steps"},

        plugin = {"pretty", "html:test-output", "json:target/cucumber.json", "html:target/cucumber.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/FailedRerun.txt"
        },
        tags = "",
        monochrome = true
)

public class FailedRunner {
}
