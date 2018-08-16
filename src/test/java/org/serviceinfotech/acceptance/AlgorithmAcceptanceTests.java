package org.serviceinfotech.acceptance;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.Ignore;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/Sequencing.feature",
        glue = "org.serviceinfotech.steps", tags = "~@wip")
public class AlgorithmAcceptanceTests {

}
