package com.softianstech.jopportal;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 6/12/2017.
 */

public class Education extends Fragment {

    Spinner qualification, institute, coursetype,year;
    Button Edusubmit;
    EditText instituteName;
    int Qualification2=0,CourseType2=0,Year2=0;
    RequestQueue requestQueue7;//
//    Context context;
//    String insertUrl7 =Links.USER_FORGET;
   private ArrayList<String> Qualification;
    private ArrayList<String> Institute;
    private ArrayList<String> Course;
    private ArrayList<String> Year;

    int valid=0;
    String message;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.education, container, false);
        requestQueue7 = com.android.volley.toolbox.Volley.newRequestQueue(getContext());


        qualification = (Spinner) v.findViewById(R.id.qualification);
//        institute= (Spinner) v.findViewById(R.id.institute);
        instituteName = (EditText) v.findViewById(R.id.institute);

        coursetype = (Spinner) v.findViewById(R.id.coursetype);
        year = (Spinner) v.findViewById(R.id.passyear);
        Edusubmit = (Button) v.findViewById(R.id.edubutton);
        // requestQueue7 = Volley.newRequestQueue(getContext());
        Qualification = new ArrayList<String>();
        Institute = new ArrayList<String>();
        Course = new ArrayList<String>();
        Year = new ArrayList<String>();

        yearpass();
        course();
        qualification();

//        instituteName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(),"Please type your institute name",Toast.LENGTH_LONG).show();
//            }
//        });
        Edusubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Qualification2=0;CourseType2=0;Year2=0;
                valid=0;
                if (instituteName.getText().toString().trim().equals("")) {
                    instituteName.setError("please enter institute");
                    Toast.makeText(getContext(), "please enter institute name", Toast.LENGTH_SHORT).show();

                    valid++;
                }
                if (qualification.getSelectedItem().toString().equals("Qualification")) {
                    //Put an allert dialog
                    qualification.setSelection(Qualification.indexOf("Qualification"));
//                    if(valid>0)
                    // {
                    //qualification();
                    Toast.makeText(getContext(), "please enter qualification", Toast.LENGTH_SHORT).show();

                    // }
                    //else
                    //{
                    //open2(v);


                    valid++;
                    Qualification2++;

                    //}
                }

                //***************************************************
                if (coursetype.getSelectedItem().toString().equals("Course Type")) {
                    //Put an allert dialog

//                    if(valid>0)
                    // {
                  //  course();
                    coursetype.setSelection(Course.indexOf("Course Type"));
                    Toast.makeText(getContext(), "please enter course type", Toast.LENGTH_SHORT).show();

                    // }
                    //else
                    //{
                    // open2(v);

                    valid++;
                    CourseType2++;

                    //}
                }
                //***************************************************************
                if (year.getSelectedItem().toString().equals("Year Of Passout")) {
                    //Put an allert dialog

//                    if(valid>0)
                    // {
                    //yearpass();
                    year.setSelection(Year.indexOf("Year Of Passout"));
                     Toast.makeText(getContext(), "please enter pass year", Toast.LENGTH_SHORT).show();

                    // }
                    //else
                    //{
//                    open2(v);

                    valid++;

                    Year2++;

                    //}
                }

