package com.example.RedditClone.helpers;

import java.util.HashMap;
import java.util.Map;

public class ParameterMapping
{
    public static HashMap<String, String> trimParamMap(Map<String, String[]> params)
    {
        HashMap<String, String> paramsNew = new HashMap<>();
        for (String key : params.keySet()) {
            paramsNew.put(key, params.get(key)[0]);
        }
        return paramsNew;
    }
}
