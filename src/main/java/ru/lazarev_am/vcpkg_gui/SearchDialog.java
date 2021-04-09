package ru.lazarev_am.vcpkg_gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SearchDialog extends JDialog {

    public SearchDialog(Component parent) {
        setTitle("Find a package");

        mSearchString = null;
        mSearchField = new JTextField();
        mSearchButton = new JButton("Search");

        setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));
        add(mSearchField);
        add(mSearchButton);

        mSearchField.setPreferredSize(new Dimension(300, 30));

        mSearchButton.addActionListener(this::writeResult);
        setModal(true);
        setLocationRelativeTo(parent);
        pack();
    }

    public String getSearchString() {
        return mSearchString;
    }

    public void writeResult(ActionEvent e) {
        mSearchString = mSearchField.getText();
        setVisible(false);
    }

    private String mSearchString;
    private JTextField mSearchField;
    private JButton mSearchButton;

}
