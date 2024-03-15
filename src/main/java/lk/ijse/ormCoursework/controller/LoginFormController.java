package lk.ijse.ormCoursework.controller;

import com.jfoenix.controls.JFXCheckBox;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.ormCoursework.bo.custom.UserBo;
import lk.ijse.ormCoursework.bo.impl.UserBoImpl;
import lk.ijse.ormCoursework.dto.UserDto;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {
    public AnchorPane root;
    public TextField txtUserName;
    public PasswordField txtPassword;
    public TextField txtShowPassword;
    public Button btnSPass;
    UserBo userBo=new UserBoImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtShowPassword.setVisible(false);
    }
    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        if (checkUserDetail()) {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/globalForm.fxml"))));
            stage.centerOnScreen();
        }else {
            Alert.AlertType type = Alert.AlertType.INFORMATION;
            Alert alert = new Alert(type, "");
            alert.getDialogPane().setContentText("Please check details and try again");
            alert.showAndWait();
        }
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

    private boolean checkUserDetail() {
        String userName = txtUserName.getText ();
        String pass=txtPassword.getText ();

        List<UserDto> userList = userBo.loadAll ();

        for (UserDto dto : userList) {
            if(dto.getUserName ().equals (userName) && dto.getPassword ().equals (pass)){
                return true;
            }
        }
        return false;
    }

    public void btnAdminLoginOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/adminLoginForm.fxml"))));
        stage.centerOnScreen();
    }
}
