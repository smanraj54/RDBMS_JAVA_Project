package com.dal.database.Login;

import java.util.Map;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class AttemptLogin {

    AllUsers allUsers = null;
    public AttemptLogin(){
        allUsers = FetchAllUsers.getInstance().getAllUsers();
    }

    public boolean loginUser(Scanner sc){

        boolean first = true;
        for(int t=0; t<3; t++) {

            if(!first) {
                System.err.println("\n\tEnter Correct Credentials!!!");
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            first = false;
            System.out.print("\n\tEnter UserName : ");
            String userName = sc.nextLine();
            System.out.print("\n\tEnter Password : ");
            String password = sc.nextLine();

            Map<String, UserDetails> usersMap = allUsers.getUsersList();

            if(usersMap.containsKey(userName) && (usersMap.get(userName)).getPassword().equals(password)){
                System.out.println("\n\t\tlogin Successful!!!");
                return true;
            }
        }

        System.err.println("\n\tFailed to login!!!");
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }


}
