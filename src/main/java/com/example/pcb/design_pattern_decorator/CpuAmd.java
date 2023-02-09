package com.example.pcb.design_pattern_decorator;

import com.example.pcb.design_pattern_decorator.dao_class_concrete_decorator.DaoRicercaCpuAmd;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CpuAmd extends Decorator{
    private final int budgetPerCpu;

    private String risultatoRicerca;
    private String risultatoCaratteristica;
    public CpuAmd(Component component, int budget) {
        this.budgetPerCpu= budget;
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

    public void cercaCpuAmd(){

        DaoRicercaCpuAmd daoRicercaCpu = new DaoRicercaCpuAmd(this.budgetPerCpu);

        try {
            daoRicercaCpu.cercaValore();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException a) {
            throw new RuntimeException(a);
        }
        this.risultatoRicerca= daoRicercaCpu.returnComponenteCpuAmd();
        this.risultatoCaratteristica=daoRicercaCpu.returnCaratteristicaCpuAmd();
    }
}
