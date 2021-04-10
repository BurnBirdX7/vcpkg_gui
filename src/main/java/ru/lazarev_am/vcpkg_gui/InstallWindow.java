package ru.lazarev_am.vcpkg_gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

interface InstallationListener {
    void installationPerformed(String packageName);
}

public class InstallWindow extends TableWindow {

    public InstallWindow(Vcpkg vcpkg) {
        super("Install new package");
        mListeners = new ArrayList<>();
        constructInterface();
        mVcpkg = vcpkg;
    }

    public void newSearch() {
        setVisible(true);
        searchPackage();
    }

    public void addInstallationListener(InstallationListener listener) {
        mListeners.add(listener);
    }

    private void constructInterface() {
        mInstallButton = new JButton(INSTALL_BUTTON_TEXT);
        mInstallWithRecurseButton = new JButton(INSTALL_W_RECURSE_BUTTON_TEXT);
        mCancelButton = new JButton(CANCEL_BUTTON_TEXT);
        mNewSearchButton = new JButton(NEW_SEARCH_BUTTON_TEXT);

        mInstallButton.addActionListener(this::buttonsListener);
        mInstallWithRecurseButton.addActionListener(this::buttonsListener);
        mCancelButton.addActionListener(this::buttonsListener);
        mNewSearchButton.addActionListener(this::buttonsListener);

        addButton(mInstallButton);
        addButton(mInstallWithRecurseButton);
        addButton(mCancelButton);
        addButton(mNewSearchButton);
    }

    private void buttonsListener(ActionEvent event) {
        switch (event.getActionCommand()) {
            case INSTALL_BUTTON_TEXT -> installPackage(false);
            case INSTALL_W_RECURSE_BUTTON_TEXT -> installPackage(true);
            case CANCEL_BUTTON_TEXT -> setVisible(false);
            case NEW_SEARCH_BUTTON_TEXT -> searchPackage();
        }
    }

    private void installPackage(boolean withRecurse) {
        int row = mTable.getSelectedRow();
        if (row == -1)
            return;

        String packageName = mTableModel.getPackageNameAtRow(row);

        String details = mVcpkg.installPackage(packageName, withRecurse);
        if (details != null) {
            DetailsDialog dialog = new DetailsDialog("Package installation", details, this);
            dialog.setVisible(true);
        }

        for (var listener: mListeners) // Notify
            listener.installationPerformed(packageName);
    }

    private void searchPackage() {
        SearchDialog dialog = new SearchDialog(this);
        dialog.setVisible(true);
        String searchString = dialog.getSearchString();
        if (searchString != null)
            updateTable(mVcpkg.searchPackage(searchString));
    }

    static final String INSTALL_BUTTON_TEXT = "Install";
    static final String INSTALL_W_RECURSE_BUTTON_TEXT = "Install (with Recurse)";
    static final String CANCEL_BUTTON_TEXT = "Cancel";
    static final String NEW_SEARCH_BUTTON_TEXT = "New Search";

    private JButton mInstallButton;
    private JButton mInstallWithRecurseButton;
    private JButton mCancelButton;
    private JButton mNewSearchButton;

    private ArrayList<InstallationListener> mListeners;

    private Vcpkg mVcpkg;

}
