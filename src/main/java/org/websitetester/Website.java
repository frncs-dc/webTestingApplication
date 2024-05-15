package org.websitetester;

import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Website {
    String url;
    List<WebElement> links = new ArrayList<>();
    List<URL> workingLinks = new ArrayList<>();
    List<URL> brokenLinks = new ArrayList<>();
    List<WebElement> imagesList = new ArrayList<>();
    ArrayList<Image> images = new ArrayList<>();

    CompatibilityChecker compatibilityChecker = new CompatibilityChecker(this);
    LinkChecker linkChecker = new LinkChecker(this);
    ImageChecker imageChecker = new ImageChecker(this);

    public Website(String url) {
        this.url = url;
    }

    /***
     * This function starts the testing of the website.
     * @throws IOException
     */
    public void startTest() throws IOException {
        try{
            // Checks the browsers
//            compatibilityChecker.checkChromeBrowser();
//            compatibilityChecker.checkEdgeBrowser();

            linkChecker.checkLinksInEdge();
            imageChecker.checkImagesInEdge();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }





}
