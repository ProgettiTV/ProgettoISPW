package com.example.pcb.gui_class;

import com.example.pcb.bean_class.BeanConferma;
import com.example.pcb.bean_class.BeanMostraResoconto;
import com.example.pcb.DomandeUtente;
import com.example.pcb.exception.DaoException;
import com.example.pcb.exception.QueryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class ConfermaRisposteGUI {

    private DomandeUtente riferimentoCapplicativo;
    private ComponentiGUI componentiGUI;
    private BeanMostraResoconto mostraResoconto;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField budgetTextField;

    @FXML
    private TextField utilizzoTextField;

    public ConfermaRisposteGUI(DomandeUtente istanzaCA) {
        this.riferimentoCapplicativo=istanzaCA;
    }


    public ConfermaRisposteGUI() {

    }

    public void switchToComponenti(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Componenti.fxml"));

        loader.setControllerFactory(f -> componentiGUI);

        scene = new Scene(loader.load());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        componentiGUI.prontoPerStampa();

    }

    public void switchToUserProfile(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("UserProfile.fxml")));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    public void switchToEntry(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Entry.fxml")));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void switchToBudget(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Budget.fxml")));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    public void vaiAComponenti(ActionEvent ae) throws IOException /*throws IOException, SQLException*/ {


        String risposta = ((Button)ae.getSource()).getText();

        BeanConferma beanConferma = new BeanConferma(risposta);

        Alert alert1 = new Alert(Alert.AlertType.NONE);
        alert1.setTitle("mostro errore a utente");
        alert1.setContentText("errore IO");
        alert1.show();

        if (Objects.equals(risposta, "Conferma")){

            try {
                try {
                    riferimentoCapplicativo.prendC(beanConferma);
                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.NONE);
                    alert.setTitle("mostro errore a utente");
                    alert.setContentText("errore IO");
                    alert.show();
                } catch (SQLException e) {
                    Alert alert = new Alert(Alert.AlertType.NONE);
                    alert.setTitle("mostro errore a utente");
                    alert.setContentText("errore SQL");
                    alert.show();
                } catch (QueryException e) {
                    throw new RuntimeException(e);
                }
            } catch (DaoException e) {
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setTitle("mostro errore a utente");
                alert.setContentText("dao errore");
                alert.show();
            }

            this.componentiGUI=new ComponentiGUI();

            riferimentoCapplicativo.prendiCGComponenti(componentiGUI);

            switchToComponenti(ae);
        }
        else{
            switchToBudget(ae);
        }


    }
    public void crea(){

        this.mostraResoconto=new BeanMostraResoconto();

        riferimentoCapplicativo.getMostraResoconto(mostraResoconto);
        stampa(mostraResoconto);

    }

    public void stampa(BeanMostraResoconto mR) {


        String budget= mR.returnStampaB();
        String utilizzo= mR.returnStampaU();

        budgetTextField.setText(budget);
        utilizzoTextField.setText(utilizzo);


    }
}
