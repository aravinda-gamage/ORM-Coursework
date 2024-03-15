package lk.ijse.ormCoursework.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.ormCoursework.util.DateTimeUtil;

import java.io.IOException;

public class GlobalFormController {
    public AnchorPane root1;
    public AnchorPane root2;
    public Label lblDate;
    public Label lblTime;
    public ImageView imgBDark;
    public AnchorPane root3;
    public JFXButton btnUser;
    public JFXButton btnBook;
    public JFXButton btnDetail;
    public JFXButton btnBranches;
    public boolean status;

    private void updateClock(){
        lblTime.setText(DateTimeUtil.timeNow());
    }

    public void initialize(){
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),event -> updateClock()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        lblDate.setText(DateTimeUtil.dateNow());
    }

    public void btnUserOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/userForm.fxml"));
        Parent load = fxmlLoader.load();
        UserFormController controller = fxmlLoader.getController();
        root2.getChildren().clear();
        root2.getChildren().add(load);
    }

    public void btnBookOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/bookForm.fxml"));
        Parent load = fxmlLoader.load();
        BookFormController controller = fxmlLoader.getController();
        root2.getChildren().clear();
        root2.getChildren().add(load);
    }

    public void btnDetailOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/detailForm.fxml"));
        Parent load = fxmlLoader.load();
        DetailFormController controller = fxmlLoader.getController();
        root2.getChildren().clear();
        root2.getChildren().add(load);
    }

    public void btnBranchesOrdOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminLoginForm.fxml"));
        Parent load = fxmlLoader.load();
        AdminLoginFormController controller = fxmlLoader.getController();
        root2.getChildren().clear();
        root2.getChildren().add(load);
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) root1.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"))));
        stage.centerOnScreen();
    }
}
