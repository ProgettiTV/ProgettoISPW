package com.example.pcb;

import com.example.pcb.bean_class.BeanBudget;
import com.example.pcb.bean_class.BeanConferma;
import com.example.pcb.bean_class.BeanMostraResoconto;
import com.example.pcb.bean_class.BeanUtilizzo;
import com.example.pcb.dao_class.DaoBudget;
import com.example.pcb.dao_class.DaoUtilizzi;
import com.example.pcb.design_pattern_decorator.RicercaComponenti;
import com.example.pcb.exception.DaoException;
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

    }

    public void prendB(BeanBudget b){

        this.budgetProvissorio =b.returnID();

    }
    public void prendU(BeanUtilizzo u){

        this.utilizzoProvissorio =u.returnStr();

    }

    public void getMostraResoconto(BeanMostraResoconto mR) {
        this.mostraResoconto=mR;
        System.out.println("indirizzo mostraResoconto Bean  "+ mostraResoconto);
        invioResoconto();
    }

    public void prendC(BeanConferma c) {
         Boolean confermatoRisposte= c.returnBool();

        if(confermatoRisposte!=false){


            DaoBudget daoBudget=new DaoBudget(budgetProvissorio);


            try {
                daoBudget.cercaValore();
            } catch (DaoException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            DaoUtilizzi daoUtilizzi=new DaoUtilizzi(utilizzoProvissorio);

            try {
                daoUtilizzi.cercaCaratteristiche();
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }



            this.budget=daoBudget.returnValoreBudget();
            this.listaCaratteristicheUtilizzo=daoUtilizzi.returnListaUtilizzo();
        }



    }



    public void invioResoconto(){

        mostraResoconto.getmostraB(budgetProvissorio);
        mostraResoconto.getmostraU(utilizzoProvissorio);

    }


    public void prendiCGComponenti(ComponentiGUI componentiGUI) {

        RicercaComponenti ricercaComponenti=new RicercaComponenti(componentiGUI);
        ricercaComponenti.creaParametriDiricerca(budget,listaCaratteristicheUtilizzo);
    }
}
