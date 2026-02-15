package org.bdd.tools.stepDefs;


import org.bdd.tools.frameworkConfig.TestContext;
import org.bdd.tools.helper.WebHelper;
import org.openqa.selenium.WebDriver;

public class KitDesktopSdf extends WebHelper {
	
	TestContext testContext;
	protected WebDriver driver;
	
	public KitDesktopSdf(TestContext context) {
		super(context);
		this.testContext = context;
		this.driver = context.getDriverRegistry().getDriver();
	}
}
