package com.example.pcb.design_pattern_decorator.dao_class_concrete_decorator;

import java.io.FileInputStream;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnectionAmazon {

    private static Connection connection;

    private DBConnectionAmazon(){

    }

    public static Connection getDBConnectionAmazon() throws IOException {

        try{
            Properties prop = new Properties();
            prop.load(new FileInputStream("C:\\Users\\justo\\Desktop\\pesame\\Progetto\\src\\main\\java\\com\\example\\pcb\\design_pattern_decorator\\dao_class_concrete_decorator\\credenzialiAmazonDB"));
            String databaseProprietario = prop.getProperty("databaseUser");
            String databaseAmazonPassword = prop.getProperty("databasePassword");
            String urlDbAmazon = prop.getProperty("url");
            if(connection==null){
                connection = DriverManager.getConnection(urlDbAmazon, databaseProprietario, databaseAmazonPassword);

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

}
