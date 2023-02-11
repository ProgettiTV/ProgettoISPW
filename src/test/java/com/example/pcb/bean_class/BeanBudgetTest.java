package com.example.pcb.bean_class;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BeanBudgetTest {

    @Test
    void returnID() {

        BeanBudget TestBeanBudget = new BeanBudget("€€€");
        int risultato = TestBeanBudget.returnID();
        assertEquals(3, risultato, 0);

    }
}