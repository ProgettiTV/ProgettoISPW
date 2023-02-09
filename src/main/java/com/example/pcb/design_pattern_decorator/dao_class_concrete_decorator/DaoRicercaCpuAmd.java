package com.example.pcb.design_pattern_decorator.dao_class_concrete_decorator;

import java.io.IOException;
import java.sql.*;

public class DaoRicercaCpuAmd {

    private String risultatoRicerca;
    private String risultatoCaratteristica;
    private final int ricercavalore;

    public DaoRicercaCpuAmd(int budgetPreso){
        this.ricercavalore=budgetPreso;



    }

    public void cercaValore() throws SQLException, IOException {

        Connection connectionCpuAmd= DBConnectionAmazon.getDBConnectionAmazon();
        Statement statement;


        try {
            statement = connectionCpuAmd.createStatement();
        } catch (SQLException e) {
            throw new SQLException(e);
        }

        String ricercaCpuAmd="SELECT link,caratteristica FROM amazon_componenti WHERE valore=" + ricercavalore + " AND tipo_c='" + "cpuAmd'" ;
        try {

            ResultSet queryCpuAmdResult=statement.executeQuery(ricercaCpuAmd);

            while(queryCpuAmdResult.next()){
                this.risultatoCaratteristica = queryCpuAmdResult.getString(2);
                this.risultatoRicerca = queryCpuAmdResult.getString(1);

            }

        } catch (SQLException e) {
            throw new SQLException(e);
        }

    }


    public String returnComponenteCpuAmd() {
        return risultatoRicerca;
    }

    public String returnCaratteristicaCpuAmd() {
        return risultatoCaratteristica;
    }
}
