package com.dal.database.Login;

import com.dal.database.fetchdatabase.GetUsersData;
import com.dal.database.utils.Application;

public class FetchAllUsers {

    private AllUsers allUsers = null;

    private static FetchAllUsers fetchAllUsers = null;

    public static FetchAllUsers getInstance() {
        if(fetchAllUsers == null){
            fetchAllUsers = new FetchAllUsers();
        }

        return fetchAllUsers;
    }

    private FetchAllUsers() {
        GetUsersData getUsersData = new GetUsersData();
        Object object = getUsersData.readObject(Application.pathOfUsers);
        this.allUsers = (AllUsers)object;
    }

    public AllUsers getAllUsers(){
        return this.allUsers;
    }

}
