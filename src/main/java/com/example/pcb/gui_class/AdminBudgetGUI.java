package com.example.pcb.gui_class;

import com.example.pcb.bean_class.BeanSelezionaBudget;
import com.example.pcb.ModificaParametri;
import com.example.pcb.exception.DaoException;
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

public class AdminBudgetGUI extends SwitchClassGUI{

    private Stage stage;
    private Scene scene;

    private final InserisciBudgetGUI inserisciBudgetGUI;



    public AdminBudgetGUI(){
        super();
        this.inserisciBudgetGUI =new InserisciBudgetGUI();
    }



    public void switchToInserisciBudget(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("InserisciBudget.fxml"));

        loader.setControllerFactory(f -> inserisciBudgetGUI);

        scene = new Scene(loader.load());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        inserisciBudgetGUI.prova();

    }


    public void selezionaValoreBudget(ActionEvent event) throws IOException, SQLException, DaoException {

        String selezioneB = ((Button)event.getSource()).getText();

        BeanSelezionaBudget beanSelezionaBudget =new BeanSelezionaBudget();


        beanSelezionaBudget.traduciB(selezioneB);

        ModificaParametri modificaParametri= new ModificaParametri();
        modificaParametri.prendiRiferimentoBeanBudget(beanSelezionaBudget);
        modificaParametri.prendiBeanRiferimentoInviaVecchioBudget(inserisciBudgetGUI);

        inserisciBudgetGUI.prendiRiferimentoCAB(modificaParametri);

        switchToInserisciBudget(event);
    }

}