//                if (Qualification2 > 0 && CourseType2 > 0 && Year2 > 0) {
//                    message = "Please choose Qualification,Course,Passout Year";
//                    open2(v);
//                }
//                if (Qualification2 > 0 && CourseType2 > 0 && Year2 < 0) {
//                    message = "Please choose Qualification,Course";
//                    open2(v);
//                }
//                if (Qualification2 > 0 && CourseType2 < 0 && Year2 > 0) {
//                    message = "Please choose Qualification,Passout Year";
//                    open2(v);
//                }
//                    if (Qualification2 < 0 && CourseType2 > 0 && Year2 > 0) {
//                        message = "Please choose Course,Passout Year";
//                        open2(v);
//                    }
//                    if (Qualification2 < 0 && CourseType2 < 0 && Year2 > 0) {
//                        message = "Please choose Passout Year";
//                        open2(v);
//                    }
//                    if (Qualification2 < 0 && CourseType2 > 0 && Year2 < 0) {
//                        message = "Please choose Course";
//                        open2(v);
//                    }
//                    if (Qualification2 > 0 && CourseType2 < 0 && Year2 < 0) {
//                        message = "Please choose Qualification";
//                        open2(v);
//                    }

                    if(valid<=0) {

                        //************************Volley Used Here********************************
                        StringRequest request = new StringRequest(Request.Method.POST, Links.USER_EDUCATION, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

//                                Variables.CheckSecondNavigation=1;

                                Toast.makeText(getContext(),"Successfully Saved", Toast.LENGTH_SHORT).show();

                                if (Variables.CheckSecondNavigation > 0) {
                                    Login login = new Login();
                                    FragmentManager fragmentManager = getFragmentManager();
                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                    fragmentTransaction.replace(R.id.frame, login);
                                    fragmentTransaction.commit();
                                } else {
                                    Personal personal = new Personal();
                                    FragmentManager fragmentManager = getFragmentManager();
                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                    fragmentTransaction.replace(R.id.frame, personal);
                                    fragmentTransaction.commit();
                                }


                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
//                                myPd_ring.dismiss();
                                if (error instanceof NetworkError) {
                                    Toast.makeText(getContext(), "Cannot connect to Internet...Please check your connection!", Toast.LENGTH_LONG).show();
                                } else if (error instanceof ServerError) {
                                    Toast.makeText(getContext(), "The server could not be found. Please try again after some time!!", Toast.LENGTH_LONG).show();
                                } else if (error instanceof AuthFailureError) {
                                    Toast.makeText(getContext(), "Cannot connect to Internet...Please check your connection !", Toast.LENGTH_LONG).show();
                                } else if (error instanceof ParseError) {
                                    Toast.makeText(getContext(), "Parsing error! Please try again after some time !!", Toast.LENGTH_LONG).show();

                                } else if (error instanceof NoConnectionError) {
                                    Toast.makeText(getContext(), "Cannot connect to Internet...Please check your connection !", Toast.LENGTH_LONG).show();
                                } else if (error instanceof TimeoutError) {
                                    Toast.makeText(getContext(), "Cannot connect to Internet...Please check your connection !", Toast.LENGTH_LONG).show();
                                }
                                // counter++;
                            }

                            {

                            }
                        }) {

                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> parameters = new HashMap<String, String>();

                                parameters.put("user_institute", instituteName.getText().toString());
                                parameters.put("user_passyear", year.getSelectedItem().toString());
                                parameters.put("user_coursetype", coursetype.getSelectedItem().toString());
                                parameters.put("user_qualification", qualification.getSelectedItem().toString());
                                parameters.put("user_email", Variables.client_email);
                                //parameters.put("user_city", city.getText().toString());

                                Variables.vqualification = qualification.getSelectedItem().toString();
                                Variables.vinstitute = instituteName.getText().toString();
                                Variables.vcourse = coursetype.getSelectedItem().toString();
                                Variables.vyearpass = year.getSelectedItem().toString();


                                return parameters;
                            }
                        };
                        requestQueue7.add(request);


                        //**************************Volley Ends Here***************************





                    }
                    else {
                        message=" Please fill in Details";
                        open2(v);
                    }
                }

        });


        return v;
    }


                    //////========================================================================================================

