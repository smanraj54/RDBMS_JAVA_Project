package com.dal.database.Login;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AllUsers implements Serializable {

    private Map<String, UserDetails> usersList = null;
    private static AllUsers instance= null;
    public static String space = "\t";

    private AllUsers(){
        if(usersList==null){
            usersList = new HashMap<>();
        }
    }

    public static AllUsers getInstance(){
        if(instance == null){
            instance = new AllUsers();
        }
        return instance;
    }

    public static void setAllUsers(AllUsers allUsers){
        instance = allUsers;
    }

    public Map<String, UserDetails> getUsersList() {
        return usersList;
    }

    public UserDetails addUser(UserDetails userDetails){
        return usersList.put(userDetails.getUserName(), userDetails);
    }

    public String getMyAllUsers(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");
        stringBuilder.append(space+"[\n");
        boolean first = true;

        for(Map.Entry<String, UserDetails> entry : usersList.entrySet()){
            if(!first){
                stringBuilder.append(" , \n");
            }
            else{
                first = false;
            }
            stringBuilder.append(space + "\t"+ entry.getKey()+" : "+entry.getValue().getMyUserDetails());

        }
        stringBuilder.append("\n");

        stringBuilder.append(space+"]\n");

        stringBuilder.append("}\n");

        return stringBuilder.toString();
    }
}
