package com.example.pcb;



import java.util.List;

public class CalcoloCompatibilita {
    private final List<String> listaCompatibilita;
    private int percentualeCompatibilita;
    public CalcoloCompatibilita(List<String> caratteristica) {
        this.listaCompatibilita=caratteristica;
    }
    public int calcoloPercentualeCompatibilita(){
        String cpu=listaCompatibilita.get(1) ;
        String schedaMadre=listaCompatibilita.get(8);
        String raffredamento=listaCompatibilita.get(6);
        String Ssd=listaCompatibilita.get(4);
        String Ram=listaCompatibilita.get(2);
        String Case=listaCompatibilita.get(7);
        String Alimentatore=listaCompatibilita.get(9);
        String SchedaVideo=listaCompatibilita.get(3);
        String Monitor=listaCompatibilita.get(5);



        if( cpu!=null  && schedaMadre!=null ){
            if(((Integer.parseInt(schedaMadre)-2 )<= (Integer.parseInt(cpu) ) ) && ( (Integer.parseInt(cpu) )<= (Integer.parseInt(schedaMadre)+2)) ) {
                percentualeCompatibilita += 20;
            }

        }
        if( cpu!=null && raffredamento!=null ){


             if ( (Integer.parseInt(raffredamento)-2 )<= (Integer.parseInt(cpu) ) && (Integer.parseInt(cpu) )<= (Integer.parseInt(raffredamento)+2) &&  !cpu.equals("null")  && raffredamento.equals("null") ) {
                 percentualeCompatibilita += 20;
             }

        }
        if (  Ssd!=null && Ram!=null){
                if( Integer.parseInt(Ssd)  > 10 * ( Integer.parseInt(Ram) ) ) {
                    percentualeCompatibilita += 20;
                }

        }
        if (  Case !=null  && Alimentatore !=null ){
                if(Case.equals(Alimentatore) ){
                    percentualeCompatibilita += 20;
                }

        }
        if ( SchedaVideo!=null &&  Monitor!=null){

                if(SchedaVideo.equals(Monitor)) {
                    percentualeCompatibilita += 20;
                }

        }

        return  percentualeCompatibilita;
    }
}
