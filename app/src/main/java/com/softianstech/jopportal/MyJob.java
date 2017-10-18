package com.softianstech.jopportal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.StringTokenizer;

/**
 * Created by Lenovo on 7/15/2017.
 */

public class MyJob extends Fragment {

    TextView exp;
    int a=0;
//    public static String stringbuffer;
    StringBuilder subjectsTable=new StringBuilder();





    private ListView listView;
    String url2;
    String myString4;
    Context context;
    RequestQueue requestQueue;

    public FragmentActivity myContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // return super.onCreateView(inflater, container, savedInstanceState);
        View v=inflater.inflate(R.layout.display,container,false);


//        if(MainActivity.h==1)
//        {
//            MainActivity.h=0;
//
//            AllJobs fragment2 = new AllJobs();
//            FragmentManager fragmentManager = getFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.frame, fragment2);
//            fragmentTransaction.commit();
//        }




        exp= (TextView) v.findViewById(R.id.exp);

        listView = (ListView) v.findViewById(R.id.listView);
        //  url2 = Links.USER_DATA+Variables.client_email;
        requestQueue = com.android.volley.toolbox.Volley.newRequestQueue(getContext());
        //final ProgressDialog myPd_ring= ProgressDialog.show(getContext(), "", "Please wait......", true);
//        myPd_ring.setCancelable(true);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                // TODO Auto-generated method stub
//                try
//                {
//                    Thread.sleep(10000);
//                }catch(Exception e){
//
//                }
//
//            }
//        }).start();



    //************************Volley Used Here********************************
    StringRequest stringRequest84111 = new StringRequest(Links.USER_MYJOB + Variables.client_email, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            //myPd_ring.dismiss();
            // Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();


            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray result = jsonObject.getJSONArray(Variables.JSON_ARRAY);
                JSONObject collegeData = result.getJSONObject(0);


                Variables.subject = collegeData.getString(Variables.user_subjects);


                StringTokenizer tokenizer = new StringTokenizer(Variables.subject, ",");

                while (tokenizer.hasMoreTokens()) {


                    if (a == 0) {
                        subjectsTable.append("'");
                        subjectsTable.append(tokenizer.nextToken());
                        subjectsTable.append("'");
                        a++;
                    } else {

                        subjectsTable.append("," + "" + "'" + tokenizer.nextToken() + "'");

                    }


                    //   Toast.makeText(getContext(), tokenizer.nextToken(), Toast.LENGTH_SHORT).show();

                }
                Variables.stringbuffer = String.valueOf(subjectsTable);
                //**************Toat to check if the subjects are convertred or not *********************

             //   Toast.makeText(getContext(), stringbuffer, Toast.LENGTH_SHORT).show();

                //*************************************************************************************


                //Variables.Name=per_name.getText().toString();

            } catch (JSONException e) {
                e.printStackTrace();
            }


            //showJSON(response);
        }
    },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

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

                    //Toast.makeText(Update.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
//                        Config.refresh++;
//
//                        if(Config.refresh>2)
//                        {
//                            Refresh();
//                        }
//                        else
//                        {
//                            Intent intent = new Intent(Update2.this, Update2.class);
//                            Bundle bundle = new Bundle();
//                            bundle.putString("cemail1", myString2);
//                            intent.putExtras(bundle);
//                            startActivity(intent);
//                        }


                }
            });

    RequestQueue requestQueue8411 = Volley.newRequestQueue(getContext());
    requestQueue8411.add(stringRequest84111);

    //**************************Volley Ends Here****************************/






if(Variables.g==0)
{
    Variables.g=1;
    FragmentTransaction ft = getFragmentManager().beginTransaction();
    ft.detach(MyJob.this).attach(MyJob.this).commit();
}




















//--------------------------------------------------------------------------------------------------------------
    // stringbuffer= String.valueOf(subjectsTable);

    // *********************************Volley Inside Volley***********************************************
    //****************************************Volley to get data fill in the Edittext**********************

    //  Toast.makeText(getContext(), stringbuffer, Toast.LENGTH_SHORT).show();

    StringRequest stringRequest8415 = new StringRequest(Links.USER_MYJOB2 + Variables.stringbuffer, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            //myPd_ring.dismiss();

            //Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();


            showJSON(response);






        }
    },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

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

                    //Toast.makeText(Update.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
//                        Config.refresh++;
//
//                        if(Config.refresh>2)
//                        {
//                            Refresh();
//                        }
//                        else
//                        {
//                            Intent intent = new Intent(Update2.this, Update2.class);
//                            Bundle bundle = new Bundle();
//                            bundle.putString("cemail1", myString2);
//                            intent.putExtras(bundle);
//                            startActivity(intent);
//                        }


                }
            });

    RequestQueue requestQueue8415 = Volley.newRequestQueue(getContext());
    requestQueue8415.add(stringRequest8415);
//


//        //   *******************************Second Volley Finishes here*****************************
//

    //----------------------------------------------------------------------------------------------------------------------------------


//
//        MyJob fragment2 = new MyJob();
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.frame, fragment2);
//        fragmentTransaction.commit();



        return v;


    }


    private void showJSON(String json){
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();

        // Toast.makeText(getContext(),ParseJSON.su, Toast.LENGTH_SHORT).show();
        CustomList3 cl = new CustomList3(getActivity(), ParseJSON.ids, ParseJSON.locations, ParseJSON.qualifications,ParseJSON.experiences,ParseJSON.subjects);
        listView.setAdapter(cl);


//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//
////                if(Variables.client_email.isEmpty()) {
//                    Login applyedJob = new Login();
//                    FragmentTransaction fragmentTransaction =getActivity().getSupportFragmentManager().beginTransaction();
//                    fragmentTransaction.replace(R.id.frame, applyedJob);
//                    fragmentTransaction.commit();
//               // }
//
//
////I ADDED ON CLICK IMPLEMENTATION HERE, BUT THIS IS NOT WORKING
//                //Toast.makeText(getApplicationContext(), "CLICKED", Toast.LENGTH_SHORT).show();
//            }
//        });




    }
}
