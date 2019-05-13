package com.lotus;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AndroidAutomationTests {

	@Test(enabled = false)
	public void realDeviceTest() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", "Chrome");
		capabilities.setCapability("deviceName", "Nexus_6_API_27");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("noReset", true);

		System.setProperty("androiddriver.chrome.driver", "/DronaUI/chromedriver");
		URL url = new URL("http://0.0.0.0:4723/wd/hub");

		AndroidDriver<MobileElement> driver = new AndroidDriver<MobileElement>(url, capabilities);
		System.out.println("testing the line");
		driver.get("http://google.com/");
		driver.findElement(By.name("q")).sendKeys("Hello appium");
		driver.quit();

	}

	@Test(enabled = true)
	public void emulatorWebTest() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", "Chrome");
		// capabilities.setCapability("version", "");

		capabilities.setCapability("deviceName", "Nexus_6_API_27");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("noReset", true);

		URL url = new URL("http://0.0.0.0:4723/wd/hub");

		AndroidDriver<MobileElement> driver = new AndroidDriver<MobileElement>(url, capabilities);
		driver.get("http://google.com/");
		driver.findElement(By.name("q")).sendKeys("Hello appium");
		driver.quit();
		// appium --chromedriver-executable /path/to/my/chromedriver

	}

	@Test(enabled = false)
	public void emulatorAppTest() throws MalformedURLException, InterruptedException {

		File appDir = new File("/Users/jaanu/Documents/");
		File app = new File(appDir, "com.quikr.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", "");

		capabilities.setCapability("deviceName", "Nexus_6_API_27");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.quikr");
		capabilities.setCapability("launchActivity ", "old.SplashActivity"); // my case RootActivity

		WebDriver driver1 = new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		Thread.sleep(50000);
		driver1.quit();

	}

//Open Contact Manager in Android
	@Test(enabled = false)
	public void emulatorNativeAppTest() throws Exception {

		File appDir = new File(System.getProperty("user.dir"));
		File app = new File(appDir, "/app/ContactManager.apk");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", "");

		capabilities.setCapability("deviceName", "Nexus_6_API_27");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("app", app.getAbsolutePath());

		capabilities.setCapability("appPackage", "com.example.android.contactmanager");
		capabilities.setCapability("appActivity ", "com.example.android.contactmanager.ContactManager");

		/*
		 * To Fetch the appPackage and appActivity 
		 * Go to location : 
		 * cd sdk/build-tools/version
		 * run this command : aapt dump badging ContactManager.apk
		 * This will return the package and launchActivity
		 */
		WebDriver driver1 = new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		Thread.sleep(10000);

		takeSnapShot(driver1, "/Users/jaanu/Desktop/sts-projects/DronaUI/Screenshots/native.png");
		Thread.sleep(20000);
		driver1.quit();

	}

	public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {

		// Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
		// Call getScreenshotAs method to create image file
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		// Move image file to new destination
		File DestFile = new File(fileWithPath);
		// Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);
	}
}
