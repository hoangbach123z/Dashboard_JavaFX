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

//    private ConfigurableApplicationContext springBootContext;
    //    private Parent root;
//    private FXMLLoader fxmlLoader;
//    @Override
//    public void init() throws Exception {
//        CSSFX.start();
//        UserAgentBuilder.builder()
//                .themes(JavaFXThemes.CASPIAN)
//                .themes(MaterialFXStylesheets.forAssemble(true))
//                .setDeploy(true)
//                .setResolveAssets(true)
//                .build()
//                .setGlobal();
//        springBootContext = SpringApplication.run(AccessControlSystemApplication.class);
//        fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
//        fxmlLoader.setControllerFactory(springBootContext::getBean);
//    }
//    @Override
//    public void start(Stage primaryStage) throws Exception {
////        fxmlLoader.setControllerFactory(c->new LoginController());
//        root = fxmlLoader.load();
//        Scene scene = new Scene(root);
//        scene.setFill(Color.WHITE);
//        primaryStage.initStyle(StageStyle.TRANSPARENT);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Đăng Nhập");
//        primaryStage.getIcons().add(new Image("/assets/icon.png"));
//        primaryStage.show();
//    }
//    @Override
//    public void stop() throws Exception {
//        springBootContext.stop();
//    }
//    public static void main(String[] args) {
//
//        launch(AccessControlSystemApplication.class,args);
//    }
    @Override
    public void start(Stage primaryStage) throws Exception {
//        var context =  SpringApplication.run(AccessControlSystemApplication.class);
//        CSSFX.start();
//        UserAgentBuilder.builder()
//                .themes(JavaFXThemes.CASPIAN)
//                .themes(MaterialFXStylesheets.forAssemble(true))
//                .setDeploy(true)
//                .setResolveAssets(true)
//                .build()
//                .setGlobal();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Dashboard.fxml"));
        fxmlLoader.setControllerFactory(c->new DashboardController(primaryStage));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.setFill(Color.WHITE);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Đăng Nhập");
        primaryStage.show();
    }

}

