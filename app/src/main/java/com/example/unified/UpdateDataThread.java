package com.example.unified;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UpdateDataThread implements Runnable{

    Thread t;

    public String userMail;
    public boolean userchecked = false;
    public boolean passchecked = false;
    public boolean updatechecked = false;

    public List<String> newData= new ArrayList<String>();










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
            updatechecked=dbt.updateData(userMail,newData);
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


}
