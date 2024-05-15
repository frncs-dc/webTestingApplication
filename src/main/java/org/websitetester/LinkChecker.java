package org.websitetester;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;

import java.net.HttpURLConnection;
import java.net.URL;

public class LinkChecker {

    Website website;

    public LinkChecker(Website website) {
        this.website = website;
    }

    /***
     * Verifies if the url is working or not. If it is a working link it is added to
     * the List of working links. Else, it is added to the List of broken links.
     * @param url is the url to verify
     */
    public void verifyLink(String url) {
        try {
            URL link = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) link.openConnection();
            httpURLConnection.setConnectTimeout(3000); // Set connection timeout to 3 seconds
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() == 200) {
                System.out.println(url + " - " + httpURLConnection.getResponseMessage());
                this.website.workingLinks.add(link);
            } else {
                System.out.println(this.website.url + " - " + httpURLConnection.getResponseMessage() + " - " + "is a broken link");
                this.website.brokenLinks.add(link);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(url + " - " + "is a broken link");
        }
    }

    public void checkLinksInEdge(){
        EdgeDriver driver = new EdgeDriver();
        driver.get(String.valueOf(this.website.url));

        driver.manage().window().maximize();

        // list of anchor links found in the website
        this.website.links = driver.findElements((By.tagName("a")));

        System.out.println("Checking links...");

        // gets the links in the website and checks if they are valid
        for (WebElement link : this.website.links) {
            String url = link.getAttribute("href");
            verifyLink(url);
        }

//        TODO: Save logs to txt or csv file

        System.out.println("Done checking links");
        driver.close();
    }
}
