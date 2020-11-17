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

/**
 * The <code>LoginController</code> contains the entire logic of the login view.
 * It determines how to react to events.
 */
public class LoginController {

    @FXML
    private TextField intID;
    @FXML
    private TextField txtPassword;
    @FXML
    private BorderPane loginBorderPane;

    private UserDAO dao;
    private User user;

    /**
     * handles the login of users
     */
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

        String userName = user.getFirstname()+" "+user.getSurname();

        try {
            if (user.getPassword().equals(textPassword)) {
                showMainWindow();
                JOptionPane.showMessageDialog(null, "Willkommen zurück, "+userName+".");
            } else {
                txtPassword.clear();
                JOptionPane.showMessageDialog(null, "Passwort oder Benutzer-ID ist falsch");
            }
        } catch (NullPointerException e2) {
            txtPassword.clear();
            JOptionPane.showMessageDialog(null, "Benutzer-ID existiert nicht");
        }
    }

    /**
     * shows the main window
     */
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
