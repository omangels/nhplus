package controller;

import datastorage.DAOFactory;
import datastorage.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.SQLException;

import model.User;

import javax.swing.*;

public class LoginController {

    @FXML
    private TextField intID;
    @FXML
    private TextField txtPassword;
    @FXML
    private BorderPane loginBorderPane;

    private UserDAO dao;
    private User user;

    @FXML
    private void handleLogin(ActionEvent e) {
        this.dao = DAOFactory.getDAOFactory().createUserDAO();
        int id = Integer.parseInt(intID.getText());
        String textPassword = txtPassword.getText();

        try {
            this.user = dao.read(id);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        if(user.getPassword().equals(textPassword)){
            showMainWindow();
        } else {
            JOptionPane.showMessageDialog(null, "Falsches Passwort");
        }
    }

    @FXML
    private void showMainWindow() {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/MainWindowView.fxml"));
        try {
            loginBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        MainWindowController controller = loader.getController();
    }
}
