package com.gmbdesign.cfrm.utils;

import com.google.gson.Gson;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 *
 * @author gustavogamboa
 */
public class DecodeJSON {
    
    public static <T extends Object> T descodificaJSON(String encodedJSON, Class<T> classOfType) throws UnsupportedEncodingException{
        Gson gson = new Gson();
        String json = new String(Base64.getDecoder().decode(encodedJSON.getBytes("utf-8")), "utf-8");
        return gson.fromJson(json, classOfType);
    }
    
}
