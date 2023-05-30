package com.example.unified;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetAccountThread implements Runnable{

    Thread t;
    public String user;
    public String pass;
    public boolean userchecked = false;
    public boolean passchecked = false;
    public List<String> accounts= new ArrayList<String>();





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
            accounts=dbt.getAccounts(user);

            //imprimirm la lista
            for (int i = 0; i < accounts.size(); i++) {
                System.out.println(accounts.get(i));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dbt.dbClose();
    }



}
