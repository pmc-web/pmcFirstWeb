package com.bootproj.pmcweb.Common.Utils;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

@Log4j2
public class JsonUtils {

    public JsonUtils() {
    }
    // json 형식으로 유입된 HttpServletRequest를 string 형태로 return
    public  JSONObject readJSONStringFromRequestBody(HttpServletRequest request){
        StringBuffer json = new StringBuffer();
        String line = null;

        try {
            BufferedReader reader = request.getReader();
            while((line = reader.readLine()) != null) {
                json.append(line);
            }

        }catch(Exception e) {
            log.info("Error reading JSON string: " + e.toString());
        }

        JSONObject jObj = new JSONObject(json.toString());
        return jObj;
    }
}