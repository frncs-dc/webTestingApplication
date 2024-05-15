package org.websitetester;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CompatibilityChecker {

    Website website;

    // Browser Drivers
    EdgeDriver edgeDriver;
    ChromeDriver chromeDriver;

    public static final String[] deviceNames = {
        "Laptop",
        "iPhone SE",
        "iPhone XR",
        "iPhone 12 Pro",
//        "iPhone 14 Pro Max",
//        "Pixel 7",
//        "Samsung Galaxy S8+",
//        "Samsung Galaxy S20 Ultra",
//        "iPad Mini",
//        "iPad Air",
//        "iPad Pro",
//        "Surface Pro 7",
//        "Surface Duo",
//        "Galaxy Z Fold 5",
//        "Asus Zenbook Fold",
//        "Samsung Galaxy A51/71",
//        "Nest Hub"
    };

    public CompatibilityChecker(Website website){
        this.website = website;
    }

    public void scrollPageWithScreenshotChrome(String deviceName, JavascriptExecutor js, int height) throws IOException {

        int scrollHeight = Integer.parseInt(js.executeScript("return document.body.scrollHeight").toString());
        int initialHeight = 0;
        int count = 0;

        js.executeScript("window.scrollBy(0, 0)");
        File scrFile = ((TakesScreenshot) chromeDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./Screenshots/" + "Edge/" + deviceName +"/" + initialHeight + ".png"));


        while (initialHeight < scrollHeight) {
            count++;
            js.executeScript("window.scrollBy(0, " + height + ")");
            scrFile = ((TakesScreenshot) chromeDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("./Screenshots/" + "Chrome/" + deviceName +"/" + initialHeight + ".png"));

            // checks if previous height is equal to current height
            int heightChecker = initialHeight;
            initialHeight = Integer.parseInt(js.executeScript("return window.pageYOffset").toString());

            if (heightChecker == initialHeight){
                break;
            }
        }
    }

    public void scrollPageWithScreenshotEdge(String deviceName, JavascriptExecutor js, int height) throws IOException {
        int scrollHeight = Integer.parseInt(js.executeScript("return document.body.scrollHeight").toString());
        int initialHeight = 0;
        int count = 0;

        js.executeScript("window.scrollBy(0, 0)");
        File scrFile = ((TakesScreenshot) edgeDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./Screenshots/" + "Edge/" + deviceName +"/" + initialHeight + ".png"));

        while (initialHeight < scrollHeight) {
            count++;
            js.executeScript("window.scrollBy(0, " + height + ")");
            scrFile = ((TakesScreenshot) edgeDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("./Screenshots/" + "Edge/" + deviceName +"/" + initialHeight + ".png"));

            // checks if previous height is equal to current height
            int heightChecker = initialHeight;
            initialHeight = Integer.parseInt(js.executeScript("return window.pageYOffset").toString());

            if (heightChecker == initialHeight){
                break;
            }
        }
    }

    public static ChromeOptions getChromeOptions(String deviceName) {
        Map<String, String> mobileEmulation = new HashMap<>();

        mobileEmulation.put("deviceName", deviceName);

        ChromeOptions chromeOptions = new ChromeOptions();

        if(!Objects.equals(deviceName, "Laptop")){
            chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        }

        return chromeOptions;
    }

    public static EdgeOptions getEdgeOptions(String deviceName) {
        Map<String, String> mobileEmulation = new HashMap<>();

        mobileEmulation.put("deviceName", deviceName);

        EdgeOptions edgeOptions = new EdgeOptions();

        if(!Objects.equals(deviceName, "Laptop")){
            edgeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        }

        return edgeOptions;
    }

    // Check Chrome Browser
    public void checkChromeBrowser() throws IOException {
        for (String deviceName : deviceNames) {
            this.chromeDriver = new ChromeDriver(getChromeOptions(deviceName));
            this.chromeDriver.get(String.valueOf(this.website.url));

            if (deviceName.equals("Laptop")) {
                this.chromeDriver.manage().window().maximize();
            }

            String size = this.chromeDriver.manage().window().getSize().toString();
            System.out.println(size);

            Integer width = Integer.valueOf(size.substring(1, size.indexOf(",")));
            int height = Integer.parseInt(((JavascriptExecutor)this.chromeDriver).executeScript("return window.document.documentElement.clientHeight").toString());

            scrollPageWithScreenshotChrome(deviceName, (JavascriptExecutor)this.chromeDriver, height);

            this.chromeDriver.quit();
        }
    }

//    Check Edge Browser
    public void checkEdgeBrowser() throws IOException {
        for (String deviceName : deviceNames) {
            this.edgeDriver = new EdgeDriver(getEdgeOptions(deviceName));
            edgeDriver.get(String.valueOf(this.website.url));

            if (deviceName.equals("Laptop")) {
                edgeDriver.manage().window().maximize();
            }

            String size = edgeDriver.manage().window().getSize().toString();
            System.out.println(size);

            Integer width = Integer.valueOf(size.substring(1, size.indexOf(",")));
            int height = Integer.parseInt(((JavascriptExecutor)edgeDriver).executeScript("return window.document.documentElement.clientHeight").toString());

            scrollPageWithScreenshotEdge(deviceName, (JavascriptExecutor)edgeDriver, height);

            edgeDriver.quit();
        }
    }
}
