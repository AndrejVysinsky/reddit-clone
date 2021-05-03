package com.example.RedditClone.helpers;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GsonMapping {

    public static <T> T MapToObject(Map<String, String[]> params, Class<T> classType)
    {
        HashMap<String, String> paramsNew = new HashMap<>();
        for (String key : params.keySet())
        {
            paramsNew.put(key,params.get(key)[0]);
        }

        Gson gson = new Gson();
        String json = gson.toJson(paramsNew);
        return gson.fromJson(json, classType);
    }

    /*public static <T> ArrayList<T> ResultSetToObject(ResultSet set, Class<T> classType)
    {
        Gson gson = new Gson();
        String json = gson.toJson(set);

        return gson.fromJson(json, (Type) classType);
    }*/
}
