package com.bachnh.dashboard_javafx;

import com.bachnh.dashboard_javafx.controller.DashboardController;
import com.bachnh.dashboard_javafx.dto.EmployeeDT0;
import fr.brouillard.oss.cssfx.CSSFX;
import io.github.palexdev.materialfx.theming.JavaFXThemes;
import io.github.palexdev.materialfx.theming.MaterialFXStylesheets;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.TableViewSkin;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DashboardJavaFxApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        ObservableList<Employee> data = FXCollections.observableArrayList(
                new Employee("1", "EMP001", "Nguyen Van A", "IT", "Hanoi", "Active",
                        "Field1", "Field2", "Field3", "Field4", "Field5",
                        "Field6", "Field7", "Field8", "Field9", "Field10"),
                new Employee("2", "EMP002", "Tran Thi B", "HR", "HCM", "Active",
                        "Data1", "Data2", "Data3", "Data4", "Data5",
                        "Data6", "Data7", "Data8", "Data9", "Data10"),
                new Employee("3", "EMP003", "Le Van C", "Finance", "Danang", "Inactive",
                        "Info1", "Info2", "Info3", "Info4", "Info5",
                        "Info6", "Info7", "Info8", "Info9", "Info10")
        );

        // Table cố định cột đầu tiên
        TableView<Employee> fixedFirstTable = new TableView<>();
        fixedFirstTable.setMinWidth(100);
        fixedFirstTable.setMaxWidth(100); // Đảm bảo kích thước cố định

        TableColumn<Employee, String> firstColumn = new TableColumn<>("ID");
        firstColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstColumn.setPrefWidth(100);

        fixedFirstTable.getColumns().add(firstColumn);
        fixedFirstTable.setItems(data);
        fixedFirstTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Table giữa có thanh cuộn ngang
        TableView<Employee> scrollableTable = new TableView<>();
        scrollableTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        scrollableTable.setPrefWidth(600); // Đặt chiều rộng ưa thích cho bảng giữa

        for (int i = 1; i <= 10; i++) {
            TableColumn<Employee, String> column = new TableColumn<>("Field" + i);
            column.setCellValueFactory(new PropertyValueFactory<>("field" + i));
            column.setPrefWidth(150); // Chiều rộng cố định
            column.setMinWidth(100); // Đặt chiều rộng tối thiểu cho cột
            scrollableTable.getColumns().add(column);
        }
        scrollableTable.setItems(data);

        // Bọc bảng giữa bằng ScrollPane để tạo thanh cuộn ngang
        ScrollPane scrollPane = new ScrollPane(scrollableTable);
        scrollPane.setStyle("-fx-background-color: WHITE;");
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true); // Không ép chiều rộng
        scrollPane.setMinWidth(600); // Đặt chiều rộng tối thiểu cho ScrollPane

        HBox.setHgrow(scrollPane, Priority.ALWAYS); // Cho phép bảng giữa mở rộng

        // Table cố định cột cuối cùng
        TableView<Employee> fixedLastTable = new TableView<>();
        fixedLastTable.setMinWidth(100);
        fixedLastTable.setMaxWidth(100); // Đảm bảo kích thước cố định

        TableColumn<Employee, String> lastColumn = new TableColumn<>("Status");
        lastColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        lastColumn.setPrefWidth(100);
        fixedLastTable.getColumns().add(lastColumn);
        fixedLastTable.setItems(data);
        fixedLastTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Layout giao diện
        HBox tableContainer = new HBox(fixedFirstTable, scrollPane, fixedLastTable);
        tableContainer.setSpacing(0); // Khoảng cách giữa các bảng
        tableContainer.setStyle("-fx-background-color: #f4f4f4;");

        Scene scene = new Scene(tableContainer, 1200, 400); // Kích thước ban đầu
        primaryStage.setScene(scene);
        primaryStage.setTitle("Fixed Columns TableView with Scrollable Middle");
        primaryStage.show();
        primaryStage.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double totalColumnWidth = scrollableTable.getColumns().stream()
                    .mapToDouble(TableColumn::getPrefWidth)
                    .sum();

            if (newWidth.doubleValue() > totalColumnWidth + 200) { // Nếu giao diện rộng hơn bảng
                scrollableTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // Cột co giãn
            } else {
                scrollableTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY); // Giữ kích thước cố định
            }

            // Cập nhật chiều rộng tổng của bảng
            scrollableTable.setPrefWidth(Math.max(totalColumnWidth, newWidth.doubleValue() - 200));
        });

    }

    public static void main(String[] args) {
        launch(args);
    }

    public static class Employee {
        private final SimpleStringProperty id;
        private final SimpleStringProperty employeeCode;
        private final SimpleStringProperty name;
        private final SimpleStringProperty department;
        private final SimpleStringProperty location;
        private final SimpleStringProperty status;

        // Các trường bổ sung cho 10 cột
        private final SimpleStringProperty field1;
        private final SimpleStringProperty field2;
        private final SimpleStringProperty field3;
        private final SimpleStringProperty field4;
        private final SimpleStringProperty field5;
        private final SimpleStringProperty field6;
        private final SimpleStringProperty field7;
        private final SimpleStringProperty field8;
        private final SimpleStringProperty field9;
        private final SimpleStringProperty field10;

        public Employee(String id, String employeeCode, String name, String department, String location, String status,
                        String field1, String field2, String field3, String field4, String field5,
                        String field6, String field7, String field8, String field9, String field10) {
            this.id = new SimpleStringProperty(id);
            this.employeeCode = new SimpleStringProperty(employeeCode);
            this.name = new SimpleStringProperty(name);
            this.department = new SimpleStringProperty(department);
            this.location = new SimpleStringProperty(location);
            this.status = new SimpleStringProperty(status);

            this.field1 = new SimpleStringProperty(field1);
            this.field2 = new SimpleStringProperty(field2);
            this.field3 = new SimpleStringProperty(field3);
            this.field4 = new SimpleStringProperty(field4);
            this.field5 = new SimpleStringProperty(field5);
            this.field6 = new SimpleStringProperty(field6);
            this.field7 = new SimpleStringProperty(field7);
            this.field8 = new SimpleStringProperty(field8);
            this.field9 = new SimpleStringProperty(field9);
            this.field10 = new SimpleStringProperty(field10);
        }

        public String getId() {
            return id.get();
        }

        public String getEmployeeCode() {
            return employeeCode.get();
        }

        public String getName() {
            return name.get();
        }

        public String getDepartment() {
            return department.get();
        }

        public String getLocation() {
            return location.get();
        }

        public String getStatus() {
            return status.get();
        }

        public String getField1() {
            return field1.get();
        }

        public String getField2() {
            return field2.get();
        }

        public String getField3() {
            return field3.get();
        }

        public String getField4() {
            return field4.get();
        }

        public String getField5() {
            return field5.get();
        }

        public String getField6() {
            return field6.get();
        }

        public String getField7() {
            return field7.get();
        }

        public String getField8() {
            return field8.get();
        }

        public String getField9() {
            return field9.get();
        }

        public String getField10() {
            return field10.get();
        }
    }
}

