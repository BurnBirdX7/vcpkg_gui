package ru.lazarev_am.vcpkg_gui;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

public class TableWindow extends JFrame {

    // Constructor:
    public TableWindow(String title) {
        super(title);

        constructInterface();
        setContentPane(mMainPanel);
        configureTable();

        setLocationRelativeTo(getParent());
        pack();
    }

    protected void addButton(JButton button) {
        mButtonsPanel.add(button);
    }

    protected void updateTable(List<vcpkg.Package> packageList) {
        mTableModel.setRowCount(0); // Remove all rows
        for (vcpkg.Package pack: packageList)
            mTableModel.addRow(pack.getArray());
    }

    // Interface methods:
    private void constructInterface() {
        mMainPanel = new JPanel(new BorderLayout());
        mScrollPane = new JScrollPane();
        mTable = new JTable();
        mButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mMainPanel.add(mButtonsPanel, BorderLayout.SOUTH);
    }

    private void configureTable() {
        mTableModel = new DefaultTableModel();
        mTable.setModel(mTableModel);

        mTable.setPreferredScrollableViewportSize(new Dimension(800, 350));

        mScrollPane.setViewportView(mTable);
        mScrollPane.setColumnHeaderView(mTable.getTableHeader());
        mMainPanel.add(mScrollPane, BorderLayout.CENTER);

        // Columns:
        mTableModel.setColumnIdentifiers(new String[]{"name", "version", "description"});
        TableColumnModel columnModel = mTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(400);

        mTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mTable.setShowVerticalLines(false);
    }

    // Interface:
    protected JPanel mMainPanel;
    protected JScrollPane mScrollPane;
    protected JTable mTable;
    protected JPanel mButtonsPanel;

    protected DefaultTableModel mTableModel;

}
