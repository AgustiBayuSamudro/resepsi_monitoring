package com.agustibayusamudro.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MenuUtamaController {
    @FXML
    private void lakilaki(ActionEvent event) {
        try {
            navigate(event, "/com/agustibayusamudro/view/FormPihakLakilaki.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Error", "Gagal navigate ke form pihak laki-laki");
        }        
    }
    @FXML
    private void perempuan(ActionEvent event) {
        try {
            navigate(event, "/com/agustibayusamudro/view/FormPihakPerempuan.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Error", "Gagal navigate ke form pihak perempuan");
        }
    }
    @FXML
    private void undangan(ActionEvent event) {
        try {
            navigate(event, "/com/agustibayusamudro/view/FormUndangan.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Error", "Gagal navigate ke form undangan");
        }
    }
    @FXML
    private void dashbord(ActionEvent event) {
        try {
            navigate(event, "/com/agustibayusamudro/view/MenuUtama.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Error", "Gagal navigate ke form menu utama");
        }
    }
    @FXML
    private void laporan(ActionEvent event) {
        try {
            navigate(event, "/com/agustibayusamudro/view/FormLaporan.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Error", "Gagal navigate ke form laporan");
        }
    }
    @FXML
    private void logout(ActionEvent event) {
        try {
            navigate(event, "/com/agustibayusamudro/view/Login.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Error", "Gagal logout");
        }
    }

    private void navigate(ActionEvent event, String path) throws IOException {
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