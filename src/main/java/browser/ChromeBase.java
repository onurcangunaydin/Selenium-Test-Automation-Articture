package browser;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

import java.util.logging.Level;

public interface ChromeBase {
    static ChromeOptions getDefaultChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-position=0,0");
        options.addArguments("disable-infobars");
        options.addArguments("disable-notifications");
        options.addArguments("--user-agent=Selenium");


        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);


        return options;
    }

}
