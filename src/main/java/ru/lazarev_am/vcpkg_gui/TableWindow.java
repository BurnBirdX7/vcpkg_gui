package ru.lazarev_am.vcpkg_gui;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;
import java.util.Vector;

class PackageTableModel extends AbstractTableModel {

    public static final int COLUMN_COUNT = 3;
    public static final int NAME_COLUMN = 0;
    public static final int VERS_COLUMN = 1;
    public static final int DESC_COLUMN = 2;


    public PackageTableModel() {
        this.mPackages = new Vector<>();
    }

    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case NAME_COLUMN -> "Name";
            case VERS_COLUMN -> "Version";
            case DESC_COLUMN -> "Description";
            default -> null;
        };
    }

    public void setPackages(List<Vcpkg.Package> list) {
        mPackages.clear();
        mPackages.addAll(list);
        fireTableDataChanged();
    }

    public String getPackageNameAtRow(int row) {
        return mPackages.elementAt(row).name;
    }

    @Override
    public int getRowCount() {
        return mPackages.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case NAME_COLUMN -> mPackages.elementAt(rowIndex).name;
            case VERS_COLUMN -> mPackages.elementAt(rowIndex).version;
            case DESC_COLUMN -> mPackages.elementAt(rowIndex).description;
            default -> null;
        };
    }

    private final Vector<Vcpkg.Package> mPackages;
}

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

    protected void updateTable(List<Vcpkg.Package> packageList) {
        mTableModel.setPackages(packageList);
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
        mTableModel = new PackageTableModel();
        mTable.setModel(mTableModel);

        mTable.setPreferredScrollableViewportSize(new Dimension(800, 350));

        mScrollPane.setViewportView(mTable);
        mScrollPane.setColumnHeaderView(mTable.getTableHeader());
        mMainPanel.add(mScrollPane, BorderLayout.CENTER);

        // Columns:
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

    protected PackageTableModel mTableModel;

}
