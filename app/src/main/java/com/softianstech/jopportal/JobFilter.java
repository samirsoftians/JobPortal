package com.softianstech.jopportal;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 7/13/2017.
 */

public class JobFilter extends Fragment {

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

      //  Toast.makeText(getContext(), Variables.qualification, Toast.LENGTH_SHORT).show();

        listView = (ListView) v.findViewById(R.id.listView);
      //  url2 = Links.USER_DATA+Variables.client_email;
        requestQueue = com.android.volley.toolbox.Volley.newRequestQueue(getContext());
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

       // Toast.makeText(getContext(), Variables.qualification, Toast.LENGTH_SHORT).show();

        //************************Volley Used Here********************************
        StringRequest request = new StringRequest(Request.Method.POST, Links.USER_FILTERJOB, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                myPd_ring.dismiss();

             showJSON(response);
//                Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();


//                if(Variables.check==1)
//                {
//                Login education= new Login();
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.frame, education);
//                fragmentTransaction.commit();
//                }




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

                parameters.put("user_subjects", Variables.subjects);
                parameters.put("user_location", Variables.location);
                parameters.put("user_qualification", Variables.qualification);
                parameters.put("user_email", Variables.client_email);





                return parameters;
            }
        };
        requestQueue.add(request);


        //**************************Volley Ends Here***************************









        return v;


    }


    private void showJSON(String json){
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();

        // Toast.makeText(getContext(),ParseJSON.su, Toast.LENGTH_SHORT).show();
        CustomList2 cl = new CustomList2(getActivity(), ParseJSON.ids, ParseJSON.locations, ParseJSON.qualifications,ParseJSON.experiences,ParseJSON.subjects,ParseJSON.useremail);
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
