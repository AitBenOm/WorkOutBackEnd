package com.AitBenOm.GymMonitor;

import java.sql.Connection;
import java.sql.DriverManager;

public class Test {

    public static void main(String[] args) {


        String jdbcUrl ="jdbc:mariadb://87.88.37.230:3306/GymMonitor";
        String user= "";
        String pwd ="";
        try {
            System.out.println("Connection To  !! "+jdbcUrl);
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conx = DriverManager.getConnection(jdbcUrl,user,pwd);
            System.out.println("Connection successfull !!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

