package com.example.pcb.bean_class;

public class BeanMostraResoconto {
    private String mostraBudegt;
    private String mostraUtilizzo;
    public BeanMostraResoconto() {
        //costruttore
    }

    public void getmostraBudget(int b1){
        this.mostraBudegt ="";
        for(int i=0;i< b1;i++){
            this.mostraBudegt = mostraBudegt + "€";
        }

    }


    public void getmostraUtilizzo(String u1){
        this.mostraUtilizzo =u1;

    }
    public String returnStampaB(){
        return mostraBudegt;
    }
    public String returnStampaU(){
        return mostraUtilizzo;
    }

}
