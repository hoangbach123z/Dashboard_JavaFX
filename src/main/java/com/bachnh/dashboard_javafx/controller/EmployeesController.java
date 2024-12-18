package com.bachnh.dashboard_javafx.controller;

import com.bachnh.dashboard_javafx.dto.EmployeeDT0;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EmployeesController implements Initializable {
    @FXML
    private TableView<EmployeeDT0> paginated;
    @FXML
    private BorderPane borderPane;
    @FXML
    private MFXButton addEmployeeBtn;
//    @FXML ScrollPane scrollPane;
    private FXMLLoader loader ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupPaginated();
        paginated.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY );

    }

    private void setupPaginated() {
        // Khởi tạo các cột bảng
        TableColumn<EmployeeDT0, String> IDColumn = new TableColumn<>("ID");
        TableColumn<EmployeeDT0, String> employeeCodeColumn = new TableColumn<>("Mã Nhân viên");
        TableColumn<EmployeeDT0, String> fullnameColumn = new TableColumn<>("Họ và Tên");
        TableColumn<EmployeeDT0, String> genderColumn = new TableColumn<>("Giới tính");
        TableColumn<EmployeeDT0, String> birthdayColumn = new TableColumn<>("Ngày sinh");
        TableColumn<EmployeeDT0, String> mobileColumn = new TableColumn<>("Số điện thoại");
        TableColumn<EmployeeDT0, String> cardIdColumn = new TableColumn<>("CMND/CCCD");
        TableColumn<EmployeeDT0, String> emailColumn = new TableColumn<>("Email");
        TableColumn<EmployeeDT0, String> addressColumn = new TableColumn<>("Địa chỉ");
        TableColumn<EmployeeDT0, String> departmentNameColumn = new TableColumn<>("Phòng ban");
        TableColumn<EmployeeDT0, String> roleNameColumn = new TableColumn<>("Vị trí");
        TableColumn<EmployeeDT0, String> statusColumn = new TableColumn<>("Trạng thái");
        TableColumn<EmployeeDT0, String> createDateColumn = new TableColumn<>("Ngày tạo");
        TableColumn<EmployeeDT0, String> updateDateColumn = new TableColumn<>("Ngày cập nhật");

        IDColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getID()));
        employeeCodeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmployeecode()));
        fullnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFullname()));
        genderColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGender()));
        birthdayColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBirthday()));
        mobileColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMobile()));
        cardIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCardId()));
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        addressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        departmentNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDepartmentName()));
        roleNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRoleName()));
        statusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
        createDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCreateDate()));
        updateDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUpdateDate()));

        // Đặt kích thước tối thiểu cho các cột
        IDColumn.setMinWidth(50);
        employeeCodeColumn.setMinWidth(150);
        fullnameColumn.setMinWidth(200);
        genderColumn.setMinWidth(100);
        birthdayColumn.setMinWidth(150);
        mobileColumn.setMinWidth(150);
        cardIdColumn.setMinWidth(150);
        emailColumn.setMinWidth(200);
        addressColumn.setMinWidth(200);
        departmentNameColumn.setMinWidth(150);
        roleNameColumn.setMinWidth(150);
        statusColumn.setMinWidth(100);
        createDateColumn.setMinWidth(150);
        updateDateColumn.setMinWidth(150);



        ObservableList<EmployeeDT0> data = FXCollections.observableArrayList(
                new EmployeeDT0("1", "EMP001", "Nguyễn Văn A", "Nam", "01/01/1990", "0912345678", "123456789", "adsadsadsadsadsa@gmail.com", "Hà Nội", "Phòng IT", "Nhân viên", "Hoạt động", "01/01/2023", "02/01/2023"),
                new EmployeeDT0("2", "EMP002", "Trần Thị B", "Nữ", "15/02/1992", "0918765432", "987654321", "bdsadsadsadsadsa@gmail.com", "Hồ Chí Minh", "Phòng HR", "Quản lý", "Hoạt động", "05/01/2023", "06/01/2023"),
                new EmployeeDT0("3", "EMP003", "Lê Văn C", "Nam", "20/03/1985", "0987654321", "111222333", "cđasadsadsad@gmail.com", "Đà Nẵng", "Phòng Kế Toán", "Kế toán trưởng", "Hoạt động", "10/01/2023", "11/01/2023"),
                new EmployeeDT0("4", "EMP004", "Phạm Thị D", "Nữ", "10/04/1995", "0971122334", "444555666", "ddsadsadsadsa@gmail.com", "Hải Phòng", "Phòng IT", "Nhân viên", "Nghỉ việc", "12/01/2023", "14/01/2023"),
                new EmployeeDT0("5", "EMP005", "Ngô Văn E", "Nam", "05/05/1993", "0933344455", "777888999", "edsadsadsads@gmail.com", "Cần Thơ", "Phòng Sales", "Trưởng phòng", "Hoạt động", "15/01/2023", "16/01/2023"),
                new EmployeeDT0("6", "EMP006", "Hoàng Thị F", "Nữ", "25/06/1991", "0956677889", "000111222", "fdsadsadsadsa@gmail.com", "Hà Nội", "Phòng Marketing", "Nhân viên", "Hoạt động", "17/01/2023", "18/01/2023"),
                new EmployeeDT0("7", "EMP007", "Vũ Văn G", "Nam", "30/07/1988", "0911223344", "333222111", "gdsadsdsdsadsa@gmail.com", "Hồ Chí Minh", "Phòng IT", "Quản lý", "Hoạt động", "19/01/2023", "20/01/2023"),
                new EmployeeDT0("8", "EMP008", "Đặng Thị H", "Nữ", "15/08/1994", "0944455566", "666555444", "hdsadsadsadsad@gmail.com", "Hải Dương", "Phòng HR", "Nhân viên", "Nghỉ việc", "21/01/2023", "22/01/2023"),
                new EmployeeDT0("9", "EMP009", "Phan Văn I", "Nam", "12/09/1987", "0922233344", "999888777", "idsadsadsadsa@gmail.com", "Quảng Ninh", "Phòng Kỹ Thuật", "Trưởng phòng", "Hoạt động", "23/01/2023", "24/01/2023"),
                new EmployeeDT0("10", "EMP010", "Lý Thị J", "Nữ", "01/10/1990", "0915566778", "123321456", "jdsadsdsadsa@gmail.com", "Vĩnh Phúc", "Phòng IT", "Nhân viên", "Hoạt động", "25/01/2023", "26/01/2023")
        );

        // Thêm cột hành động
        TableColumn<EmployeeDT0, Void> actionColumn = new TableColumn<>("Hành động");
        Callback<TableColumn<EmployeeDT0, Void>, TableCell<EmployeeDT0, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<EmployeeDT0, Void> call(final TableColumn<EmployeeDT0, Void> param) {
                final TableCell<EmployeeDT0, Void> cell = new TableCell<>() {
                    private final HBox actionBox = new HBox(10);


                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            actionBox.setAlignment(Pos.CENTER);
                            // Tạo icon Xem
                            MFXFontIcon viewIcon = new MFXFontIcon("fas-eye", 20);
                            viewIcon.setStyle("-fx-cursor: hand;");
                            viewIcon.setColor(Color.FORESTGREEN);
                            viewIcon.setOnMouseClicked(event -> {
                                loader = new FXMLLoader ();
                                loader.setLocation(getClass().getResource("/fxml/EmployeeDetail.fxml"));
                                try {
                                    loader.load();
                                } catch (IOException ex) {
                                    Logger.getLogger(EditEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                Parent parent = loader.getRoot();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(parent));
                                stage.initStyle(StageStyle.UTILITY);
                                stage.show();
                            });

                            // Tạo icon Sửa
                            MFXFontIcon editIcon = new MFXFontIcon("fas-pen-to-square", 20);
                            editIcon.setStyle("-fx-cursor: hand;");
                            editIcon.setColor(Color.BLUE);
                            editIcon.setOnMouseClicked(event -> {
                                loader = new FXMLLoader ();
                                loader.setLocation(getClass().getResource("/fxml/EditEmployee.fxml"));
                                try {
                                    loader.load();
                                } catch (IOException ex) {
                                    Logger.getLogger(EditEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                Parent parent = loader.getRoot();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(parent));
                                stage.initStyle(StageStyle.UTILITY);
                                stage.show();
                            });

                            // Tạo icon Xóa
                            MFXFontIcon deleteIcon = new MFXFontIcon("fas-trash-can", 20);
                            deleteIcon.setStyle("-fx-cursor: hand;");
                            deleteIcon.setColor(Color.RED);
                            deleteIcon.setOnMouseClicked(event -> {
                                Stage currentStage = (Stage) borderPane.getScene().getWindow();
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.initOwner(currentStage);
                                alert.initModality(Modality.WINDOW_MODAL);
                                alert.setTitle("Xác nhận xóa");
                                alert.setHeaderText("Bạn có chắc chắn muốn xóa người dùng này?");
                                alert.setContentText("Hành động này không thể hoàn tác.");

                                alert.showAndWait().ifPresent(response -> {
                                    if (response == ButtonType.OK) {
                                        System.out.println("Xóa nhân viên: " + getTableView().getItems().get(getIndex()).getFullname());
                                    }
                                });
                            });
                            actionBox.getChildren().addAll(viewIcon, editIcon, deleteIcon);
                            setGraphic(actionBox);
                        }
                    }
                };
                return cell;
            }
        };
        actionColumn.setCellFactory(cellFactory);
        actionColumn.setPrefWidth(120);
        // Thêm tất cả cột vào bảng
        paginated.getColumns().addAll(IDColumn, employeeCodeColumn, fullnameColumn,
                genderColumn, birthdayColumn, mobileColumn, cardIdColumn, emailColumn,
                addressColumn, departmentNameColumn, roleNameColumn,
                statusColumn, createDateColumn, updateDateColumn, actionColumn);

        // Set danh sách nhân viên
        paginated.setItems(data);

        addEmployee();
    }

    private void addEmployee() {
        addEmployeeBtn.setOnAction(event -> {
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/AddEmployee.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(AddEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        });
    }
}