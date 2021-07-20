package com.dal.database.fetchdatabase;

import com.dal.database.Login.AllUsers;
import com.dal.database.utils.Application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class GetUsersData {


    public GetUsersData(){

    }

    public Object readObject(String path){
        FileInputStream fileInputStream = null;
        Object readObject = null;
        try {
            fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            readObject = objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            System.out.println("Reading object was successful");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return readObject;
    }



}
