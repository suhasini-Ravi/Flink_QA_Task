package com.flink.util;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;

public class ExtentManager {
	private static ExtentReports extent;

	public static ExtentReports getInstance() {
		if (extent == null) {
			extent = new ExtentReports(System.getProperty("user.dir")+"/Reports/Flink_QA_Task_Automation_Report.html", true, DisplayOrder.NEWEST_FIRST);

			// optional
		//	extent.config().documentTitle("Automation Report")
		//			.reportName("Regression").reportHeadline("");
			extent.loadConfig(new File(System.getProperty("user.dir")+"/ReportsConfig.xml"));
			// optional
			extent.addSystemInfo("Selenium Version", "3.141.59").addSystemInfo(
					"Environment", "Test");
		}
		return extent;
	}
}
