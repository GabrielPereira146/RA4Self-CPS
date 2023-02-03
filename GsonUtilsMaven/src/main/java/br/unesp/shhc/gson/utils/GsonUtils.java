package br.unesp.shhc.gson.utils;

import com.google.gson.Gson;

public class GsonUtils {
    
    private GsonUtils(){
        
    }
    
    public static String objetoToJson(Object o){
        Gson gson = new Gson();
        
        return gson.toJson(o);
    }
}
