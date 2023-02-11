package com.example.pcb.dao_class;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DaoBudgetTest {

    @Test
    void cercaBudget() throws SQLException {
        DaoBudget TestCercaValore = new DaoBudget();
        DaoBudget.getDaoBudgetConnection();
        TestCercaValore.setValoreRicercaBudget(3);
        TestCercaValore.cercaBudget();
        int risultato = TestCercaValore.returnValoreBudgetDB();
        assertEquals(1400, risultato, 0);
    }
}