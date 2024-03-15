package lk.ijse.ormCoursework.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.ormCoursework.bo.custom.DetailBo;
import lk.ijse.ormCoursework.bo.impl.DetailBoImpl;
import lk.ijse.ormCoursework.dto.BookDto;
import lk.ijse.ormCoursework.dto.DetailDto;
import lk.ijse.ormCoursework.dto.UserDto;
import lk.ijse.ormCoursework.dto.tm.DetailTm;

import java.util.Calendar;
import java.util.List;

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

    DetailBo detailBo=new DetailBoImpl();
    public static ObservableList<UserDto> userId = FXCollections.observableArrayList ();

    public void initialize(){
        colId.setCellValueFactory (new PropertyValueFactory<>("dId"));
        colGetDate.setCellValueFactory (new PropertyValueFactory<> ("gDate"));
        colReturnDate.setCellValueFactory (new PropertyValueFactory<> ("rDate"));
        colUserId.setCellValueFactory (new PropertyValueFactory<> ("userId"));
        colBookId.setCellValueFactory (new PropertyValueFactory<> ("bookId"));

        setId();
        loadAllDetils();
    }
    public void btnSaveOnAction(ActionEvent actionEvent) {
        UserDto userdto = getUserDetail();
        BookDto bookdto = getBookDetail();
        String detId =txtId.getText();
        java.sql.Date sqlSate = new java.sql.Date (Calendar.getInstance ().getTime ().getTime ());
        java.sql.Date sqlEDate = new java.sql.Date (Calendar.getInstance ().getTime ().getTime ());

        if (checkDuplicate()) {
            boolean isSaveDetail = detailBo.saveDetail (
                    new DetailDto(
                            detId,
                            sqlSate,
                            sqlEDate,
                            userdto,
                            bookdto
                    ));
            if (isSaveDetail) {
                BookDto book = getBookDetail();

                loadAllDetils();
            }
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String uid = cmbUserId.getValue ().toString ();
        String bid = cmbBookId.getValue ().toString ();
        String dId = txtId.getText ();
        UserDto userdto=getUserDetail();
        BookDto bookdto=getBookDetail();
        java.sql.Date sqlSDate = new java.sql.Date (Calendar.getInstance ().getTime ().getTime ());
        java.sql.Date sqlEDate = new java.sql.Date (Calendar.getInstance ().getTime ().getTime ());
        try {
            boolean isUpdate = detailBo.updateDetail(
                    new DetailDto (
                            dId,
                            sqlSDate,
                            sqlEDate,
                            userdto,
                            bookdto
                    ));
            loadAllDetils();
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        java.sql.Date sqlSDate = new java.sql.Date (Calendar.getInstance ().getTime ().getTime ());
        java.sql.Date sqlEDate = new java.sql.Date (Calendar.getInstance ().getTime ().getTime ());
        UserDto userdto=getUserDetail();
        BookDto bookdto=getBookDetail();
        DetailDto detaildto=new DetailDto(
                txtId.getText(),
                sqlSDate,
                sqlEDate,
                userdto,
                bookdto

        );

        boolean isDeleted = detailBo.deleteDetail(detaildto);

        if (isDeleted) {
            new Alert (Alert.AlertType.INFORMATION, "Detail Delete Succuss").show ();
            tblDetail.getItems ().clear ();
            loadAllDetils();
            setId();
        } else {
            new Alert (Alert.AlertType.ERROR, "Something went Wrong").show ();
        }
    }

    private void loadAllDetils() {
        try {
            List<DetailDto> alldetail = detailBo.loadAll ();

            ObservableList<DetailTm> resList = FXCollections.observableArrayList ();

            for (DetailDto dto : alldetail) {
                resList.add (new DetailTm (
                        dto.getDId(),
                        dto.getGDate().toString(),
                        dto.getRDate().toString(),
                        dto.getUser().getUserId(),
                        dto.getUser().getPassword(),
                        dto.getBook().getBId(),
                        dto.getBook().getTitle()
                ));
            }

            tblDetail.setItems (resList);

            System.out.println (resList);

        } catch (Exception e) {
            System.out.println (e);
        }
    }

    private boolean checkDuplicate() {
        String resId = txtId.getText ();
        List<DetailDto> list = detailBo.loadAll ();

        for (DetailDto dto : list) {
            if (resId.equals (dto.getDId())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Detail ADDED SUCCUSS").show ();
                return false;
            }
        }
        return true;
    }

    private BookDto getBookDetail() {
        try {
            String bookId = cmbBookId.getValue ().toString ();
            System.out.println ("This IS Book ID"+bookId);
            return detailBo.getBook(bookId);
        } catch (Exception e) {
            System.out.println (e);
        }
        return null;
    }

    private UserDto getUserDetail() {
        String stId = cmbUserId.getValue ().toString ();
        return detailBo.getUser(stId);
    }

    private void setId() {
        List<String> uId = detailBo.getUserIds();
        ObservableList use = FXCollections.observableArrayList (uId);
        cmbUserId.setItems (use);

        List<String> bookId = detailBo.getBookIds ();
        ObservableList room = FXCollections.observableArrayList (bookId);
        cmbBookId.setItems (room);

    }
}
