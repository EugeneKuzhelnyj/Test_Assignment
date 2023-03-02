package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class GetURL {
    private static final Logger LOG = LoggerFactory.getLogger(GetURL.class);

    public static String getCurrentURL(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            LOG.info("Thread"+Thread.currentThread().getName()+" was interrupted while sleep");
        }
        return getWebDriver().getCurrentUrl();
    }
}
