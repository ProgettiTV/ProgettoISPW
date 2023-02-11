package com.example.pcb.design_pattern_decorator;

import com.example.pcb.bean_class.BeanStampaCompatibilita;
import com.example.pcb.bean_class.BeanStampaConfigurazione;
import com.example.pcb.CalcoloCompatibilita;
import com.example.pcb.CreaParametriDiRicerca;
import com.example.pcb.gui_class.ComponentiGUI;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RicercaComponenti {
    protected Integer[] listaBudget;
    protected List<String> configurazioneFinale;
    protected List<String> caratteristica;

    private final ComponentiGUI inviaComponentiGUI;
    Component component;
    public RicercaComponenti( ComponentiGUI usaComponentiGUI) {

        this.inviaComponentiGUI=usaComponentiGUI;
    }
    public void creaParametriDiricerca(int budget,String[] listaCaratteristicheUtilizzo) throws IOException, SQLException {
        CreaParametriDiRicerca creazioneParametriDiRicerca=new CreaParametriDiRicerca();
        this.listaBudget= creazioneParametriDiRicerca.produciParametri(budget,listaCaratteristicheUtilizzo);
        this.component = new PcVuotoConcreteComponent();
        this.configurazioneFinale = new ArrayList<>();
        this.caratteristica = new ArrayList<>();
        creaConfigurazione();
    }
    public void creaConfigurazione() throws IOException, SQLException {

        double randomCpu =  Math.random()*10;

        if(randomCpu%2==0){
            CpuIntel cpuIntel =new CpuIntel( this.component, listaBudget[0]);
            this.component = cpuIntel;
            cpuIntel.cercaCpuIntel();
        }else{
            CpuAmd cpuAmd =new CpuAmd( this.component, listaBudget[0]);
            this.component = cpuAmd;
            cpuAmd.cercaCpuAmd();
        }

        Ram ram=new Ram( this.component, listaBudget[1]);
        this.component = ram;
        ram.cercaRam();
        try {
            ram.cercaRam();
        } catch (SQLException e) {
            throw new SQLException(e);
        }

        SchedaVideo schedaVideo=new SchedaVideo( this.component, listaBudget[2]);
        this.component = schedaVideo;
        try {
            schedaVideo.cercaSchedaVideo();
        } catch (SQLException e) {
            throw new SQLException(e);
        }

        Ssd ssd=new Ssd( this.component, listaBudget[3]);
        this.component =  ssd;
        try {
            ssd.cercaSsd();
        } catch (IOException e) {
            throw new IOException(e);
        }

        Monitor monitor=new Monitor( this.component, listaBudget[4]);
        this.component =  monitor;

        try {
            monitor.cercaMonitor();
        } catch (SQLException e) {
            throw new SQLException(e);
        }


        double randomRaffredamento = (Math.random() *20) ;

        if(randomRaffredamento%2==0){
            RaffredamentoAcqua raffredamentoAcqua=new RaffredamentoAcqua( this.component, listaBudget[5]);
            this.component =  raffredamentoAcqua;
            try {
                raffredamentoAcqua.cercaRaffredamentoAcqua();
            } catch (SQLException e) {
                throw new SQLException(e);
            }
        }else{
            RaffredamentoAria raffredamentoAria=new RaffredamentoAria( this.component, listaBudget[5]);
            this.component =  raffredamentoAria;
            try {
                raffredamentoAria.cercaRaffredamentoAria();
            } catch (SQLException e) {
                throw new SQLException(e);
            }

        }


        Case caseComponent=new Case( this.component, listaBudget[6]);
        this.component =  caseComponent;
        try {
            caseComponent.cercaCase();
        } catch (SQLException e) {
            throw new SQLException(e);
        }

        SchedaMadre schedaMadre=new SchedaMadre( this.component, listaBudget[7]);
        this.component = schedaMadre;
        try {
            schedaMadre.cercaSchedaMadre();
        } catch (SQLException e) {
            throw new SQLException(e);
        }


        Alimentatore alimentatore=new Alimentatore( this.component, listaBudget[8]);
        this.component =  alimentatore;
        try {
            alimentatore.cercaAlimentatore();
        } catch (SQLException e) {
            throw new SQLException(e);
        }


        this.component.getConfigurazione(configurazioneFinale);


        this.component.getCaratteristica(caratteristica);

        calcoloCompatibilita();

    }
    public void calcoloCompatibilita(){

        CalcoloCompatibilita calcoloCompatibilita=new CalcoloCompatibilita(caratteristica);
        int percentualeCompatibilita=calcoloCompatibilita.calcoloPercentualeCompatibilita();

        BeanStampaConfigurazione beanStampaConfigurazione=new BeanStampaConfigurazione(configurazioneFinale);
        BeanStampaCompatibilita beanStampaCompatibilita= new BeanStampaCompatibilita(percentualeCompatibilita);
        inviaComponentiGUI.prendiBeanStampaConfigurazione(beanStampaConfigurazione);
        inviaComponentiGUI.prendiBeanStampaCompatibilita(beanStampaCompatibilita);
    }
}
