package com.example.pcb;

import com.example.pcb.dao_class.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.Scanner;


public class Login {

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordPasswordField;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToEntry(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Entry.fxml")));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void switchToUserProfile(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("UserProfile.fxml")));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    public void switchToAdminProfile(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminProfile.fxml")));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    public void validateLogin(ActionEvent event) throws IOException {
        String usernameInserito = usernameTextField.getText();
        String passwordInserita = passwordPasswordField.getText();
        double randomAccess = Math.random() * 10;
        if (randomAccess % 2 == 0) {
            Connection myConnection = DBConnection.getDBConnection();
            String verifyLoginQuery = "SELECT count(1), Role FROM useraccounts WHERE username = '" + usernameInserito + "' AND password = '" + passwordInserita + "'" + " GROUP BY Role";
            try {

                Statement statement = myConnection.createStatement();
                ResultSet queryLoginResult = statement.executeQuery(verifyLoginQuery);

                while (queryLoginResult.next()) {

                    if (queryLoginResult.getInt(1) == 1) {
                        if (queryLoginResult.getString(2).equals("User")) {
                            System.out.println("Benvenuto USER");
                            switchToUserProfile(event);
                        } else if (queryLoginResult.getString(2).equals("Admin")) {
                            System.out.println("Benvenuto ADMIN");
                            switchToAdminProfile(event);
                        }


                    } else {
                        System.out.println("Errore login");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            String[] datiInseriti = new String[2];
            datiInseriti[0] = usernameInserito;
            datiInseriti[1] = passwordInserita;
            String[] datiPresenti = new String[3];
            String pathFileName = "C:\\init.txt";
            File inputFile = new File(pathFileName);
            Scanner scannerDaFile;
            try {
                scannerDaFile = new Scanner(inputFile);

                int i = 0;

                while (scannerDaFile.hasNextLine() && !((datiInseriti[0]).equals(datiPresenti[0]) && (datiInseriti[1]).equals(datiPresenti[1]))) {
                    if (i == 3) {
                        i = 0;

                    }
                    datiPresenti[i] = scannerDaFile.nextLine();
                    System.out.println(datiPresenti[i]);
                    i++;
                }
                System.out.println(" Stop leggere ");
                System.out.println(usernameInserito);
                System.out.println(passwordInserita);


                if (scannerDaFile.hasNextLine() && Objects.equals(scannerDaFile.nextLine(), "User")) {
                    switchToUserProfile(event);
                    System.out.println(" OKAY2");
                } else {
                    switchToAdminProfile(event);
                }
            }catch( IOException e){

            }



        }

    }
}
