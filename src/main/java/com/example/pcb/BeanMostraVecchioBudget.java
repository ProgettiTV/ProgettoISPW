package com.example.pcb;

public class BeanMostraVecchioBudget {
    private final String vecchioB;
    private final int idVecchioB;

    public BeanMostraVecchioBudget(int budget, int id){
        this.vecchioB= Integer.toString(budget);
        this.idVecchioB = id;
        System.out.println("RICEVO VECCHIO BEAN PER STAMPA"+vecchioB);
    }
    public String returnVecchioB(){
        return vecchioB;
    }

    public int returnIdVecchioB() {
        return idVecchioB;
    }
}
