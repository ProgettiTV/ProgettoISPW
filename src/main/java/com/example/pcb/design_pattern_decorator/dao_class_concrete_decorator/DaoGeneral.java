package com.example.pcb.design_pattern_decorator.dao_class_concrete_decorator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoGeneral {

    private String risultatoRicerca;
    private String risultatoCaratteristica;
    private String nomeComponente;
    private final int ricercavalore;

    public DaoGeneral(int budgetPreso,String componente){
        this.ricercavalore=budgetPreso;
        this.nomeComponente=componente;
    }



    public void cercaValore() throws SQLException, IOException {
        Connection connection= DBConnectionAmazon.getDBConnectionAmazon();
        Statement statement;

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new SQLException(e);
        }

        String ricercaCase="SELECT link,caratteristica FROM amazon_componenti WHERE valore=" + ricercavalore + " AND tipo_c='"+nomeComponente+"'";
        try {

            ResultSet queryCaseResult=statement.executeQuery(ricercaCase);

            while(queryCaseResult.next()){
                this.risultatoRicerca = queryCaseResult.getString(1);
                this.risultatoRicerca = queryCaseResult.getString(1);
                this.risultatoCaratteristica = queryCaseResult.getString(2);
            }



        } catch (SQLException e) {
            throw new SQLException(e);
        }

    }


    public String returnComponente() {
        return risultatoRicerca;
    }

    public String returnCaratteristica() {
        return risultatoCaratteristica;
    }

}
