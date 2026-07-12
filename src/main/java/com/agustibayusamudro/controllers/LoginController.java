package com.agustibayusamudro.controllers;

import com.agustibayusamudro.dto.LoginDTO;
import com.agustibayusamudro.services.AuthService;
import com.agustibayusamudro.services.impl.AuthServiceImpl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

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
            System.out.println("Login Berhasil!");
        } else {
            showError("Login Gagal", "Username atau Password salah.");
        }
    }

    private void showError(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}


