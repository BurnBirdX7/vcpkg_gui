package ru.lazarev_am;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;

public class DetailsDialog extends JDialog {

    public DetailsDialog(String shortMessage, String details, JFrame parent) {
        super(parent, "Details", true);

        constructInterface();
        setContentPane(mMainPanel);

        mButton.addActionListener((ActionEvent e) -> {
            this.setVisible(false);
        });

        mMainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mDetailsArea.setBorder(new EmptyBorder(5, 10, 5, 10));

        mShortMessageField.setText(shortMessage);
        mDetailsArea.setText(details);

        pack();
        setLocationRelativeTo(parent);
    }

    private void constructInterface() {
        mMainPanel = new JPanel(new BorderLayout(10, 10));
        mShortMessageField = new JTextField();
        mDetailsArea = new JTextArea();
        mButton = new JButton("OK");

        mMainPanel.add(mShortMessageField, BorderLayout.NORTH);
        mMainPanel.add(mDetailsArea, BorderLayout.CENTER);
        mMainPanel.add(mButton, BorderLayout.SOUTH);

        mShortMessageField.setBorder(new EmptyBorder(0, 0, 0, 0));

        mShortMessageField.setEditable(false);
        mDetailsArea.setEditable(false);
    }

    private JPanel mMainPanel;
    private JTextField mShortMessageField;
    private JTextArea mDetailsArea;
    private JButton mButton;
    static final int borderSize = 15;

    public void loadDetailsFromReader(BufferedReader reader) {
        try {
            for (String line = reader.readLine(); line != null; line = reader.readLine())
                mDetailsArea.setText(mDetailsArea.getText() + line + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
