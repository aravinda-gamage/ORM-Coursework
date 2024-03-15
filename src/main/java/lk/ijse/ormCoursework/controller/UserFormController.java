package lk.ijse.ormCoursework.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.ormCoursework.bo.custom.UserBo;
import lk.ijse.ormCoursework.bo.impl.UserBoImpl;
import lk.ijse.ormCoursework.dto.BookDto;
import lk.ijse.ormCoursework.dto.UserDto;
import lk.ijse.ormCoursework.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserFormController {
    public JFXTextField txtId;
    public JFXTextField txtUserName;
    public JFXTextField txtEmail;
    public JFXTextField txtPassword;
    public JFXTextField txtRePassword;
    public TableView tblUser;
    public TableColumn colId;
    public TableColumn colUserName;
    public TableColumn colEmail;

    UserBo userBo=new UserBoImpl();

    public void initialize(){
        colId.setCellValueFactory (new PropertyValueFactory<>("userId"));
        colUserName.setCellValueFactory (new PropertyValueFactory<>("userName"));
        setUserId();
        loadAllUsers();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String pass=txtPassword.getText ();
        String rePass=txtRePassword.getText ();
        String userId = txtId.getText ();
        String userName = txtUserName.getText ();
        String email=txtEmail.getText ();


        if (checkDuplidate ()){
            // if (checkValidation ()){
            if(pass.equals (rePass)){
                userBo.saveUser (new UserDto(
                        userId,
                        userName,
                        pass
                ));
                new Alert(Alert.AlertType.CONFIRMATION, "USER ACCOUNT CREATED SUCCUSS").show ();
                clearFeilds();
                initialize();

            }else {
                new Alert (Alert.AlertType.ERROR, "Check your Password and Try Again").show ();
            }

        }else{
            new Alert(Alert.AlertType.ERROR, "THIS USER ID ALREADY GET").show ();
            clearFeilds ();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        UserDto userDto = new UserDto (
                txtId.getText(),
                txtUserName.getText(),
                txtPassword.getText()
        );

        boolean isUpdate = userBo.updateUser (userDto);

        if (isUpdate) {
            new Alert (Alert.AlertType.INFORMATION, " Update Complete...!").show ();
            // tblRoom.getItems ().clear ();
            clearFeilds();
            loadAllUsers();
            initialize();
            //setBookId ();
        } else {
            new Alert (Alert.AlertType.ERROR, "Something went Wrong").show ();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        UserDto userDto = new UserDto (
                txtId.getText (),
                txtUserName.getText (),
                txtPassword.getText ()
        );

        boolean isDeleted = userBo.deleteUser(userDto);

        if (isDeleted) {
            new Alert (Alert.AlertType.INFORMATION, "Delete Succes..!").show ();
            tblUser.getItems ().clear ();
            clearFeilds();
            loadAllUsers ();
            setUserId();
            initialize();
        } else {
            new Alert (Alert.AlertType.ERROR, "Something went Wrong").show ();
        }
    }

    private void loadAllUsers() {
        try {
            List allUser = userBo.loadAll ();
            ObservableList observableList = FXCollections.observableArrayList (allUser);
            tblUser.setItems (observableList);

        } catch (Exception e) {
            System.out.println (e);
        }
    }
    public String nextUserID() {
        Session session = SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction = session.beginTransaction ();

        Query query = session.createQuery ("select userId from user order by userId desc");

        String nextId = "U001";

        if (query.list ().size () == 0) {
            return nextId;
        } else {
            String id = (String) query.list ().get (0);

            String[] SUs = id.split ("U00");

            for (String a : SUs) {
                id = a;
            }

            int idNum = Integer.valueOf (id);

            id = "U00" + (idNum + 1);

            transaction.commit ();
            session.close ();

            return id;
        }
    }

    private void setUserId() {
        String userID=nextUserID ();
        txtId.setText (userID);
    }

    private void clearFeilds() {
        txtId.clear();
        txtUserName.clear();
        txtEmail.clear();
        txtPassword.clear();
        txtRePassword.clear();
    }


    private boolean checkDuplidate() {
        String userId=txtId.getText ();
        List<UserDto> allRoom = userBo.loadAll ();
        for (UserDto u : allRoom) {
            if (userId.equals (u.getUserId ())){
                return false;
            }
        }
        return  true;

    }
}
