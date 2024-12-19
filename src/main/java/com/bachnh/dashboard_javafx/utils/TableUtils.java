package com.bachnh.dashboard_javafx.utils;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
public class TableUtils {
    public static void disableSorting(TableView<?> tableView) {
        for (TableColumn<?, ?> column : tableView.getColumns()) {
            column.setSortable(false); // Disable sorting for each column
        }
    }
}
