package com.example.unified;

import android.widget.Toast;

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
        String passEncrypted="";
        String passUnecnrpted="";



        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultado = statement.executeQuery(String.format("SELECT userMasterPassword from user where user.userMail='%s'",user));




        boolean primerElemento = true;
        String primerNombreTabla = null;

        while (resultado.next()) {
            String nombreTabla = resultado.getString(1);
            try {
                String key = "clave-secreta123"; // La clave debe tener 16, 24 o 32 caracteres para AES-128, AES-192 o AES-256 respectivament
                nombreTabla = AESEncryptionUtil.decrypt(key, nombreTabla);
                System.out.println("Texto descifrado: " + nombreTabla);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error al descifrar: " + e.getMessage());
            }


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

    public boolean updateData(String user,List<String> newData) throws SQLException {
        String newNombre="";
        String newApe="";
        String newTlf="";
        newNombre=newData.get(0);
        newApe=newData.get(1);
        newTlf=newData.get(2);
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        int resultado = statement.executeUpdate("UPDATE user SET userName='"+newNombre+"', userSurname='"+newApe+"',userTlf='"+newTlf+"' WHERE userMail='"+user+"'-");
        if (resultado==1){
            return true;
        }
        else{
            return false;
        }

    }

    public boolean deleteUser(String user) throws SQLException {
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        int resultado = statement.executeUpdate("DELETE FROM user WHERE userMail='"+user+"'");
        statement.close();
        if (resultado==1){
            return true;
        }
        else{
            return false;
        }

    }


    public boolean addService(String user, List <String> serviceData){
        int insertado=0;
        int insertado2=0;
        int maseterinsertado=0;
        if(serviceData.isEmpty()){
            return false;
        }else {
            try {
                String consulta = "INSERT INTO service (servName,servType,servMainUrl) values ('" + serviceData.get(0) + "','" + serviceData.get(4) + "','" + serviceData.get(3) + "')";
                String consulta2 = "INSERT INTO account (accountNickName, `accountPassword`, `Service_idService`, `user_idUser`) VALUES ('" + serviceData.get(1) + "', '" + serviceData.get(2) + "', (SELECT idService FROM service WHERE servName = '" + serviceData.get(0) + "'), (SELECT idUser FROM user WHERE userMail = '" + user + "'))";
                PreparedStatement statement = conn.prepareStatement(consulta);
                PreparedStatement statement2 = conn.prepareStatement(consulta2);
                insertado = statement.executeUpdate(consulta);
                insertado2 = statement2.executeUpdate(consulta2);
                if(insertado>0 && insertado2>0){
                    maseterinsertado=1;
                    System.out.println("se ha insertado correctamente");
                }
                else{
                    System.out.println("no se ha insertado correctamente");
                }

                statement.close();
                statement2.close();
            } catch (Exception e) {
                System.out.println("Error al insertar el servicio");
                System.out.println(e);
            }
        }


        if(maseterinsertado==1){
            return true;
        }
        else{
            return false;
        }

    }


}