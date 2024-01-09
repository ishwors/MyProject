
package com.apex.ishwor.database;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConnectionFactory {
    Connection con;
    Statement stmt;
    ResultSet rs;
    
    
    /**
     * 
     */
    public ConnectionFactory(){
    try{
        Class.forName("com.mysql.jdbc.Driver");
        con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "");
        stmt = con.createStatement();

    }catch(Exception e){
        e.printStackTrace();
        }
    }
    
    /**
     * 
     * @param username
     * @param password
     * @return 
     */
    public boolean checkLogin(String username, String password){

        try{
            String sql = "select * from user where username ='"+username+"'and password ='"+password+"'";
            rs =  stmt.executeQuery(sql);

            while(rs.next()){
                return true;
            }
        }catch (Exception e){
         e.printStackTrace();
        }
        return false;
    }
    
    /**
     * 
     * @return 
     */
    public Connection getConnection(){  //connection 2steps among 7steps in JDBC
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "");

        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        
        return con;
    }

}