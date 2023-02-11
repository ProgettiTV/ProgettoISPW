package com.example.pcb.bean_class;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BeanSelezionaBudgetTest {

    @Test
    void returnB() {
        BeanSelezionaBudget TestBeanSelezionaBudget = new BeanSelezionaBudget();
        TestBeanSelezionaBudget.traduciB("€€€€");
        int risultato = TestBeanSelezionaBudget.returnB();
        assertEquals(4, risultato, 0);
    }
}