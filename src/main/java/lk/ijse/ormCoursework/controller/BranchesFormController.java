package lk.ijse.ormCoursework.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.AnchorPane;

public class BranchesFormController {
    public AnchorPane root;
    public JFXTextField txtId;
    public JFXTextField txtTitle;
    public JFXTextField txtLocation;
    public JFXComboBox cmbStatus;
    public TableView tbLBranch;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colLocation;
    public TableColumn colStatus;

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }
}
