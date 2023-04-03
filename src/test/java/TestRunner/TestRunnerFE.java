package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import utilities.TestBase;

@CucumberOptions(features = {"src/test/java/FeatureFile/FE"}
                    , glue = {"StepDefinition"}
                    , tags = ("@TestScenario")
, plugin = {
        "pretty","html:test-output/DefaultReport/DefaultReport.html",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
}
        )

public class TestRunnerFE extends TestBase
{
}
