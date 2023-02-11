package com.example.pcb;

import com.example.pcb.bean_class.BeanBudget;
import com.example.pcb.bean_class.BeanConferma;
import com.example.pcb.bean_class.BeanMostraResoconto;
import com.example.pcb.bean_class.BeanUtilizzo;
import com.example.pcb.dao_class.DaoBudget;
import com.example.pcb.dao_class.DaoUtilizzi;
import com.example.pcb.design_pattern_decorator.RicercaComponenti;
import com.example.pcb.exception.QueryException;
import com.example.pcb.gui_class.ComponentiGUI;
import java.io.IOException;
import java.sql.SQLException;



public  class DomandeUtente {
    private int budget;
    private String[] listaCaratteristicheUtilizzo= new String[9];
    private BeanMostraResoconto mostraResoconto;
    private int budgetProvissorio;
    private String utilizzoProvissorio;



    public DomandeUtente(){

        //costruttore
    }

    public void prendiBudget(BeanBudget beanBudget){

        this.budgetProvissorio = beanBudget.returnID();

    }
    public void prendiUtilizzo(BeanUtilizzo beanUtilizzo){

        this.utilizzoProvissorio =beanUtilizzo.returnStr();

    }

    public void prendiMostraResoconto(BeanMostraResoconto mostraResoconto) {
        this.mostraResoconto= mostraResoconto;
        invioResoconto();
    }

    public void prendiConferma(BeanConferma beanConferma) throws QueryException, SQLException{
         Boolean confermatoRisposte= beanConferma.returnBool();

        if(Boolean.TRUE.equals(confermatoRisposte)){


            DaoBudget daoBudget=DaoBudget.getInstance();
            DaoBudget.getDaoBudgetConnection();
            daoBudget.setRicercavalore(budgetProvissorio);


            try {

                daoBudget.cercaValore();
            } catch (SQLException e){
                throw new QueryException("Errore SQL");
            }


            DaoUtilizzi daoUtilizzi=new DaoUtilizzi();
            daoUtilizzi.setRicercaUtilizzo(utilizzoProvissorio);
            DaoUtilizzi.getDaoUtilizziConnection();



            try {
                daoUtilizzi.cercaCaratteristiche();
            } catch (SQLException e) {
                throw new SQLException(e);
            }



            this.budget=daoBudget.returnValoreBudget();
            this.listaCaratteristicheUtilizzo=daoUtilizzi.returnListaUtilizzo();
        }



    }



    public void invioResoconto(){

        mostraResoconto.getmostraBudget(budgetProvissorio);
        mostraResoconto.getmostraUtilizzo(utilizzoProvissorio);

    }


    public void prendiCGComponenti(ComponentiGUI componentiGUI) throws IOException, SQLException {

        RicercaComponenti ricercaComponenti=new RicercaComponenti(componentiGUI);
        ricercaComponenti.creaParametriDiricerca(budget,listaCaratteristicheUtilizzo);
    }
}
