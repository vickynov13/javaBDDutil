package org.bdd.tools.util;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.presentation.PresentationMode;

import java.io.File;
import java.util.ArrayList;

public class ReportHelper {

    ymlReader reader = new ymlReader();
    public static void generateCucumberReport() {
        File reportOutputDirectory = new File("target");
        ArrayList<String> jsonFiles = new ArrayList<>();
        ymlReader reader = new ymlReader();
        jsonFiles.add("target/cucumber-html-reports/Main.json");

        File file;
        file = new File("target/cucumber-html-reports/Rerun.json");
        if(file.exists()){
            jsonFiles.add("target/cucumber-html-reports/Rerun.json");
        }
        String buildNumber = System.getProperty("buildID");
        String projectName = System.getProperty("projectName")==null?"CQE ToolKit":System.getProperty("projectName");
        addConfiguration(reportOutputDirectory,jsonFiles,buildNumber,projectName,reader);

    }

    private static void addConfiguration(File opDirectory, ArrayList<String> jsonFiles, String buildNumber, String projectName, ymlReader reader) {
        Configuration config = new Configuration(opDirectory, projectName);
        config.addPresentationModes(PresentationMode.PARALLEL_TESTING);
        config.setBuildNumber(buildNumber);
        config.addClassifications("TestPlatform", reader.getTestPlatform());
        if(reader.getTestPlatform().toLowerCase().contains("desktop")) {
            config.addClassifications("Platform", System.getProperty("os.name"));
            config.addClassifications("ApplicationUrl", reader.getApplicationUrl());
            config.addClassifications("Browser", reader.getBrowser().toString());
           // config.addClassifications("Execution ID", DataBaseUtil.getExecutionID());
        }
        else if(reader.getTestPlatform().equalsIgnoreCase("Mobile")) {
//            config.addClassifications("Mobile Device Type", reader.getMobileDeviceType().toString());
//            config.addClassifications("Appium Server", reader.getAppiumServerUrl());
//            config.addClassifications("Mobile Device Name", reader.getDeviceName());
        }

        //This will generate a detailed report. If there are failures it shows the step which failed
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, config);
        reportBuilder.generateReports();


    }
}
