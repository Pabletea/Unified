package com.example.unified;


import java.sql.SQLException;

public class RegisterThread implements Runnable{

    Thread t;
    public boolean userchecked = false;
    public boolean passchecked = false;
    public int insertado=0;

    public String nomUser="";
    public String emailuser="";
    public String passuser="";




    public boolean tryLogThread() throws InterruptedException {
        t = new Thread(this,"Register Thread");
        t.start();
        while(t.getState()!=Thread.State.TERMINATED){

        }
        return userchecked && passchecked;
    }
    @Override
    public void run() {

        DbTools dbt = new DbTools();


        userchecked = false;
        passchecked = false;


        dbt.dbConnection();

        insertado=dbt.addUser(nomUser,emailuser,passuser);

        dbt.dbClose();
    }




}
