package com.example.pcb.design_pattern_decorator;

import com.example.pcb.design_pattern_decorator.dao_class_concrete_decorator.DaoRicercaSchedaMadre;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SchedaMadre extends Decorator{
    private final int budgetPerSchedaMadre;
    private String  risultatoRicerca;
    private String  risultatoCaratteristica;
    public SchedaMadre(Component component, int budget) {
        this.budgetPerSchedaMadre= budget;
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

    public void cercaSchedaMadre () throws IOException, SQLException {
        DaoRicercaSchedaMadre daoRicercaSchedaMadre= new DaoRicercaSchedaMadre(this.budgetPerSchedaMadre);


        try {
            daoRicercaSchedaMadre.cercaValore();
        } catch (SQLException e) {
            throw new SQLException(e);
        } catch (IOException e) {
            throw new IOException(e);
        }
        this.risultatoRicerca= daoRicercaSchedaMadre.returnComponenteSchedaMadre();
        this.risultatoCaratteristica=daoRicercaSchedaMadre.returnCaratteristicaSchedaMadre();


    }
}
