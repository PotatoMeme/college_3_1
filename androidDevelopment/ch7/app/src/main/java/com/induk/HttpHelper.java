package com.induk;

import android.util.Log;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpHelper {
    public String[] getList() {

        // Declare variable(s).
        String[] arrList = null;
        JSONObject listObject = null;
        String url = "http://115.89.165.130:6776/v1/api/getList";

        try{
            OkHttpClient client = new OkHttpClient(); // 서버 연동용
            client = new OkHttpClient.Builder().retryOnConnectionFailure(false).build();

            // Request
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            Response response = client.newCall(request).execute();
            String strRet = response.body().string();
            JSONParser parser = new JSONParser();
            listObject = (JSONObject)parser.parse(strRet);// Json String ->  Json Object
            // error check.
            if (listObject == null) {
                return null;
            }

            JSONArray jArr = (JSONArray) listObject.get("data");
            arrList = new String[jArr.size()];
            for (int ii=0; ii < jArr.size(); ii++) {
                JSONObject j = (JSONObject) jArr.get(ii);
                Log.d("***",(String) j.get("code"));
                arrList[ii] = (String) j.get("name");
            }

        } catch (Exception ex){
            return null;
        }
        // Return.
        return arrList;

    }
}


