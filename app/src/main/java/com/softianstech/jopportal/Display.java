package com.softianstech.jopportal;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
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
 * Created by Lenovo on 6/6/2017.
 */

public class Display extends Fragment {

    private ListView listView;
    String url2;
    String myString4;
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // return super.onCreateView(inflater, container, savedInstanceState);
        View v=inflater.inflate(R.layout.display,container,false);

        listView = (ListView) v.findViewById(R.id.listView);
        url2 = Links.USER_DATA+Variables.client_email;

        final ProgressDialog myPd_ring= ProgressDialog.show(getContext(), "", "Please wait......", true);
        myPd_ring.setCancelable(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                try
                {
                    Thread.sleep(10000);
                }catch(Exception e){

                }

            }
        }).start();



        StringRequest stringRequest = new StringRequest(url2,new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                //showJSON(response);



                //Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                myPd_ring.dismiss();
            }
        },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        myPd_ring.dismiss();
                        if (error instanceof NetworkError)
                        {
                            Toast.makeText(getContext(),"Cannot connect to Internet...Please check your connection!",Toast.LENGTH_LONG ).show();
                        }
                        else if (error instanceof ServerError)
                        {
                            Toast.makeText(getContext(),"The server could not be found. Please try again after some time!!",Toast.LENGTH_LONG ).show();
                        }
                        else if (error instanceof AuthFailureError)
                        {
                            Toast.makeText(getContext(),"Cannot connect to Internet...Please check your connection !",Toast.LENGTH_LONG ).show();
                        }
                        else if (error instanceof ParseError)
                        {
                            Toast.makeText(getContext(),"Parsing error! Please try again after some time !!",Toast.LENGTH_LONG ).show();

                        }
                        else if (error instanceof NoConnectionError)
                        {
                            Toast.makeText(getContext(),"Cannot connect to Internet...Please check your connection !",Toast.LENGTH_LONG ).show();
                        }
                        else if (error instanceof TimeoutError)
                        {
                            Toast.makeText(getContext(),"Cannot connect to Internet...Please check your connection !",Toast.LENGTH_LONG ).show();
                        }




                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

        //**************************************










        return v;


    }


    private void showJSON(String json){
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();

       // Toast.makeText(getContext(),ParseJSON.su, Toast.LENGTH_SHORT).show();
//        CustomList cl = new CustomList(getActivity(), ParseJSON.ids, ParseJSON.locations, ParseJSON.qualifications,ParseJSON.experiences,ParseJSON.subjects);
//        listView.setAdapter(cl);

        //listView.setOnClickListener();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
//                Object o = listView.getItemAtPosition(position);
//                String str=(String)o;//As you are using Default String Adapter
//                Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
               // Toast.makeText(Display.this, "samir", Toast.LENGTH_SHORT).show();
            }
        });


    }


}
