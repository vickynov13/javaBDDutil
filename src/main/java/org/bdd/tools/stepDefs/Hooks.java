package org.bdd.tools.stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.bdd.tools.frameworkConfig.TestContext;
import org.bdd.tools.util.ymlReader;

import java.net.URISyntaxException;

public class Hooks {

    TestContext testContext = new TestContext();
    ymlReader reader = new ymlReader();

    public Hooks(TestContext context) {
        this.testContext = context;
    }

    @Before(value="@Web",order=0)
    public void BeforeSteps(Scenario scenario) {
        System.out.println("Before hook");
        testContext.getDriverRegistry().setScenario(scenario);
    }

    @After("@Web")
    public void teardown(Scenario scenario) throws InterruptedException, URISyntaxException {
        System.out.println("After hook");
        testContext.getDriverRegistry().closeDriver();
    }
}
