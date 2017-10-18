package com.softianstech.jopportal;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Lenovo on 7/25/2017.
 */

public class ParsingApplyedJob {

    public static String su;

    Context c;
    public static String[] jobids;
    public static String stringbuffer2;
    StringBuilder subjectsTable2=new StringBuilder();

    public static final String JSON_ARRAY = "result";
    public static final String KEY_ID = "job_id";


    public static JSONArray users = null;

    private String json;

    public ParsingApplyedJob(String json){
        this.json = json;
    }

    protected void ParsingApplyedJob(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            jobids = new String[users.length()];
            Variables.length=users.length();

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                jobids[i] = jo.getString(KEY_ID);






            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
