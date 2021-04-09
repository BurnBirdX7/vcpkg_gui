package ru.lazarev_am.vcpkg_gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainWindow extends TableWindow {

    // Important constants:
    static final String UPDATE_BUTTON_TEXT = "Update";
    static final String ADD_BUTTON_TEXT = "Add";
    static final String REMOVE_BUTTON_TEXT = "Remove";
    static final String REMOVE_W_RECURSE_BUTTON_TEXT = "Remove (With Recurse)";

    // Constructor:
    public MainWindow(vcpkg vcpkg) {
        super("vcpkg gui || Artemiy Lazarev");
        mVcpkg = vcpkg;
        mInstallWindow = new InstallWindow(vcpkg);
        mInstallWindow.addInstallationListener(this::installationListener);

        constructInterface();
        configureButtons();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        updateTable();
    }

    private void updateTable() {
        super.updateTable(mVcpkg.getInstalledPackages());
    }

    private void removeSelectedPackage(boolean withRecurse) {
        int row = mTable.getSelectedRow();
        if (row == -1)
            return;

        String packageName = mTableModel.getPackageNameAtRow(row);

        String details = mVcpkg.removePackage(packageName, withRecurse);

        if (details != null) {
            DetailsDialog dialog = new DetailsDialog("Package " + packageName + " deleted.", details, this);
            dialog.setVisible(true);
        }
        updateTable();
    }

    private void installationListener(String ignored) {
        updateTable();
    }

    private void buttonListener(ActionEvent e) {
        String btn = e.getActionCommand();
        switch (btn) {
            case ADD_BUTTON_TEXT -> mInstallWindow.newSearch();
            case REMOVE_BUTTON_TEXT -> removeSelectedPackage(false);
            case REMOVE_W_RECURSE_BUTTON_TEXT -> removeSelectedPackage(true);
            case UPDATE_BUTTON_TEXT -> updateTable();
        }
    }

    private void constructInterface() {
        mAddButton = new JButton(ADD_BUTTON_TEXT);
        mRemoveButton = new JButton(REMOVE_BUTTON_TEXT);
        mRemoveWRecurseButton = new JButton(REMOVE_W_RECURSE_BUTTON_TEXT);
        mUpdateButton = new JButton(UPDATE_BUTTON_TEXT);
    }

    private void configureButtons() {
        mMainPanel.add(mButtonsPanel, BorderLayout.SOUTH);

        addButton(mAddButton);
        addButton(mRemoveButton);
        addButton(mRemoveWRecurseButton);
        addButton(mUpdateButton);

        mAddButton.addActionListener(this::buttonListener);
        mRemoveButton.addActionListener(this::buttonListener);
        mRemoveWRecurseButton.addActionListener(this::buttonListener);
        mUpdateButton.addActionListener(this::buttonListener);
    }

    // Interface:
    private JButton mAddButton;
    private JButton mRemoveButton;
    private JButton mRemoveWRecurseButton;
    private JButton mUpdateButton;

    private final vcpkg mVcpkg;
    private final InstallWindow mInstallWindow;

}
