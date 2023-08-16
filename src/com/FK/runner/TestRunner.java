package com.FK.runner;


import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.TestNG;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;


public class TestRunner {

    public static void main(String[] args) throws MessagingException, IOException {

        // Create object of TestNG Class
        TestNG runner = new TestNG();

        // Create a list of String
        List<String> suitefiles=new ArrayList<String>();

        // Add xml file which you have to execute
        suitefiles.add("sanity-suite.xml");

        // now set xml file for execution
        runner.setTestSuites(suitefiles);

        // finally execute the runner using run method
        runner.run();
        System.exit(0);
    }
}
