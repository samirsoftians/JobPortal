package com.softianstech.jopportal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by Lenovo on 6/6/2017.
 */

public class ForgetPassword extends Fragment {

    EditText eid;
    Button submit;
    RequestQueue requestQueue7;//
    Context context;
    String insertUrl7 =Links.USER_FORGET;
    int valid=0;
    String message;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // return super.onCreateView(inflater, container, savedInstanceState);
        View v=inflater.inflate(R.layout.forgetpass,container,false);

        requestQueue7 = com.android.volley.toolbox.Volley.newRequestQueue(getContext());


        eid= (EditText) v.findViewById(R.id.email_id);
        submit= (Button)v.findViewById(R.id.btnsubmit);
        requestQueue7 = Volley.newRequestQueue(getContext());
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                valid=0;

                if(eid.getText().toString().isEmpty())
                {
                    eid.setError("Please enter Email-id");
                    valid++;
                    message="Please enter Email-id";
                }
                if (!checkEmail(eid.getText().toString())) {
                    eid.setError("Invalid Email");

                    // Toast.makeText(SignUpActivity.this, "Paswword lenght should be greater than 6 char", Toast.LENGTH_SHORT).show();
                    valid++;
                    message="Invalid Email";

                }
                if(valid>0)
                {

                }
                else {

                    StringRequest request1 = new StringRequest(Request.Method.POST, insertUrl7, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            System.out.println(response.toString());

                            Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                           // Toast.makeText(getContext(), "Password successfully sent to your email", Toast.LENGTH_SHORT).show();
                            Login fragment2 = new Login();
                            FragmentManager fragmentManager = getFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.frame, fragment2);
                            fragmentTransaction.commit();


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> parameters = new HashMap<String, String>();
                            parameters.put("user_email", eid.getText().toString());

                            return parameters;
                        }
                    };
                    requestQueue7.add(request1);
//
// =============================================================================================================
                }
            }
        });









        return v;


    }
    public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9+._%-+]{1,256}" +
                    "@" +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
                    "(" +
                    "." +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
                    ")+"
    );



    private boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }


}
