package com.example.pcb.dao_class;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DaoUtilizziTest {

    @Test
    void cercaPercentualiUtilizzo() throws SQLException {
        DaoUtilizzi TestDaoUtilizzi = new DaoUtilizzi();
        DaoUtilizzi.getDaoUtilizziConnection();
        TestDaoUtilizzi.setNomeRicercaUtilizzo("Gaming");
        TestDaoUtilizzi.cercaPercentualiUtilizzo();
        String[] risultato = TestDaoUtilizzi.returnValoreUtilizzoDB();
        String[] risultato1= {"26","14","8","12","8","5","3","17","5"};
        assertArrayEquals(risultato1, risultato);
    }
}