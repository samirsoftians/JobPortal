package com.softianstech.jopportal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
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

/**
 * Created by Lenovo on 7/26/2017.
 */

public class AllJobs extends Fragment {

    TextView exp;
    int a=0;
    //    public static String stringbuffer;
    StringBuilder subjectsTable=new StringBuilder();

   // Personal p;





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
        final View v=inflater.inflate(R.layout.display,container,false);


//        p=new Personal();

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






//--------------------------------------------------------------------------------------------------------------
        // stringbuffer= String.valueOf(subjectsTable);

        // *********************************Volley Inside Volley***********************************************
        //****************************************Volley to get data fill in the Edittext**********************

        //  Toast.makeText(getContext(), stringbuffer, Toast.LENGTH_SHORT).show();

        StringRequest stringRequest8415 = new StringRequest(Links.USER_MYJOB3+Variables.client_email, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //myPd_ring.dismiss();

//                Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
//                Toast.makeText(getContext(), Variables.client_email, Toast.LENGTH_LONG).show();

                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        if (error instanceof NetworkError) {

                            neterror(v);
                          // Toast.makeText(getContext(), "Cannot connect to Internet...Please check your connection!", Toast.LENGTH_LONG).show();
                        } else if (error instanceof ServerError) {

                            Toast.makeText(getContext(), "The server could not be found. Please try again after some time!!", Toast.LENGTH_LONG).show();
                        } else if (error instanceof AuthFailureError) {
                            neterror(v);
                           // Toast.makeText(getContext(), "Cannot connect to Internet...Please check your connection !", Toast.LENGTH_LONG).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(getContext(), "Parsing error! Please try again after some time !!", Toast.LENGTH_LONG).show();

                        } else if (error instanceof NoConnectionError) {
                            neterror(v);
                            //Toast.makeText(getContext(), "Cannot connect to Internet...Please check your connection !", Toast.LENGTH_LONG).show();
                        } else if (error instanceof TimeoutError) {
                            neterror(v);
                            //Toast.makeText(getContext(), "Cannot connect to Internet...Please check your connection !", Toast.LENGTH_LONG).show();
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





        return v;


    }


    private void showJSON(String json){
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();

        // Toast.makeText(getContext(),ParseJSON.su, Toast.LENGTH_SHORT).show();
        CustomList c1 = new CustomList(getActivity(), ParseJSON.ids, ParseJSON.locations, ParseJSON.qualifications,ParseJSON.experiences,ParseJSON.subjects,ParseJSON.useremail);
        listView.setAdapter(c1);


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

    //*******************************Allert Dialog Box**************************
    public  void neterror(View view){
        AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(getContext());
        alertDialogBuilder2.setMessage("Please Check Your Internet Connection");

        alertDialogBuilder2.setPositiveButton("Refresh",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        AllJobs personalInfo = new AllJobs();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame, personalInfo);
                        fragmentTransaction.commit();
                    }
                });

        AlertDialog alertDialog2 = alertDialogBuilder2.create();
        alertDialog2.show();
    }

    //*****************************Allert Dialog box ends here***********************
}
