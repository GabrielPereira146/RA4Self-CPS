package br.unesp.rc.gson.utils;

import com.google.gson.Gson;

public class GsonUtils {

    private GsonUtils() {

    }

    public static String objetoToJson(Object o) {
        Gson gson = new Gson();

        return gson.toJson(o);
    }

    public static Object jsonToObject(String json, Class cls) {
        Gson gson = new Gson();

        return gson.fromJson(json, cls);
    }
}
