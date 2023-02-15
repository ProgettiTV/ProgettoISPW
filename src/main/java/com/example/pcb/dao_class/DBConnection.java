package com.example.pcb.dao_class;

import java.io.FileInputStream;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import java.util.Properties;


public class DBConnection {

    private static Connection connection;

    public DBConnection(){
        //costruttore
    }

    public static Connection getDBConnection() throws IOException{
        Properties prop = new Properties();
        prop.load(new FileInputStream("C:\\Users\\justo\\Desktop\\pesame\\Progetto\\src\\main\\java\\com\\example\\pcb\\dao_class\\credenzialiDB"));
        String databaseUser = prop.getProperty("databaseUser");
        String databasePassword = prop.getProperty("databasePassword");
        String url = prop.getProperty("url");
        try{
            if(connection==null){
                connection = DriverManager.getConnection(url, databaseUser, databasePassword);

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    public String getFilePath() throws IOException{

        Properties prop1 = new Properties();
        prop1.load(new FileInputStream("C:\\Users\\justo\\Desktop\\pesame\\Progetto\\src\\main\\java\\com\\example\\pcb\\dao_class\\filePath"));
        return prop1.getProperty("path");

    }



}
