package com.example.unified;

import java.sql.SQLException;

public class Threads {
    public class LoginThread implements Runnable{

        Thread t;
        public String user;
        public String pass;
        public boolean userchecked = false;
        public boolean passchecked = false;
        public int insertado=0;

        public String nomUser="";
        public String emailuser="";
        public String passuser="";



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


            userchecked = false;
            passchecked = false;

            dbt.dbConnection();

            try {
                pass=dbt.getPassword("pabloymiguel2002@gmail.com");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            insertado=dbt.addUser(nomUser,emailuser,passuser);

            dbt.dbClose();
        }



    }
}
