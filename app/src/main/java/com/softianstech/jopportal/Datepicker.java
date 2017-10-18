package com.softianstech.jopportal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

/**
 * Created by Lenovo on 6/10/2017.
 */

public class Datepicker extends Fragment {

    DatePicker picker;
    Button displayDate;
    TextView textview1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // return super.onCreateView(inflater, container, savedInstanceState);
        View v=inflater.inflate(R.layout.datepicker,container,false);




        textview1=(TextView)v.findViewById(R.id.textView);
        picker=(DatePicker)v.findViewById(R.id.datePicker);
        displayDate=(Button)v.findViewById(R.id.button);


        textview1.setText(getCurrentDate());

        displayDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textview1.setText(getCurrentDate());
            }

        });
      //  submit.setOnClickListener(new View.OnClickListener() {
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
        builder.append("Current Date: ");
        builder.append((picker.getMonth() + 1)+"/");//month is 0 based
        builder.append(picker.getDayOfMonth()+"/");
        builder.append(picker.getYear());
        return builder.toString();
    }

}
