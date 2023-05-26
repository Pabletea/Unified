package com.example.unified;

import java.sql.SQLException;

public class DeleteUserThread implements Runnable{

    Thread t;
    public String user;
    public boolean userchecked = false;
    public boolean passchecked = false;
    public boolean deleteCheck=false;




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
            deleteCheck=dbt.deleteUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dbt.dbClose();
    }



}
