package lk.ijse.ormCoursework.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLoginFormController {
    public AnchorPane root;
    public TextField txtUserName;
    public PasswordField txtPassword;
    private String name="Aravinda";
    private String password="1234";

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        String userPass=txtPassword.getText ();
        String userName=txtUserName.getText ();
        if (userName.equals (name) && userPass.equals (password)){
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/globalForm.fxml"))));
            stage.centerOnScreen();

        }else{
            new Alert(Alert.AlertType.ERROR, "INVALID ADMIN ATHUENTICATION").show ();
            txtPassword.clear ();
            txtUserName.clear ();
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"))));
        stage.centerOnScreen();
    }
}
