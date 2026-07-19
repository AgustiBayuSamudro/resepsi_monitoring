package com.agustibayusamudro;

import com.agustibayusamudro.controllers.LoginController;
import com.agustibayusamudro.database.DatabaseConnection;
import com.agustibayusamudro.repositories.UndanganRepository;
import com.agustibayusamudro.repositories.UserRepository;
import com.agustibayusamudro.repositories.impl.UndanganRepositoryImpl;
import com.agustibayusamudro.repositories.impl.UserRepositoryImpl;
import com.agustibayusamudro.services.UndanganService;
import com.agustibayusamudro.services.UserService;
import com.agustibayusamudro.services.impl.UndanganServiceImpl;
import com.agustibayusamudro.services.impl.UserServiceImpl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static UserService userService;
    private static UndanganService undanganService;

    public static UserService getUserService() { 
        return userService; 
    }
    public static UndanganService getUndanganService() { 
        return undanganService; 
    }

    @Override
    public void start(Stage stage) throws Exception {        
        DatabaseConnection dbConnection = new DatabaseConnection();
        UserRepository userRepository = new UserRepositoryImpl(dbConnection);
        UndanganRepository undanganRepository = new UndanganRepositoryImpl(dbConnection);

        userService = new UserServiceImpl(userRepository);
        undanganService = new UndanganServiceImpl(undanganRepository);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/agustibayusamudro/view/Login.fxml"));

        loader.setControllerFactory(clazz -> {
            if (clazz == LoginController.class) {
                return new LoginController(userService);
            }
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        Parent root = loader.load();
        stage.setResizable(true);
        stage.setTitle("SISTEM RESEPSI");
        stage.setScene(new Scene(root));        
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}