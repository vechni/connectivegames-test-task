package com.semyonov.utils;

public class ClassNameUtils
{
    public static String getCurrentClassName(){
        try{
            throw new RuntimeException();
        }catch( RuntimeException e ){
            return e.getStackTrace()[1].getClassName();
        }
    }
}
