package lk.ijse.ormCoursework.controller;

import com.jfoenix.controls.JFXCheckBox;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {
    public AnchorPane root;
    public TextField txtUserName;
    public PasswordField txtPassword;
    public TextField txtShowPassword;
    public Button btnSPass;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtShowPassword.setVisible(false);
    }
    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/globalForm.fxml"))));
        stage.centerOnScreen();
    }

    public void btnExitOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void txtPasswordOnKeyTyped(KeyEvent keyEvent) {
        txtShowPassword.textProperty().bind(Bindings.concat(txtPassword.getText()));
    }

    public void btnSPassOnMousePressed(MouseEvent mouseEvent) {
        txtShowPassword.setVisible(true);
        txtShowPassword.textProperty().bind(Bindings.concat(txtPassword.getText()));
    }

    public void btnSPassOnMouseReleased(MouseEvent mouseEvent) {
        txtShowPassword.setVisible(false);
    }
}
