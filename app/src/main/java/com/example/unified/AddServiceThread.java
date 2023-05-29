package com.example.unified;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddServiceThread implements Runnable{

    Thread t;

    public String userMail;
    public boolean userchecked = false;
    public boolean passchecked = false;
    public boolean uploadchecked = false;

    public List<String> servData= new ArrayList<String>();










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

            uploadchecked=dbt.addService(userMail,servData);

        dbt.dbClose();
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }


}
