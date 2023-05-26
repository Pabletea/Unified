package com.example.unified;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbTools{

    final String url = "jdbc:mysql://ovh2.trebol-ie.com:3306/unifieddb";
    final String user = "root";
    final String pass = "Afuera-dam2";

    Connection conn;
    public void dbConnection(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
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

    public String getPassword(String user) throws SQLException {
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultado = statement.executeQuery(String.format("SELECT userMasterPassword from user where user.userMail='%s'",user));
        boolean primerElemento = true;
        String primerNombreTabla = null;

        while (resultado.next()) {
            String nombreTabla = resultado.getString(1);
            System.out.println("Nombre de tabla: " + nombreTabla);

            if (primerElemento) {
                primerNombreTabla = nombreTabla;
                primerElemento = false;
            }
        }

        resultado.close(); // Cerrar el conjunto de resultados después de terminar el bucle

        return primerNombreTabla; // Retorna el primer elemento o null si no hay elementos
    }

    public int addUser(String nombre,String email,String password){
        int insertado=0;
        try {
            String consulta = "INSERT INTO user (userName, userMail, userCreatedDate, userMasterPassword) VALUES ('"+nombre+"', '"+email+"', NOW(),'"+password+"')";

            // Crear una sentencia preparada con parámetros
            PreparedStatement statement = conn.prepareStatement(consulta);
            insertado = statement.executeUpdate(consulta);
            if(insertado>0){
                System.out.println("se ha insertado correctamente");
            }
            else{
                System.out.println("no se ha insertado correctamente");
            }
        }
        catch(Exception e){
            System.out.println("se ha producido un error de conexion");
            System.out.println(e);
        }

        return insertado;
    }
    public boolean checkUser(String user) throws SQLException{
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultado = statement.executeQuery("SELECT COUNT(*) AS count FROM user WHERE userMail = '"+user+"'");
        boolean primerElemento = true;
        String primerNombreTabla = null;
        while (resultado.next()) {
            String nombreTabla = resultado.getString(1);
            if (primerElemento) {
                primerNombreTabla = nombreTabla;
                primerElemento = false;
            }
        }
        resultado.close();
        if (primerNombreTabla.equals("1")) {
            return true;
        }
        else{
            return false;
        }

    }
    public boolean checkPass(String user,String pass) throws SQLException {
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultado = statement.executeQuery(String.format("SELECT userMasterPassword from user where user.userMail='%s'",user));
        boolean primerElemento = true;
        String primerNombreTabla = null;

        while (resultado.next()) {
            String nombreTabla = resultado.getString(1);
            if (primerElemento) {
                primerNombreTabla = nombreTabla;
                primerElemento = false;
            }
        }

        resultado.close(); // Cerrar el conjunto de resultados después de terminar el bucle
        if(pass.equals(primerNombreTabla)){
            return true;
        }
        else{
            return false;
        }
    }

    public  List<String> getUserData(String mail) throws SQLException {
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultado = statement.executeQuery("SELECT * from user WHERE userMAil = '"+mail+"'");
        List<String> datosUser= new ArrayList<String>();
        ResultSetMetaData metaData=resultado.getMetaData();
        int columnas=metaData.getColumnCount();
        if(resultado.next()){
            for(int i=1;i<=columnas;i++){
                datosUser.add(resultado.getString(i));
            }
        }
        resultado.close();

        return datosUser;
    }

    public boolean updatePassword(String userMail,String newPass) throws SQLException {
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        int resultado = statement.executeUpdate("UPDATE user SET userMasterPassword = '"+newPass+"' WHERE userMail = '"+userMail+"'");
        statement.close();
        if(resultado==1){
            return true;
        } else{
            return false;
        }


    }


}