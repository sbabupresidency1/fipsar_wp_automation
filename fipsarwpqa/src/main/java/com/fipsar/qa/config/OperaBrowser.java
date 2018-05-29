package com.fipsar.qa.config;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Class to configure and get Details for opera Browser
 */
public class OperaBrowser extends DefaultBrowser implements Browser {
	
    @SuppressWarnings("deprecation")
	@Override    
    protected Capabilities createRemoteCapabilities() {
    	OperaDriver driver = null;
    	System.setProperty("webdriver.opera.driver", "C:\\workspace\\fipsar_automation\\fipsarqa\\src\\main\\resources\\drivers\\operadriver.exe");
    	OperaOptions options = new OperaOptions();
        options.addArguments("user-agent=foo;bar");       
        options.setBinary(new File("C:\\Program Files\\Opera\\51.0.2830.55\\opera.exe"));
        driver = new OperaDriver(options);

        Object userAgent = driver.executeScript("return window.navigator.userAgent");
        assertEquals("foo;bar", userAgent);
        
        final DesiredCapabilities caps = DesiredCapabilities.opera();
        caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        return caps;
    }

    @Override
    protected RemoteWebDriver createLocalDriver() {
        return new OperaDriver();
    }
}