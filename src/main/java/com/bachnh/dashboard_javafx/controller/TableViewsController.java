package com.bachnh.dashboard_javafx.controller;
import com.bachnh.dashboard_javafx.model.Device;
import com.bachnh.dashboard_javafx.model.Model;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialogBuilder;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import io.github.palexdev.materialfx.enums.ScrimPriority;
import io.github.palexdev.materialfx.filter.EnumFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.others.observables.When;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Comparator;
import java.util.Map;
import java.util.ResourceBundle;


public class TableViewsController implements Initializable {
    @FXML
    private MFXPaginatedTableView<Device> paginated;
    private MFXGenericDialog dialogContent;
    private MFXStageDialog dialog;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupPaginated();
        paginated.autosizeColumnsOnInitialization();
        When.onChanged(paginated.currentPageProperty())
                .then((oldValue, newValue) -> paginated.autosizeColumns())
                .listen();
    }
    private void setupPaginated() {
        // Khởi tạo các cột bảng
        MFXTableColumn<Device> idColumn = new MFXTableColumn<>("ID", false, Comparator.comparing(Device::getID));
        MFXTableColumn<Device> nameColumn = new MFXTableColumn<>("Name", false, Comparator.comparing(Device::getName));
        MFXTableColumn<Device> ipColumn = new MFXTableColumn<>("IP", false, Comparator.comparing(Device::getIP));
        MFXTableColumn<Device> ownerColumn = new MFXTableColumn<>("Owner", false, Comparator.comparing(Device::getOwner));
        MFXTableColumn<Device> stateColumn = new MFXTableColumn<>("State", false, Comparator.comparing(Device::getState));

        idColumn.setRowCellFactory(device -> new MFXTableRowCell<>(Device::getID));
        nameColumn.setRowCellFactory(device -> new MFXTableRowCell<>(Device::getName));
        ipColumn.setRowCellFactory(device -> new MFXTableRowCell<>(Device::getIP) {{
            setAlignment(Pos.CENTER_RIGHT);
        }});
        ownerColumn.setRowCellFactory(device -> new MFXTableRowCell<>(Device::getOwner));
        stateColumn.setRowCellFactory(device -> new MFXTableRowCell<>(Device::getState));

        // Thêm cột hành động
        MFXTableColumn<Device> actionColumn = new MFXTableColumn<>("Actions", false);
        actionColumn.setRowCellFactory(device -> {
            return new MFXTableRowCell<>(device1 -> "") {
                private final HBox actionBox;

                {
                    actionBox = new HBox(10);
                    actionBox.setAlignment(Pos.CENTER);

                    // Tạo icon Xem
                    MFXFontIcon viewIcon = new MFXFontIcon("fas-eye", 24);
                    viewIcon.setStyle("-fx-cursor: hand;");
                    viewIcon.setColor(Color.FORESTGREEN);
                    viewIcon.setOnMouseClicked(event -> {
                        System.out.println("Xem chi tiết thiết bị: " + device.getName());
                    });

                    // Tạo icon Sửa
                    MFXFontIcon editIcon = new MFXFontIcon("fas-pen-to-square", 24);
                    editIcon.setStyle("-fx-cursor: hand;");
                    editIcon.setColor(Color.BLUE);
                    editIcon.setOnMouseClicked(event -> {
                        System.out.println("Sửa thiết bị: " + device.getName());
                    });

                    // Tạo icon Xóa
                    MFXFontIcon deleteIcon = new MFXFontIcon("fas-trash-can", 24);
                    deleteIcon.setStyle("-fx-cursor: hand;");
                    deleteIcon.setColor(Color.RED);
                    deleteIcon.setOnMouseClicked(event -> {
                        Stage stage = new Stage();
                        MFXFontIcon infoIcon = new MFXFontIcon("fas-circle-info", 18);
                        Platform.runLater(() -> {
                            dialogContent = MFXGenericDialogBuilder.build()
                                    .setHeaderIcon(infoIcon)
                                    .setHeaderText("This is a generic info dialog")
                                    .setContentText(Model.ipsum)
                                    .makeScrollable(true)
                                    .get();
                            dialog = MFXGenericDialogBuilder.build(dialogContent)
                                    .toStageDialogBuilder()
                                    .initOwner(stage)
                                    .initModality(Modality.APPLICATION_MODAL)
                                    .setDraggable(true)
                                    .setTitle("Dialogs Preview")
                                    .setScrimPriority(ScrimPriority.WINDOW)
                                    .setScrimOwner(true)
                                    .get();

                            dialogContent.addActions(
                                    Map.entry(new MFXButton("Confirm"), e -> {
                                    }),
                                    Map.entry(new MFXButton("Cancel"), e -> dialog.close())
                            );

                            dialogContent.setMaxSize(400, 200);
                            dialog.showDialog();
                        });
                    });
                    actionBox.getChildren().addAll(viewIcon, editIcon, deleteIcon);
                    setGraphic(actionBox);
                }

                @Override
                public void update(Device item) {
                    super.update(item);
                    if (item == null) {
                        setGraphic(null);
                    } else {
                        setGraphic(actionBox);
                    }
                }
            };
        });

        // Thêm tất cả cột vào bảng
        paginated.getTableColumns().addAll(idColumn, nameColumn, ipColumn, ownerColumn, stateColumn, actionColumn);
        paginated.getFilters().addAll(
                new IntegerFilter<>("ID", Device::getID),
                new StringFilter<>("Name", Device::getName),
                new StringFilter<>("IP", Device::getIP),
                new StringFilter<>("Owner", Device::getOwner),
                new EnumFilter<>("State", Device::getState, Device.State.class)
        );

        // Set danh sách thiết bị
        paginated.setItems(Model.devices);
    }

}

