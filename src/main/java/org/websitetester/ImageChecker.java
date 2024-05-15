package org.websitetester;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.*;

public class ImageChecker {

    Website website;

    public ImageChecker(Website website) {
        this.website = website;
    }

    public void checkImagesInEdge(){
        System.out.println("Checking images...");

        EdgeDriver driver = new EdgeDriver();
        driver.get(String.valueOf(this.website.url));

        // list of images
        this.website.imagesList = driver.findElements(By.tagName("img"));

        // gets the images (src) and their alt text
        for (WebElement imagesWebElement : this.website.imagesList) {

            String src = imagesWebElement.getAttribute("src");
            String altSrc = imagesWebElement.getAttribute("alt");
            System.out.println(src + " - " + altSrc);

            Image image = new Image(altSrc, src);
            this.website.images.add(image);
        }

        driver.close();
    }
}
