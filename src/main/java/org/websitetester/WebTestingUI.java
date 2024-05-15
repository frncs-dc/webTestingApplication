package org.websitetester;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class WebTestingUI extends JFrame {
    private JTextField textField1;
    private JPanel panel1;
    private JButton testWebsiteButton;

    public WebTestingUI() {

        testWebsiteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Website website = new Website(textField1.getText());
                try {
                    website.startTest();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        this.setContentPane(this.panel1);
        this.setTitle("Web Testing Application");
        this.setSize(700,400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }




    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
