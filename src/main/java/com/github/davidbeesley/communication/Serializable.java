package com.github.davidbeesley.communication;

import com.github.davidbeesley.logger.Logger;
import com.google.gson.Gson;

public class Serializable {

    public String encode(){
        Gson gson = new Gson();
        String s = gson.toJson(this);
        Logger.getInstance().debug("Encoding: " + s);
        return s;
    }

    public static <T> T decode(String json, Class<T> type ){
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

}
