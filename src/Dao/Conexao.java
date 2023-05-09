/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    private static Connection con;
    
public static Connection getConnection() throws ClassNotFoundException, SQLException{
     if(con==null){
      Class.forName("org.postgresql.Driver");
               String url="jdbc:postgresql://localhost:5432/banco";
               String user="postgres";
               String pass="1234";
               con= DriverManager.getConnection(url, user, pass);
               return con;
       }else{
            return con;
       }
    }
}
