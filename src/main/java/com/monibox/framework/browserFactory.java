/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monibox.framework;

import static com.monibox.helperUtils.ConfigPropertyReader.getProperty;

import java.io.File;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class browserFactory {
	// IE11 is stable only with 2.53.1 Selenium bindings
//Latest firefox works with latest geckodriver paths
//Chrome driver is compatible with all selenium binding versions
//Edge browser support is brittle as of now
	private static String browser;
	// \Selenium_Tests\seleniumConfigs
	private static final DesiredCapabilities capabilities = new DesiredCapabilities();

	String environment = System.getProperty("env",
			getProperty(System.getProperty("user.dir") + File.separator + "Config.properties", "env"));
	private static String chromeDriverPath;
	private static String geckoDriverPath;
	private static String edgeDriverPath;
	private static String ieDriverPath;
	private static String phantomDriverPath;

	public browserFactory() {
		if (environment.equalsIgnoreCase("local")) {

			if (System.getProperty("os.name").toLowerCase().contains("windows")) {
				phantomDriverPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
						+ File.separator + "resources" + File.separator + "Drivers" + File.separator + "Windows"
						+ File.separator + "phantomjs.exe";
				chromeDriverPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
						+ File.separator + "resources" + File.separator + "Drivers" + File.separator + "Windows"
						+ File.separator + "chromedriver.exe";
				geckoDriverPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
						+ File.separator + "resources" + File.separator + "Drivers" + File.separator + "Windows"
						+ File.separator + "geckodriver.exe";
				edgeDriverPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
						+ File.separator + "resources" + File.separator + "Drivers" + File.separator + "Windows"
						+ File.separator + "MicrosoftWebDriver.exe";
				ieDriverPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
						+ File.separator + "resources" + File.separator + "Drivers" + File.separator + "Windows"
						+ File.separator + "IEDriverServer.exe";

			} else if (System.getProperty("os.name").toLowerCase().contains("linux")) {
				chromeDriverPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
						+ File.separator + "resources" + File.separator + "Drivers" + File.separator + "Linux"
						+ File.separator + "chromedriver";
				geckoDriverPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
						+ File.separator + "resources" + File.separator + "Drivers" + File.separator + "Linux"
						+ File.separator + "geckodriver";

			} else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
				chromeDriverPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
						+ File.separator + "resources" + File.separator + "Drivers" + File.separator + "Mac"
						+ File.separator + "chromedriver";
				geckoDriverPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
						+ File.separator + "resources" + File.separator + "Drivers" + File.separator + "Mac"
						+ File.separator + "geckodriver";

			}
		} 

	}

	// C:\Program Files (x86)\Microsoft Web Driver
//	private static final String edgeDriverPath = System.getProperty("C://Program Files (x86)//Microsoft Web Driver//MicrosoftWebDriver.exe");
	public WebDriver getDriver(String browserName) {
		browser = browserName;
		System.out.println("Test Browser is :" + browser);

		if (browser.equalsIgnoreCase("firefox")) {
			return getFirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			return getChromeDriver(chromeDriverPath);
		} else if ((browser.equalsIgnoreCase("ie")) || (browser.equalsIgnoreCase("internetexplorer"))
				|| (browser.equalsIgnoreCase("internet explorer"))) {
			return getInternetExplorerDriver(ieDriverPath);

		} else if ((browser.equalsIgnoreCase("edge")) || (browser.equalsIgnoreCase("Edge"))) {
			return getEdgeDriver(edgeDriverPath);
		}

		return new FirefoxDriver();
	}

	private static WebDriver getChromeDriver(String driverpath) {
		System.setProperty("webdriver.chrome.driver", driverpath);
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("download.default_directory", System.getProperty("user.dir") + File.separator + "target");
		ChromeOptions options = new ChromeOptions();

		options.addArguments("--disable-extensions");
		options.addArguments("test-type");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.setExperimentalOption("prefs", prefs);
		return new ChromeDriver(options);
	}

	private static WebDriver getInternetExplorerDriver(String driverpath) {
		System.setProperty("webdriver.ie.driver", driverpath);
		InternetExplorerOptions options = new InternetExplorerOptions();
		options.setCapability("ignoreZoomSetting", true);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		// ---> Add this capability only if your execution is backed down due to network
		// policies
		return new InternetExplorerDriver(options);
	}

	private static WebDriver getFirefoxDriver() {
		System.setProperty("webdriver.gecko.driver", geckoDriverPath);
		// Uncomment above statement if selenium version in project POM file is greater
		// than 2.53
		FirefoxProfile profile = new FirefoxProfile();
		FirefoxOptions options = new FirefoxOptions();

		options.addPreference("browser.cache.disk.enable", false);
		options.addPreference("browser.tabs.remote.autostart", false);
		options.addPreference("browser.tabs.remote.autostart.1", false);
		options.addPreference("browser.tabs.remote.autostart.2", false);
		options.addPreference("network.proxy.type", 4);
		return new FirefoxDriver(options);
	}

	private static WebDriver getEdgeDriver(String driverpath) {
		System.setProperty("webdriver.edge.driver", driverpath);
		EdgeOptions options = new EdgeOptions();

		return new EdgeDriver(options);
	}

}
