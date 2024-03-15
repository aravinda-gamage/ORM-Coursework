package lk.ijse.ormCoursework.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.ormCoursework.bo.custom.BranchesBo;
import lk.ijse.ormCoursework.bo.impl.BranchesBoImpl;
import lk.ijse.ormCoursework.dto.BranchesDto;
import javafx.collections.ObservableList;
import lk.ijse.ormCoursework.entity.Branches;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
    BranchesBo branchesBo=new BranchesBoImpl();

    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("brId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("bname"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("bstatus"));

        try {

            ArrayList<BranchesDto> branch = branchesBo.getAllBranches();

            tbLBranch.setItems(FXCollections.observableArrayList(branch.stream().map(br -> {
                return new BranchesDto(
                        br.getBrId(),
                        br.getBName(),
                        br.getLocation(),
                        br.getBStatus()

                );
            }).collect(Collectors.toList())));

        }catch (Exception e){
            System.out.println(e);
        }

        setStatus();
    }

    private void setStatus() {
        ObservableList<String> data = FXCollections.observableArrayList ("Open", "Close");
        cmbStatus.setItems (data);
    }
    public void btnSaveOnAction(ActionEvent actionEvent) {
        boolean validate = validateBranches();
        if (validate) {
            branchesBo.saveBranches(new BranchesDto(
                    txtId.getText(),
                    txtTitle.getText(),
                    txtLocation.getText(),
                    cmbStatus.getValue().toString()

            ));

            new Alert(Alert.AlertType.CONFIRMATION, "Branch Added Successfully").show();
            clearText();

            initialize();
        } else {
            new Alert(Alert.AlertType.ERROR, "ENTER RIGHT DETAILS..!").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        branchesBo.updateBranches(new BranchesDto(
                txtId.getText(),
                txtTitle.getText(),
                txtLocation.getText(),
                cmbStatus.getValue().toString()
        ));

        new Alert(Alert.AlertType.CONFIRMATION,"Branch Updated Successfully").show();


        initialize();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        branchesBo.deleteBranch(new Branches(
                txtId.getText(),
                txtTitle.getText(),
                txtLocation.getText(),
                cmbStatus.getValue().toString()
        ));

        new Alert(Alert.AlertType.CONFIRMATION,"Branch Delete Successfully").show();
        initialize();
    }

    private void clearText() {
        txtId.clear();
        txtTitle.clear();
        txtLocation.clear();
    }

    private boolean validateBranches() {
        String id_value=txtId.getText();
        Pattern complie= Pattern.compile("[B][0-9]{3}");
        Matcher matcher=complie.matcher(id_value);
        boolean matches=matcher.matches();
        if (!matches){
            new Alert(Alert.AlertType.ERROR,"INVALID BRANCH ID").show();
            return  false;
        }
        String location=txtLocation.getText();
        Pattern compile1 = Pattern.compile("[A-Za-z]{4,}");
        Matcher matcher1=compile1.matcher(location);
        boolean isAddress=matcher1.matches();
        if (!isAddress){
            new Alert(Alert.AlertType.ERROR,"WRONG ADDRSS TYPE").show();
        }
        String nameText=txtTitle.getText();
        boolean isnameValid= Pattern.compile("[A-Za-z]{3,}").matcher(nameText).matches();

        if (!isnameValid){
            new Alert(Alert.AlertType.ERROR,"WRONG NAME TYPE").show();
        }

        return true;

    }
}
