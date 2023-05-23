package com.example.unified;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbTools{

    final String url = "jdbc:mysql://localhost:3306/glucmodel";
    final String user = "root";
    final String pass = "mf9#4tTTl5*a";

    Connection conn;
    public void dbConnection(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
        }
        catch (Exception e){
            System.out.println("no se ha podido conectar a la base de datos");
            System.out.println(e);
            conn = null;
        }
    }
    public void dbClose(){
        try {
            conn.close();
            conn = null;
        }
        catch(Exception e){
            System.out.println("no hay una bd abierta");
            System.out.println(e);
        }

    }
    public boolean checkUser(String user){

        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT username FROM auth_user");
            while (rs.next()) {
                if(rs.getString(1).equals(user))
                {
                    return true;
                }
            }
        }
        catch(Exception e){
            System.out.println("se ha producido un error de conexion");
            System.out.println(e);
        }
        return false;
    }
    public boolean checkPass(String user,String pass){
        CryptoPass cp = new CryptoPass();
        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT password FROM auth_user WHERE username="+user);

            rs.next();
            if(cp.checkpass(rs.getString(1),pass)){
                return true;
            }
        }
        catch(Exception e){
            System.out.println("se ha producido un error de conexion");
            System.out.println(e);
        }
        return false;
    }
}
