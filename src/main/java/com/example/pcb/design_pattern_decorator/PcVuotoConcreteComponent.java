package com.example.pcb.design_pattern_decorator;

import java.util.List;

public class PcVuotoConcreteComponent extends Component{

    @Override
    void getConfigurazione(List<String> list){
        String risultatoRicerca="confiugrazione strandard Vuota";
        list.add(risultatoRicerca);
    }
    @Override
    void getCaratteristica(List<String> caratteristica){
        String risultatoCaratteristica="Vuota";
        caratteristica.add(risultatoCaratteristica);
    }
}
