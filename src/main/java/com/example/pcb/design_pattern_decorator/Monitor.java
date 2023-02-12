package com.example.pcb.design_pattern_decorator;

import com.example.pcb.design_pattern_decorator.dao_class_concrete_decorator.DaoGeneral;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Monitor extends Decorator{
    private final int budgetPerMonitor;

    private String risultatoRicerca;
    private String risultatoCaratteristica;
    public Monitor(Component component, int budget) {
        this.budgetPerMonitor = budget;
        this.component=component;

    }

    @Override
    void getConfigurazione(List<String> list){
        super.getConfigurazione(list);
        list.add(risultatoRicerca);
    }
    @Override
    void getCaratteristica(List<String> caratteristica){
        super.getCaratteristica(caratteristica);
        caratteristica.add(risultatoCaratteristica);
    }

    public void cercaMonitor() throws SQLException, IOException {
        DaoGeneral daoGeneral= new DaoGeneral(this.budgetPerMonitor,"monitor");
        try {
            daoGeneral.cercaValore();
        } catch (SQLException e) {
            throw new SQLException(e);
        } catch (IOException e) {
            throw new IOException(e);
        }
        this.risultatoRicerca= daoGeneral.returnComponente();
        this.risultatoCaratteristica=daoGeneral.returnCaratteristica();

    }
}
