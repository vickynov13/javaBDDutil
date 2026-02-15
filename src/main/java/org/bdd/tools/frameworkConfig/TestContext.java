package org.bdd.tools.frameworkConfig;

public class TestContext {
    public ScenarioContext scenarioContext;
    public DriverManager driverManager;
    public TestContext() {
        driverManager = new DriverManager();
        scenarioContext = new ScenarioContext();
    }

  public DriverManager getDriverRegistry() {
        return driverManager;
    }
  public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }

}
