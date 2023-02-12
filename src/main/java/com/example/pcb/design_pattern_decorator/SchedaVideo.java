package com.example.pcb.design_pattern_decorator;

import com.example.pcb.design_pattern_decorator.dao_class_concrete_decorator.DaoGeneral;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SchedaVideo extends Decorator{
    private final int budgetPerSchedaVideo;
    private String risultatoRicerca;
    private String risultatoCaratteristica;
    public SchedaVideo(Component component, int budget) {
        this.budgetPerSchedaVideo= budget;
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

    public void cercaSchedaVideo() throws IOException, SQLException {
        DaoGeneral daoGeneral= new DaoGeneral(this.budgetPerSchedaVideo,"SchedaVideo");
        try{
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
