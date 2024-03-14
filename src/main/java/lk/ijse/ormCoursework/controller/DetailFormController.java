package lk.ijse.ormCoursework.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DetailFormController {
    public TableView tblDetail;
    public TableColumn colId;
    public TableColumn colGetDate;
    public TableColumn colReturnDate;
    public TableColumn colUserId;
    public TableColumn colBookId;
    public JFXTextField txtId;
    public DatePicker dpGetDate;
    public DatePicker dpReturnDate;
    public JFXComboBox cmbUserId;
    public JFXComboBox cmbBookId;

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }
}
