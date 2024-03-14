package lk.ijse.ormCoursework.controller;

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

    private void updateClock(){
        lblTime.setText(DateTimeUtil.timeNow());
    }

    public void initialize(){
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),event -> updateClock()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        lblDate.setText(DateTimeUtil.dateNow());
    }

    public void btnUserOnAction(ActionEvent actionEvent) {
    }

    public void btnBookOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/bookForm.fxml"));
        Parent load = fxmlLoader.load();
        BookFormController controller = fxmlLoader.getController();
        root2.getChildren().clear();
        root2.getChildren().add(load);
    }

    public void btnDetailOnAction(ActionEvent actionEvent) {
    }

    public void btnBranchesOrdOnAction(ActionEvent actionEvent) {
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) {
    }
}
