package com.softianstech.jopportal;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Lenovo on 2/28/2017.
 */

public class ParseJSON {

    public static String su;

    Context c;
    public static String[] ids;
    public static String[] locations;
    public static String[] qualifications;
    public static String[] experiences;
    public static String[] designations;
    public static String[] subjects;
    public static String[] useremail;

    public static final String JSON_ARRAY = "result";
    public static final String KEY_ID = "job_id";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_QUALIFICATION = "qualification";
    public static final String KEY_EXPERIENCE = "experience";
    public static final String KEY_DESIGNATION = "designation";
    public static final String KEY_SUBJECTS = "subjects";
    public static final String KEY_EMAIL = "user_email";

    private JSONArray users = null;

    private String json;

    public ParseJSON(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            ids = new String[users.length()];
            locations = new String[users.length()];//  THEY ARE GETTING MEMEORY HERE
            qualifications = new String[users.length()];
            experiences = new String[users.length()];
           // designations = new String[users.length()];
            subjects = new String[users.length()];
            useremail = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                ids[i] = jo.getString(KEY_ID);
                locations[i] = jo.getString(KEY_LOCATION);
                qualifications[i] = jo.getString(KEY_QUALIFICATION);
                experiences[i] = jo.getString(KEY_EXPERIENCE);
               // designations[i] = jo.getString(KEY_DESIGNATION);
                subjects[i] = jo.getString(KEY_SUBJECTS);
               useremail[i] = jo.getString(KEY_EMAIL);

                su=locations[i];



            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
