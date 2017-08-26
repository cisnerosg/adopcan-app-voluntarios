package com.adopcan.adopcan_voluntarios.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by german on 23/8/2017.
 */

public class JsonUtils {

    public static final <T> List<T> getList(String json) throws Exception {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        Type typeOfList = new TypeToken<List<T>>(){}.getType();
        return gson.fromJson(json, typeOfList);
    }
}
