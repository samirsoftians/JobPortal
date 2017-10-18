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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
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

public class PlaceTeach extends Fragment {

    Animation shake,bounce;

    TextView place_teach;
    Button placebutton;
    RequestQueue requestQueue11;

    private ArrayList<String> subj;
    private ArrayList<String> locality;
    protected ArrayList<CharSequence> selectedColours = new ArrayList<CharSequence>();
    protected ArrayList<CharSequence> selectedSubjects = new ArrayList<CharSequence>();
    int sub=0;
    int valid=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // return super.onCreateView(inflater, container, savedInstanceState);
        View v=inflater.inflate(R.layout.placeteach,container,false);
        bounce = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);


        subj=new ArrayList<String>();
        locality=new ArrayList<String>();

        place_teach= (TextView) v.findViewById(R.id.place_teach);
        placebutton= (Button) v.findViewById(R.id.placebutton);
        requestQueue11 = com.android.volley.toolbox.Volley.newRequestQueue(getContext());

        placebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placebutton.startAnimation(bounce);
                valid=0;
                if(place_teach.getText().toString()=="")
                {
                    //place_teach.setError("Please Choose your prefered location");
                    Toast.makeText(getContext(), "Please Choose your prefered location", Toast.LENGTH_SHORT).show();
                    valid++;
                }
                //***********************Volley begins here**********************
                if(valid>0)
                {
                    //Toast.makeText(getContext(), "Please fill details", Toast.LENGTH_SHORT).show();
                }
                else {
                    // Toast.makeText(Registration.this, "hi samir", Toast.LENGTH_SHORT).show();

                    StringRequest request = new StringRequest(Request.Method.POST, Links.USER_TEACHINGPLACES, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Variables.vplaces=place_teach.getText().toString();


                            Toast.makeText(getContext(), "Saved successfully", Toast.LENGTH_SHORT).show();

                            Personal personal= new Personal();
                            FragmentManager fragmentManager = getFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.frame, personal);
                            fragmentTransaction.commit();


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
                            parameters.put("user_places", place_teach.getText().toString());
                            parameters.put("user_email", Variables.client_email);

                            //parameters.put("user_city", city.getText().toString());





                            return parameters;
                        }
                    };
                    requestQueue11.add(request);
                    //**********************Volley Ends here*************************
                }

            }
        });
        place_teach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sub=1;
                subj.clear();

                showSelectColoursDialog();

            }
        });

