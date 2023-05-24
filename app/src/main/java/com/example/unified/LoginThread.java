package com.example.unified;


import java.sql.SQLException;

public class LoginThread implements Runnable{

    Thread t;
    public String user;
    public String pass;
    public boolean userchecked = false;
    public boolean passchecked = false;




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



        dbt.dbClose();
    }



}
