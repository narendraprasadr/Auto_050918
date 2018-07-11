package com.macys.mst.macysnet.stepdefs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.macys.mst.artemis.db.DBConnections;
import com.macys.mst.artemis.db.DBUtils;
import com.macys.mst.artemis.kdddriver.FetchExampleTable;
import com.macys.mst.artemis.reports.StepDetail;
import com.macys.mst.artemis.selenium.LocalDriverManager;
import com.macys.mst.artemis.selenium.SeUiContextBase;
import com.macys.mst.artemis.selenium.WebDriverListener;
import com.macys.mst.macysnet.config.Constants;
import com.macys.mst.macysnet.db.app.DBMethods;
import com.macys.mst.macysnet.pageobjects.LoginPage;
import com.macys.mst.macysnet.pageobjects.MnetHomePage;
import com.macys.mst.macysnet.pageobjects.RequestfreightMovement;
import com.macys.mst.macysnet.sqlconstants.SQLConstants;
import com.macys.mst.macysnet.utils.General;
import com.macys.mst.macysnet.utils.RestServicesUtils;

public class FreightOptimization {

	



}//end
