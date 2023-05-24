package com.example.unified;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetDataThread implements Runnable{

    Thread t;

    public String userMail;
    public boolean userchecked = false;
    public boolean passchecked = false;

    public List<String> datosUser= new ArrayList<String>();







    public boolean tryLogThread() throws InterruptedException {
        t = new Thread(this,"Login Thread");
        t.start();
        while(t.getState()!=Thread.State.TERMINATED){

        }
        return userchecked && passchecked;
    }
    @Override
    public void run() {


        DbTools dbt = new DbTools();

        dbt.dbConnection();

        try {
            datosUser=dbt.getUserData(userMail);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dbt.dbClose();
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public List<String> getDatosUser() {
        return datosUser;
    }

    public void setDatosUser(List<String> datosUser) {
        this.datosUser = datosUser;
    }
}
