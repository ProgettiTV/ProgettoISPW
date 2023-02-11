package com.example.pcb;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalcoloCompatibilitaTest {

    @Test
    void calcoloCompatibilita() {

        final List<String> listaCompatibilita = new ArrayList<>();

        listaCompatibilita.add("Vuota");
        listaCompatibilita.add("2022");
        listaCompatibilita.add("16");
        listaCompatibilita.add("i7");
        listaCompatibilita.add("1000");
        listaCompatibilita.add("i9");
        listaCompatibilita.add("2005");
        listaCompatibilita.add("tipo 2");
        listaCompatibilita.add("2000");
        listaCompatibilita.add("tipo 2");


        CalcoloCompatibilita TestCompatibilita = new CalcoloCompatibilita(listaCompatibilita);

        int percentuale = TestCompatibilita.calcoloPercentualeCompatibilita();
        assertEquals(20, percentuale, 20);

    }
}