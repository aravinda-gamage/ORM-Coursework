package lk.ijse.ormCoursework.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class BookFormController implements Initializable {
    public AnchorPane root;
    public JFXButton btnSave;
    public TableView tblBook;
    public TableColumn colId;
    public TableColumn colTitle;
    public TableColumn colAuthor;
    public TableColumn colGenre;
    public TableColumn colStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveOnDragEnt(MouseEvent mouseEvent) {
    }
}
