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

    public void prendB(BeanBudget b){

        this.budgetProvissorio =b.returnID();

    }
    public void prendU(BeanUtilizzo u){

        this.utilizzoProvissorio =u.returnStr();

    }

    public void getMostraResoconto(BeanMostraResoconto mR) {
        this.mostraResoconto=mR;
        invioResoconto();
    }

    public void prendC(BeanConferma c) throws QueryException, SQLException{
         Boolean confermatoRisposte= c.returnBool();

        if(Boolean.TRUE.equals(confermatoRisposte)){


            DaoBudget daoBudget=DaoBudget.getInstance();
            DaoBudget.getDaoBudgetConnection();
            daoBudget.setRicercavalore(budgetProvissorio);


            try {

                daoBudget.cercaValore();
            } catch (QueryException e){
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

        mostraResoconto.getmostraB(budgetProvissorio);
        mostraResoconto.getmostraU(utilizzoProvissorio);

    }


    public void prendiCGComponenti(ComponentiGUI componentiGUI) throws IOException, SQLException {

        RicercaComponenti ricercaComponenti=new RicercaComponenti(componentiGUI);
        ricercaComponenti.creaParametriDiricerca(budget,listaCaratteristicheUtilizzo);
    }
}
