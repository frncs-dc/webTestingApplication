package org.websitetester;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                WebTestingUI webTestingUI = new WebTestingUI();
            }
        });
    }
}