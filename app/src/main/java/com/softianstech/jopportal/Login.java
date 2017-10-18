package com.softianstech.jopportal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 6/1/2017.
 */

public class Login extends Fragment {
    Animation shake,bounce;
    public static SharedPreferences sharedpreferences;
    EditText loginEmail,loginPassword;
    Button loginSignin;
    TextView forgot;
    DBHelp db;
    String sqliteEmail,SqlitePassword;
    int shared=1;
    String tok2;

    Button loginlogin;
    Button loginregister;

    //*****************************SHARRED PREFERENCES TO STORE DATA****
    public static final String mypreference = "mypref";
    public static final String Password = "nameKey";
    public static final String Email = "emailKey";
    //****************************END HERE*************************


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // return super.onCreateView(inflater, container, savedInstanceState);




        View v=inflater.inflate(R.layout.login,container,false);


        //********************************************************************



        //*******************************************************

        bounce = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);

//        if (sharedpreferences.contains(Email)) {
//            MainActivity.loginregester2.setVisibility(View.INVISIBLE);
//            MainActivity.loginlogin2.setVisibility(View.INVISIBLE);
//
//        }
        loginEmail= (EditText) v.findViewById(R.id.login_email);
        loginPassword= (EditText) v.findViewById(R.id.login_password);
        forgot= (TextView) v.findViewById(R.id.forgot);



        loginSignin= (Button) v.findViewById(R.id.login_signin);

       // sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);//here is the shared preference

        db=new DBHelp(getActivity());

        sharedpreferences = getContext().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        FirebaseInstanceIDService.sharedpreferences1 = getContext().getSharedPreferences(FirebaseInstanceIDService.myToken, Context.MODE_PRIVATE);

         tok2=FirebaseInstanceIDService.sharedpreferences1.getString(FirebaseInstanceIDService.userToken, "");
       // Toast.makeText(getContext(), tok2, Toast.LENGTH_SHORT).show();




        if(Variables.CheckSecondNavigation>0) {
            loginEmail.setText(Variables.client_email);
        }
        else {
            if (sharedpreferences.contains(Email)) {
                Variables.islogin=2;
                loginEmail.setText(sharedpreferences.getString(Email, ""));
                shared++;
                Variables.client_email = sharedpreferences.getString(Email, "");
            }
            if (sharedpreferences.contains(Password)) {
                loginPassword.setText(sharedpreferences.getString(Password, ""));
                shared++;
            }
        }

        Variables.CheckSecondNavigation=0;//to make only to execute only when registration is done


//if(shared>2)
//{
//    Variables.client_email=loginEmail.getText().toString();
//    Variables.Name="";
//    Variables.vphone="";
//    Variables.vemail=loginEmail.getText().toString();
//    Variables.vcurrentlocation="";
//    Variables.vdateofbirth="";
//    Variables.vgender="";
//    Variables.vtotexperience="";
//    Variables.vsubjects="";
//    Variables.vnoticeperiod="";
//    Variables.vqualification="";
//    Variables.vinstitute="";
//    Variables.vcourse="";
//    Variables.vyearpass="";
//    Variables.vplaces="";
//    login();
//}