//        place_teach.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
//                View rootView = layoutInflater.inflate(R.layout.placeteach, null);
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
//                alertDialogBuilder.setView(rootView);
//
//                place_teach = (TextView) rootView.findViewById(R.id.place_teach);
//                // String date=yourEditText2.;
//                alertDialogBuilder.setCancelable(false);
//
//
//                alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
//                {
//                    @Override
//                    public void onClick(DialogInterface arg0, int arg1)
//                    {
//                        place_teach.setText(getCurrentDate());
//
//                    }
//                });
//                AlertDialog alert = alertDialogBuilder.create();
//                alert.show();
//            }
//        });
//        submit= (Button)v.findViewById(R.id.btnsubmit);
//        requestQueue7 = Volley.newRequestQueue(getContext());
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
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
////                //=============================================================================================================
//                Toast.makeText(getContext(),"Password successfully sent to your email", Toast.LENGTH_SHORT).show();
//                Login fragment2 = new Login();
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.frame, fragment2);
//                fragmentTransaction.commit();
//            }
//        });









        return v;


    }

    public String getCurrentDate(){
        StringBuilder builder=new StringBuilder();

//        builder.append((yourEditText2.getMonth() + 1)+"/");//month is 0 based
//        builder.append(yourEditText2.getDayOfMonth()+"/");
//        builder.append(yourEditText2.getYear());
        return builder.toString();
    }

    //**********************************************************************
    protected void showSelectColoursDialog()
    {

        if(sub==1)
        {
            subj.add("Amar");
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

            subj.add("Aundh");
            subj.add("Balewadi");
            subj.add("Baner");
            subj.add("Bavdhan");
            subj.add("Botanical Garden");
            subj.add("Dapodi");
            subj.add("Dhankawadi");
            subj.add("Gardon House");
            subj.add("Hadapsar");
            subj.add("Hingne");
            subj.add("Kadki East");
            subj.add("Kadki West");
            subj.add("Kondhwa kh");
            subj.add("Katraj");
            subj.add("Khadki");
            subj.add("Kharadi");
            subj.add("Kinara");
            subj.add("Laxmi Narayan");
            subj.add("Mohamadwadi");
            subj.add("warje");
            subj.add("wadgaon BK.");
            subj.add("wadgaon Kh");
            subj.add("Kothrud");
            subj.add("Wanawadi");
            subj.add("Viman Nagar");
            subj.add("Tilak Vidyapeeth");
            subj.add("Tanji Wadi");
            subj.add("Swargate");
            subj.add("Sutarwadi");
            subj.add("Shivane");
            subj.add("Shivajinagar");
            subj.add("Shalimar");
            subj.add("Sangavi");
            subj.add("Raviraj");
            subj.add("RajBhavan");
            subj.add("Pashan");
            subj.add("Parvati");
            subj.add("Neelayam");


            //*****************************************************
            boolean[] checkedSubjects = new boolean[subj.size()];

            int count = subj.size();

            for (int i = 0; i < count; i++)

                checkedSubjects[i] = selectedSubjects.contains(subj.get(i));

            DialogInterface.OnMultiChoiceClickListener coloursDialogListener2 = new DialogInterface.OnMultiChoiceClickListener() {

                @Override

                public void onClick(DialogInterface dialog, int which, boolean isChecked)
                {



                    if (isChecked)

                        selectedSubjects.add(subj.get(which));

                    else

                        selectedSubjects.remove(subj.get(which));

                    onChangeSelectedColours();

                }

            };


            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            builder.setTitle("Select Locations");

            builder.setPositiveButton("Submit",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //spinner.setSelection(students.indexOf("Business Type"));
                            ///  colours.substring(0, colours.length() - 1) ;

                            dialog.cancel();
                        }
                    });
            String [] subje = subj.toArray(new String[subj.size()]);

            builder.setMultiChoiceItems(subje, checkedSubjects, coloursDialogListener2);
            AlertDialog dialog = builder.create();

            dialog.show();



        }
        else
        {
            //*************************************************
            locality.add("Hinjewadi");
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
            locality.add("Katraj");
            locality.add("Karvenagar");
            locality.add("Hadapsar");
            locality.add("Kothrud");
            locality.add("Kondwagate");
            locality.add("Wakad");


            //*****************************************************
            boolean[] checkedColours = new boolean[locality.size()];

            int count = locality.size();

            for (int i = 0; i < count; i++)

                checkedColours[i] = selectedColours.contains(locality.get(i));

            DialogInterface.OnMultiChoiceClickListener coloursDialogListener = new DialogInterface.OnMultiChoiceClickListener() {

                @Override

                public void onClick(DialogInterface dialog, int which, boolean isChecked)
                {



                    if (isChecked)

                        selectedColours.add(locality.get(which));

                    else

                        selectedColours.remove(locality.get(which));

                    onChangeSelectedColours();

                }

            };

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            builder.setTitle("Select Places");

            builder.setPositiveButton("Submit",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //spinner.setSelection(students.indexOf("Business Type"));
                            ///  colours.substring(0, colours.length() - 1) ;

                            dialog.cancel();
                        }
                    });
            String [] countries = locality.toArray(new String[locality.size()]);

            builder.setMultiChoiceItems(countries, checkedColours, coloursDialogListener);
            AlertDialog dialog = builder.create();

            dialog.show();




        }

    }

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    protected void onChangeSelectedColours()
    {
        int a=0;
        StringBuilder stringBuilder = new StringBuilder();
        if(sub==1)
        {
            //StringBuilder stringBuilder2 = new StringBuilder();
            for(CharSequence colour : selectedSubjects)

                if(a==0)
                {
                    stringBuilder.append(colour);

                    place_teach.setText(stringBuilder.toString());
                    a++;
                }
                else
                {
                    stringBuilder.append("," + colour);
                    place_teach.setText(stringBuilder.toString());
                }
        }
        else
        {
            // StringBuilder stringBuilder = new StringBuilder();

            for(CharSequence colour : selectedColours)

                if(a==0)
                {
                    stringBuilder.append(colour);

                    place_teach.setText(stringBuilder.toString());
                    a++;
                }
                else
                {
                    stringBuilder.append("," + colour);
                    place_teach.setText(stringBuilder.toString());

                }
        }


//        stringBuilder.append(prefix + colour);
//        prefix=",";
//        place_teach.setText(stringBuilder.toString());

    }

}
