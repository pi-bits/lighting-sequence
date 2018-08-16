package org.serviceinfotech.acceptance;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/AlternatingSequencing.feature",
        glue = "org.serviceinfotech.steps")
public class AlternatingAlgorithmAcceptanceTests {

}
