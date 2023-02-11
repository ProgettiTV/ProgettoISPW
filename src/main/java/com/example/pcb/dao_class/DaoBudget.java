package com.example.pcb.dao_class;

import com.example.pcb.AbstractObserver;
import com.example.pcb.AbstractSubject;
import java.sql.*;
import java.util.ArrayList;

public class DaoBudget extends AbstractSubject {

    private int risultatoBudget;
    private int ricercavalore;
    private static DaoBudget instance;
    private static Connection connection;

    private int valoreRicercaBudget;
    private String rispostaBudgetDB;

    private String valoreNuovoBudget;


    public  DaoBudget(){

        //costruttore
    }

    public static DaoBudget getInstance(){
        if(instance==null){
            instance = new DaoBudget();
        }
        return instance;
    }

    public void setOsservatoreBudget(){
        this.observers = new ArrayList<>();
    }
    public void setValoreRicercaBudget(int valore){
        this.valoreRicercaBudget = valore;

    }

    public void setNuovoValoreBudget(String valore){

        this.valoreNuovoBudget = valore;

    }
    public void setRicercavalore(int budgetPreso){
        this.ricercavalore=budgetPreso;
    }


    public static Connection getDaoBudgetConnection() {

        try{
            if(connection==null){
                connection = DBConnection.getDBConnection();

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    public void cercaValore() throws SQLException {

        String ricercaValoreBudget="SELECT valore FROM parametri_budget WHERE idparametri_budget=" + ricercavalore ;
        Statement statement;
        statement = connection.createStatement();
        String result = "";
        try {

            ResultSet queryBudgetResult=statement.executeQuery(ricercaValoreBudget);

            while(queryBudgetResult.next()){
                result = queryBudgetResult.getString(1);
            }

        } catch (SQLException e) {
            throw new SQLException(e);
        }

        this.risultatoBudget= Integer.parseInt(result);


    }


    public int returnValoreBudget() {
        return risultatoBudget;
    }

    public void cercaBudget() throws SQLException {


        String showBudgetQuery = "SELECT Valore FROM parametri_budget WHERE idparametri_budget = " + valoreRicercaBudget;

        Statement statement = connection.createStatement();
        ResultSet querySearchBudgetResult = statement.executeQuery(showBudgetQuery);

        while (querySearchBudgetResult.next()){
            rispostaBudgetDB = querySearchBudgetResult.getString(1);


        }
    }

    public void aggiornaBudget() throws SQLException {


        String updateBudgetQuery = "UPDATE parametri_budget SET Valore = '" + valoreNuovoBudget + "' WHERE idparametri_budget = " + valoreRicercaBudget;
        Statement statement = connection.createStatement();
        statement.executeUpdate(updateBudgetQuery);
        notifyObservers(Integer.parseInt(valoreNuovoBudget));

    }
    public int returnValoreBudgetDB(){
        return Integer.parseInt(rispostaBudgetDB);
    }

    @Override
    public void addObserver(AbstractObserver observer) {

        observers.add(observer);
    }


    @Override
    public void notifyObservers(int budget) {


        for (AbstractObserver observer : observers) {
            observer.updateBudget(budget);

        }

    }

}
