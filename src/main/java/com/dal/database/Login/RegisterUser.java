package com.dal.database.Login;

import com.dal.database.saveData.WriteObjecToFile;
import com.dal.database.utils.Application;

public class RegisterUser {

    AllUsers allUsers = null;
    public RegisterUser() {
        allUsers = new AllUsers();
    }

    public boolean registerNewUser(String userName, String password){
        UserDetails userDetails = new UserDetails(userName, password);
        allUsers.addUser(userDetails);
        return true;
    }

    public boolean writeToObjectFile(){
        WriteObjecToFile writeObjecToFile = new WriteObjecToFile();
        writeObjecToFile.writeObject(allUsers,Application.pathOfUsers );
        return true;
    }
}
