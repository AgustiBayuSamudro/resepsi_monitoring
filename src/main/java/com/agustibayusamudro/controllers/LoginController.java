package com.agustibayusamudro.controllers;

import java.io.IOException;

import org.kordamp.ikonli.javafx.FontIcon;

import com.agustibayusamudro.dto.LoginDTO;
import com.agustibayusamudro.services.UserService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController {
    private final UserService userService;
    public LoginController(UserService userService) {
        this.userService = userService;
    }
    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private TextField txtPasswordShow;
    @FXML private FontIcon toggleIcon;

    private boolean isPasswordShown = false;

    @FXML
    private void login(ActionEvent event) {
        String username = txtUsername.getText();
        String password = isPasswordShown ? txtPasswordShow.getText() : txtPassword.getText();

        LoginDTO login = new LoginDTO(username, password);
        boolean isSuccess = userService.login(login);
        if(isSuccess) {
            try {
                navigation(event, "/com/agustibayusamudro/view/MenuUtama.fxml");
            } catch (IOException e) {
                e.printStackTrace();
                showError("Error", "Gagal memuat halaman utama");
            }
        } else {
            showError("Login Gagal", "username atau password salah");
        }
    }

    @FXML
    void handleTogglePassword(MouseEvent event) {
        if(isPasswordShown) {
            txtPassword.setText(txtPasswordShow.getText());

            txtPassword.setVisible(true);
            txtPassword.setManaged(true);
            txtPasswordShow.setVisible(false);
            txtPasswordShow.setManaged(false);

            toggleIcon.setIconLiteral("fa-eye");
            isPasswordShown = false;
        } else {
            txtPasswordShow.setText(txtPassword.getText());
            txtPassword.setVisible(false);
            txtPassword.setManaged(false);
            txtPasswordShow.setVisible(true);
            txtPasswordShow.setManaged(true);

            toggleIcon.setIconLiteral("fa-eye-slash");
            isPasswordShown = true;
        }
    }

    private void navigation(ActionEvent event, String path)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
    }

    private void showError(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}