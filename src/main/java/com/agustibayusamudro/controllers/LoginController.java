package com.agustibayusamudro.controllers;

import java.io.IOException;

import com.agustibayusamudro.dto.LoginDTO;
import com.agustibayusamudro.services.AuthService;
import com.agustibayusamudro.services.impl.AuthServiceImpl;

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
import javafx.stage.Stage;

public class LoginController {
    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;

    private AuthService authService = new AuthServiceImpl();

    @FXML
    private void login(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        LoginDTO loginDTO = new LoginDTO(username, password);
        boolean isSuccess = authService.login(loginDTO);
        
        if(isSuccess) {
            try {                
                navigate(event,"/com/agustibayusamudro/view/MenuUtama.fxml");
            } catch (IOException e) {
                e.printStackTrace();
                showError("Error", "Tidak dapat membuka Menu Utama.");
            }
        } else {
            showError("Login Gagal", "Username atau Password salah.");
        }
    }

    private void navigate(ActionEvent event, String path) throws IOException {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource(path)
        );

        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

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


