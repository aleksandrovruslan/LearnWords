package com.aleksandrov.Base;

public class BaseCreator {
    private static Base base;

    public static Base getBase(String type){
        if(type.equals("BaseFile")){
            base = new BaseFile();
        } else if(type.equals("BasePostgre")){
            base = new BasePostgre();
        }
        return base;
    }
}