//                StringRequest request1 = new StringRequest(Request.Method.POST, insertUrl7, new Response.Listener<String>()
//                {
//                    @Override
//                    public void onResponse(String response)
//                    {
//
//
//                        System.out.println(response.toString());
//
//                        Toast.makeText(getContext(),"Password successfully sent to your email", Toast.LENGTH_SHORT).show();
//
//
//                    }
//                }, new Response.ErrorListener()
//                {
//                    @Override
//                    public void onErrorResponse(VolleyError error)
//                    {
//
//                    }
//                })
//                {
//
//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError
//                    {
//                        Map<String,String> parameters  = new HashMap<String, String>();
//                        parameters.put("email",eid.getText().toString());
//
//                        return parameters;
//                    }
//                };
//                requestQueue7.add(request1);
//                //=============================================================================================================






    private void qualification()//JSONArray j
    {
        Qualification.add("Qualification");
//        for (int i = 0; i < j.length(); i++)
//        {
//            try
//            {
//                JSONObject json = j.getJSONObject(i);
//
//
//                if(json.getString("business_type").equals("Doctor") || json.getString("business_type").equals("Hotel") ||
//                        json.getString("business_type").equals("Restaurant")|| json.getString("business_type").equals("Real Estate")
//                        || json.getString("business_type").equals("Hospital")|| json.getString("business_type").equals("Classes")
//                        || json.getString("business_type").equals("Education") || json.getString("business_type").equals("Entertainment")
//                        || json.getString("business_type").equals("Fitness") || json.getString("business_type").equals("Internet Service Provider")
//                        || json.getString("business_type").equals("Repair") || json.getString("business_type").equals("Showroom")
//                        || json.getString("business_type").equals("Saloon") || json.getString("business_type").equals("Travels"))
//                {
//
//                }
//
//                else
//                {
//
//                    qualiFication.add(json.getString("business_type"));//Config.TAG_USERNAME
//
//                }
//            } catch (JSONException e)
//            {
//                e.printStackTrace();
//            }
//        }

        Qualification.add("APGDCA");
        Qualification.add("BA");
        Qualification.add("BBA");
        Qualification.add("BCA");
        Qualification.add("BCOM");
        Qualification.add("BE");
        Qualification.add("BSC");
        Qualification.add("BTECH");
        Qualification.add("ITI");
        Qualification.add("MA");
        Qualification.add("MBA");
        Qualification.add("MCA");
        Qualification.add("MCOM");
        Qualification.add("Sql");
        Qualification.add("MS");
        Qualification.add("ME");
        Qualification.add("MSC");
        Qualification.add("ME");
        Qualification.add("MTECH");
        qualification.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, Qualification));
        qualification.setSelection(Qualification.indexOf(Variables.b));

    }

    //--------------------------------------------------------------------
    private void institute()//JSONArray j
    {
        Institute.add("Notice Period");
//        for (int i = 0; i < j.length(); i++)
//        {
//            try
//            {
//                JSONObject json = j.getJSONObject(i);
//
//
//                if(json.getString("business_type").equals("Doctor") || json.getString("business_type").equals("Hotel") ||
//                        json.getString("business_type").equals("Restaurant")|| json.getString("business_type").equals("Real Estate")
//                        || json.getString("business_type").equals("Hospital")|| json.getString("business_type").equals("Classes")
//                        || json.getString("business_type").equals("Education") || json.getString("business_type").equals("Entertainment")
//                        || json.getString("business_type").equals("Fitness") || json.getString("business_type").equals("Internet Service Provider")
//                        || json.getString("business_type").equals("Repair") || json.getString("business_type").equals("Showroom")
//                        || json.getString("business_type").equals("Saloon") || json.getString("business_type").equals("Travels"))
//                {
//
//                }
//
//                else
//                {
//
//                    qualiFication.add(json.getString("business_type"));//Config.TAG_USERNAME
//
//                }
//            } catch (JSONException e)
//            {
//                e.printStackTrace();
//            }
//        }
        Institute.add("");
        Institute.add("2 Months");
        Institute.add("3 Months");
        Institute.add("4 Months");
        Institute.add("5 Months");
        institute.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, Institute));
        institute.setSelection(Institute.indexOf(Variables.b));

    }
    //=======================================================================================
    private void course()//JSONArray j
    {
        Course.add("Course Type");
//        for (int i = 0; i < j.length(); i++)
//        {
//            try
//            {
//                JSONObject json = j.getJSONObject(i);
//
//
//                if(json.getString("business_type").equals("Doctor") || json.getString("business_type").equals("Hotel") ||
//                        json.getString("business_type").equals("Restaurant")|| json.getString("business_type").equals("Real Estate")
//                        || json.getString("business_type").equals("Hospital")|| json.getString("business_type").equals("Classes")
//                        || json.getString("business_type").equals("Education") || json.getString("business_type").equals("Entertainment")
//                        || json.getString("business_type").equals("Fitness") || json.getString("business_type").equals("Internet Service Provider")
//                        || json.getString("business_type").equals("Repair") || json.getString("business_type").equals("Showroom")
//                        || json.getString("business_type").equals("Saloon") || json.getString("business_type").equals("Travels"))
//                {
//
//                }
//
//                else
//                {
//
//                    qualiFication.add(json.getString("business_type"));//Config.TAG_USERNAME
//
//                }
//            } catch (JSONException e)
//            {
//                e.printStackTrace();
//            }
//        }
        Course.add("Full time");
        Course.add("Part time");
        Course.add("Distance Education");

        coursetype.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, Course));
        coursetype.setSelection(Course.indexOf(Variables.b));

    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void yearpass()//JSONArray j
    {
        Year.add("Year Of Passout");
//        for (int i = 0; i < j.length(); i++)
//        {
//            try
//            {
//                JSONObject json = j.getJSONObject(i);
//
//
//                if(json.getString("business_type").equals("Doctor") || json.getString("business_type").equals("Hotel") ||
//                        json.getString("business_type").equals("Restaurant")|| json.getString("business_type").equals("Real Estate")
//                        || json.getString("business_type").equals("Hospital")|| json.getString("business_type").equals("Classes")
//                        || json.getString("business_type").equals("Education") || json.getString("business_type").equals("Entertainment")
//                        || json.getString("business_type").equals("Fitness") || json.getString("business_type").equals("Internet Service Provider")
//                        || json.getString("business_type").equals("Repair") || json.getString("business_type").equals("Showroom")
//                        || json.getString("business_type").equals("Saloon") || json.getString("business_type").equals("Travels"))
//                {
//
//                }
//
//                else
//                {
//
//                    qualiFication.add(json.getString("business_type"));//Config.TAG_USERNAME
//
//                }
//            } catch (JSONException e)
//            {
//                e.printStackTrace();
//            }
//        }

        Year.add("1990");
        Year.add("1991");
        Year.add("1992");
        Year.add("1993");
        Year.add("1994");
        Year.add("1995");
        Year.add("1996");
        Year.add("1997");
        Year.add("1998");
        Year.add("1999");
        Year.add("2000");
        Year.add("2001");
        Year.add("2002");
        Year.add("2003");
        Year.add("2004");
        Year.add("2005");
        Year.add("2006");
        Year.add("2007");
        Year.add("2008");
        Year.add("2009");
        Year.add("2010");
        Year.add("2011");
        Year.add("2012");
        Year.add("2013");
        Year.add("2014");
        Year.add("2014");
        Year.add("2015");
        Year.add("2016");
        Year.add("2017");
        year.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, Year));
        year.setSelection(Year.indexOf(Variables.b));

    }
    public void open2(View v){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage(message);

        alertDialogBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
