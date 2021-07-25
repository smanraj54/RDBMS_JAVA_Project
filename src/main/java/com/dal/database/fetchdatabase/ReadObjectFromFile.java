package com.dal.database.fetchdatabase;

import java.io.*;

public class ReadObjectFromFile {

    private static ReadObjectFromFile instance = null;
    private ReadObjectFromFile(){}

    public static ReadObjectFromFile getInstance(){
        if(instance == null){
            instance = new ReadObjectFromFile();
        }
        return instance;
    }

    public Object readObject(String path){
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        Object readObject = null;
        try {
            File file = new File(path);
            if(!file.exists()){
                return null;
            }
            fileInputStream = new FileInputStream(path);
            objectInputStream = new ObjectInputStream(fileInputStream);
            readObject = objectInputStream.readObject();
            System.out.println("Reading object was successful");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(objectInputStream != null){
                try {
                    objectInputStream.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

        return readObject;
    }


}
