package com.example.unified;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

}