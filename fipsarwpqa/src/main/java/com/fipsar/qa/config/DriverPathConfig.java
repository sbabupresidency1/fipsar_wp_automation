package com.fipsar.qa.config;

import com.fipsar.qa.utils.Platform;
import com.fipsar.qa.utils.Utils;

/**
 * Created  on 07-02-2017.
 */
public class DriverPathConfig {
    public static void setDriverPaths() {
        if (Utils.isGrid()) {
            return;
        }

        final String osName = Platform.OS.toUpperCase();

        if (osName.contains("WINDOWS")) {
            setPath(ApplicationConstants.GECKO_DRIVER_NAME, ApplicationConstants.GECKO_DRIVER_PATH_WINDOWS);
            setPath(ApplicationConstants.CHROME_DRIVER_NAME, ApplicationConstants.CHROME_DRIVER_PATH_WINDOWS);
            setPath(ApplicationConstants.IE_DRIVER_NAME, ApplicationConstants.IE_DRIVER_PATH);
            setPath(ApplicationConstants.OPERA_DRIVER_NAME, ApplicationConstants.OPERA_DRIVER_PATH_WINDOWS);
            
        } else if (osName.contains("LINUX")) {
            setPath(ApplicationConstants.GECKO_DRIVER_NAME, ApplicationConstants.GECKO_DRIVER_PATH_LINUX);
            setPath(ApplicationConstants.CHROME_DRIVER_NAME, ApplicationConstants.CHROME_DRIVER_PATH_LINUX);
        }
        else if (osName.contains("MAC")) {
            setPath(ApplicationConstants.GECKO_DRIVER_NAME, ApplicationConstants.GECKO_DRIVER_PATH_MAC);
            setPath(ApplicationConstants.CHROME_DRIVER_NAME, ApplicationConstants.CHROME_DRIVER_PATH_MAC_OS);
            setPath(ApplicationConstants.SAFARI_DRIVER_NAME, ApplicationConstants.SAFARI_DRIVER_PATH);
        }
    }
    private static void setPath(final String propertyKey, final String driverPath) {
        System.setProperty(propertyKey, driverPath);
    }
}