//        loginEmail.setText(db.GetQuestion("Email"));
//        loginPassword.setText(db.GetQuestion("Pass"));
//        Toast.makeText(getContext(), db.GetQuestion("Email"), Toast.LENGTH_SHORT).show();
        loginlogin= (Button) v.findViewById(R.id.loginlogin);
        loginregister= (Button) v.findViewById(R.id.loginregister);



       // loginlogin.setBackgroundColor(Color.YELLOW);Color.parseColor("#FFFFFF")
        loginlogin.setBackgroundColor(Color.parseColor("#fd8701"));
        loginSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginSignin.startAnimation(bounce);

                //Toast.makeText(getContext(),String.valueOf(sharedpreferences.getString(FirebaseInstanceIDService.userToken, "")) , Toast.LENGTH_LONG).show();

                if(loginEmail.getText().toString().trim().equals(""))
                {
                    loginEmail.setError("Please Enter your Email");
                }
                if(loginPassword.getText().toString().trim().equals(""))
                {
                    loginPassword.setError("Please Enter Password");
                }
                else {

                    Variables.client_email = loginEmail.getText().toString();
                    Variables.Name = "";
                    Variables.vphone = "";
                    Variables.vemail = loginEmail.getText().toString();
                    Variables.vcurrentlocation = "";
                    Variables.vdateofbirth = "";
                    Variables.vgender = "";
                    Variables.vtotexperience = "";
                    Variables.vsubjects = "";
                    Variables.vnoticeperiod = "";
                    Variables.vqualification = "";
                    Variables.vinstitute = "";
                    Variables.vcourse = "";
                    Variables.vyearpass = "";
                    Variables.vplaces = "";


                    login();
                }
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForgetPassword forgetPassword = new ForgetPassword();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, forgetPassword);
                fragmentTransaction.commit();
            }
        });






        loginlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginlogin.setBackgroundColor(Color.parseColor("#fd8701"));

                //loginlogin.setTextColor(Color.parseColor("#fd8701"));
                //loginlogin.setBackgroundColor(Color.parseColor("#fd8701"));

                Login fragment2 = new Login();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment2);
                fragmentTransaction.commit();
            }
        });



        loginregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginregister.setBackgroundColor(Color.parseColor("#fd8701"));
                LoginRegistration fragment3 = new LoginRegistration();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment3);
                fragmentTransaction.commit();
            }
        });







        return v;


    }

    public void login() {

//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        //MainActivity mainactivity=new MainActivity();
//
//        TextView welcome1 = (TextView)navigationView.getHeaderView(0).findViewById(R.id.welcome1);
//        welcome1.setText(Variables.client_email2);

        //*******************Volley for cheing validation ====================================
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Links.LOGIN,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        // myPd_ring.dismiss();
                        if(response.trim().equals("Login Successful"))
                        {

                            updatetoken();

//                            Variables.islogin=2;
//
//                            MainActivity.loginregester2.setVisibility(View.INVISIBLE);
//                            MainActivity.loginlogin2.setVisibility(View.INVISIBLE);
//
//
//                            getImage();
//
//                           MainActivity.welcome1.setText(loginEmail.getText().toString());
//
//                            String e = loginEmail.getText().toString();
//                            String n = loginPassword.getText().toString();
//                            SharedPreferences.Editor editor = sharedpreferences.edit();
//                            editor.putString(Password, n);
//                            editor.putString(Email, e);
//                            editor.commit();
//
//
//
//
//
//                            Variables.CheckSecondNavigation=0;//to make only to execute only when registration is done
//
//
//                            Variables.client_email=loginEmail.getText().toString();
//                            Variables.client_email2=loginEmail.getText().toString();
//                            Toast.makeText(getContext(),"Login Successful", Toast.LENGTH_LONG).show();
//
//
//
//
//                            AllJobs display= new AllJobs();
//                            FragmentManager fragmentManager = getFragmentManager();
//                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                            fragmentTransaction.replace(R.id.frame, display);
//                            fragmentTransaction.commit();

                        }
                        else
                        {
                            Toast.makeText(getContext(),"Please Check Email or Password",Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        //  myPd_ring.dismiss();
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
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> parameters  = new HashMap<String, String>();
                parameters.put("user_email",loginEmail.getText().toString());
                parameters.put("user_password",loginPassword.getText().toString());



                return parameters;
            }
        };

        RequestQueue requestQueue = com.android.volley.toolbox.Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
        //******************Volley ends here****************************************
    }

    private void getImage() {
        //  String id = editTextId.getText().toString().trim();
        class GetImage extends AsyncTask<String, Void, Bitmap> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();


                //loading = ProgressDialog.show(MainActivity.this, "Retriving image...", null, true, true);
            }

            @Override
            protected void onPostExecute(Bitmap b) {
                super.onPostExecute(b);
                // loading.dismiss();
                MainActivity.imageView1.setImageBitmap(b);
               // Personal.imageView2.setImageBitmap(b);
            }

            @Override
            protected Bitmap doInBackground(String... params) {
                String id = params[0];
                String add = Links.USER_IMAGE+sharedpreferences.getString(Email, "");
                URL url = null;
                Bitmap image = null;
                try {
                    url = new URL(add);
                    image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return image;
            }
        }

        GetImage gi = new GetImage();
        gi.execute(Variables.client_email);
    }



    public void updatetoken()
    {
        StringRequest request = new StringRequest(Request.Method.POST, Links.UPDATE_TOKEN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Variables.islogin=2;

                MainActivity.loginregester2.setVisibility(View.INVISIBLE);
                MainActivity.loginlogin2.setVisibility(View.INVISIBLE);


                getImage();

                MainActivity.welcome1.setText(loginEmail.getText().toString());

                String e = loginEmail.getText().toString();
                String n = loginPassword.getText().toString();
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(Password, n);
                editor.putString(Email, e);
                editor.commit();





                Variables.CheckSecondNavigation=0;//to make only to execute only when registration is done


                Variables.client_email=loginEmail.getText().toString();
                Variables.client_email2=loginEmail.getText().toString();
                Toast.makeText(getContext(),"Login Successful", Toast.LENGTH_LONG).show();




                AllJobs display= new AllJobs();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, display);
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

                           //String tok2=FirebaseInstanceIDService.sharedpreferences1.getString(FirebaseInstanceIDService.userToken, "");
                parameters.put("user_token",tok2 );
                parameters.put("user_email",loginEmail.getText().toString());
                return parameters;
            }
        };
        RequestQueue requestQueue = com.android.volley.toolbox.Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }
}
