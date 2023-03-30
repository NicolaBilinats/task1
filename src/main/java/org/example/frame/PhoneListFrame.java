package org.example.frame;

import javax.swing.*;
import java.awt.*;

public class PhoneListFrame extends JFrame {
    private final JTextArea textArea;

    public PhoneListFrame() {
        setTitle("Phone List");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public void appendText(String text) {
        textArea.append(text + "\n");
    }
}