package com.dal.database.Login;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AllUsers implements Serializable {

    private Map<String, UserDetails> usersList = null;

    public AllUsers(){
        if(usersList==null){
            usersList = new HashMap<>();
        }
    }

    public Map<String, UserDetails> getUsersList() {
        return usersList;
    }

    public UserDetails addUser(UserDetails userDetails){
        return usersList.put(userDetails.getUserName(), userDetails);
    }
}
