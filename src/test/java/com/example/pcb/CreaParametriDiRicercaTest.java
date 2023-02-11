package com.example.pcb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreaParametriDiRicercaTest {

    @Test
    void produciParametri() {

        CreaParametriDiRicerca TestParametriDiRicerca = new CreaParametriDiRicerca();
        int budget = 2200;
        String[] listaComponenti = {"22","11","11","12","12","5","3","19","5"};

        Integer[] risultatoAspettato = {484,242,242,264,264,110,66,418,110};


        Integer[] risultato = TestParametriDiRicerca.produciParametri(budget, listaComponenti);

        assertArrayEquals(risultatoAspettato, risultato);

    }
}