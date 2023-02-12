package com.example.pcb.gui_class;

import com.example.pcb.bean_class.BeanSelezionaUtilizzo;
import com.example.pcb.ModificaParametri;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class AdminUtilizziGUI extends SwitchClassGUI{

    private final InserisciUtilizzoGUI inserisciUtilizzoGUI;
    private Stage stage;
    private Scene scene;


    public AdminUtilizziGUI(){
        super();
        this.inserisciUtilizzoGUI =new InserisciUtilizzoGUI();
    }


    public void switchToInserisciUtilizzo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("InserisciUtilizzo.fxml"));

        loader.setControllerFactory(f -> inserisciUtilizzoGUI);
        scene = new Scene(loader.load());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        inserisciUtilizzoGUI.prova();

    }

    public void selezionaNomeUtilizzo(ActionEvent actionEvent) throws IOException, SQLException {

        String utilizzo = ((Button)actionEvent.getSource()).getText();

        BeanSelezionaUtilizzo beanSelezionaUtilizzo = new BeanSelezionaUtilizzo();
        beanSelezionaUtilizzo.prendiU(utilizzo);

        ModificaParametri modificaParametri= new ModificaParametri();
        modificaParametri.prendiRiferimentoBeanUtilizzo(beanSelezionaUtilizzo);
        modificaParametri.prendiBeanRiferimentoInviaVecchioUtilizzo(inserisciUtilizzoGUI);

        inserisciUtilizzoGUI.prendiRiferimentoCAU(modificaParametri);
        switchToInserisciUtilizzo(actionEvent);

    }
}
