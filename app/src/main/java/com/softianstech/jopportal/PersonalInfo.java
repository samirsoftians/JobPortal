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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
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
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by Lenovo on 6/7/2017.
 */

public class PersonalInfo extends Fragment {
    Animation shake,bounce;
    EditText pName,pPhone,pEmail;
    Spinner pLocation;
    Button pSave;
    RadioButton male,female;
    String gender;
    TextView pBirth;

    RequestQueue requestQueue;

    LoginRegistration loginRegistration;

    private JSONArray result;
    private ArrayList<String> city;

    DatePicker yourEditText2;
    int valid =0;
    String message;
    int a=1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.personalfragment, container, false);

        bounce = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
        pName= (EditText) v.findViewById(R.id.pnane);
        pPhone= (EditText) v.findViewById(R.id.pphone);
        pBirth= (TextView) v.findViewById(R.id.pbirth);
        pEmail= (EditText) v.findViewById(R.id.pemail);
        male= (RadioButton) v.findViewById(R.id.male);
        female= (RadioButton) v.findViewById(R.id.female);
        pLocation= (Spinner) v.findViewById(R.id.plocation);
        pSave= (Button) v.findViewById(R.id.psave);

        loginRegistration=new LoginRegistration();

     //   loginRegistration.getStudents();

        requestQueue = com.android.volley.toolbox.Volley.newRequestQueue(getContext());

        city=new ArrayList<String>();

        getcity();

//*******************Autofill the textview************************

        //****************************************Volley to get data fill in the Edittext**********************

        StringRequest stringRequest8411 = new StringRequest(Links.User_GETDATA+Variables.client_email, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                //myPd_ring.dismiss();

               //  Toast.makeText(getContext(),response,Toast.LENGTH_LONG ).show();


                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {

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

        RequestQueue requestQueue841 = Volley.newRequestQueue(getContext());
        requestQueue841.add(stringRequest8411);

        //************************************End of volley here*************************************************


//*************************AutoFill Ends Here****************************


        //Variables.vgender=gender;

       if(Variables.vgender.equals("Male"))
        {
            male.setChecked(true);
            gender="Male";

        }
        else if(Variables.vgender.equals("Female"))
        {
            female.setChecked(true);
            gender="Female";

        }
        else
        {
            male.setChecked(false);
            female.setChecked(false);
        }


        //**********************************
        pBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                View rootView = layoutInflater.inflate(R.layout.dialogbox, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setView(rootView);

                 yourEditText2 = (DatePicker) rootView.findViewById(R.id.datePicker2);
               // String date=yourEditText2.;
                alertDialogBuilder.setCancelable(false);


                alertDialogBuilder.setPositiveButton("Click Ok", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                        pBirth.setText(getCurrentDate());

                    }
                });
                AlertDialog alert = alertDialogBuilder.create();
                alert.show();
            }
        });

          //*********************************************



//******************************************Gender button on set click lister functions **************


        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                female.setChecked(false);
                gender="Male";
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male.setChecked(false);
                gender="Female";
            }
        });

//************************************************Gender button finishes here*****************************


        pSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pSave.startAnimation(bounce);
                valid=0;

                if(Variables.client_email.equals(""))//here it was client_email2
                {
                    valid++;





                }

//                if(pName.getText().toString().equals("null"))
//                {
//                    valid++;
//                    message="Please enter Name";
//                }
//                if(pPhone.getText().toString().equals("null"))
//                {
//                    valid++;
//                    message="Please enter Phone Number";
//                }
//                if(pEmail.getText().toString().equals("null"))
//                {
//                    valid++;
//                    message="Please enter Email";
//                }
//                if(pBirth.getText().toString().equals("null"))
//                {
//                    valid++;
//                    message="Please Select Date of birth";
//                }
//                if(gender=="null")
//                {
//                    valid++;
//                    message="Please Choose gender";
//                }

            else {

//                    pName.setText(Variables.Name);
//
//                    pPhone.setText(Variables.vphone);
//
//                 //   pBirth.setText(Variables.vdateofbirth);
//
//
//
//                    pEmail.setText(Variables.vemail);



//                    pLocation.setSelection(city.indexOf(Variables.vcurrentlocation));
                    //**********************************************************
                    if (pName.getText().toString().isEmpty()) {
                        valid++;
                        message = "Please enter Name";
                    }
                    if (pPhone.getText().toString().isEmpty()) {
                        valid++;
                        message = "Please enter Phone Number";
                    }
                    if (pEmail.getText().toString().isEmpty()) {
                        valid++;
                        message = "Please enter Email";
                    }
                    if (pBirth.getText().toString()=="") {
                        valid++;
                        message = "Please Select Date of birth";
                    }
                    if (gender.isEmpty()) {
                        valid++;
                        message = "Please Choose gender";
                    }
                    if (pLocation.getSelectedItem().toString().equals("City")) {
                        valid++;
                        message = "Please select a City";
                    }


                    if (pPhone.getText().toString().length() < 10 || pPhone.getText().toString().length() >= 12) {

                        pPhone.setError("Please enter valid phone number");
                        // Toast.makeText(SignUpActivity.this,"Please enter valid phone number",Toast.LENGTH_SHORT).show();
                        valid++;
                        message = "Please enter a valid Phone Number";
                    }
                    if (!checkEmail(pEmail.getText().toString())) {
                        pEmail.setError("Invalid Email");
                        message = "Please enter valid Email";
                        // Toast.makeText(SignUpActivity.this, "Paswword lenght should be greater than 6 char", Toast.LENGTH_SHORT).show();
                        valid++;

                    }
                }


                if(valid>0)
                {

//                    if(a==1)
//                    {
//                        allert();
//                    }
                  //  else {
                        open5(v);
                   // }
                }
                else
                    {


                    StringRequest request = new StringRequest(Request.Method.POST, Links.User_UPDATEPROFILE, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            //Toast.makeText(getContext(),response , Toast.LENGTH_SHORT).show();

                            Personal personal = new Personal();
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
                            parameters.put("user_name", pName.getText().toString());
                            parameters.put("user_phone", pPhone.getText().toString());
                            parameters.put("user_birth", pBirth.getText().toString());
                            parameters.put("user_email", pEmail.getText().toString());
                            parameters.put("user_city", pLocation.getSelectedItem().toString());
                            parameters.put("user_gender", gender);
                            parameters.put("user_preemail", Variables.client_email);

                            Variables.Name = pName.getText().toString();
                            Variables.vphone = pPhone.getText().toString();
                            Variables.vdateofbirth = pBirth.getText().toString();
                            Variables.vemail = pEmail.getText().toString();
                            Variables.vcurrentlocation = pLocation.getSelectedItem().toString();
                            Variables.city = pLocation.getSelectedItem().toString();
                            Variables.vgender = gender;

                            return parameters;
                        }
                    };
                    requestQueue.add(request);
                }


            }
        });




        return v;
    }



    public void getcity()//here it was JSONArray j
    {

        //if(Variables.city==" ")
       // {
            city.add("City");
//        }
//        else
//        {
//            city.add(Variables.city);
//        }


        city.add("Mumbai");
        city.add("Pune");
        city.add("Hydrabad");
        city.add("Delhi");
        city.add("Kolkatta");
        city.add("Chennai");
        city.add("Banglore");
        city.add("Noida");
        city.add("Gurgaon");
        city.add("Faridabad");
        city.add("Kochi");
        city.add("Jaipur");
        city.add("Chandigarh");
        city.add("Ahmedabad");
        city.add("Others");
        pLocation.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, city));
        pLocation.setSelection(city.indexOf(Variables.city));

    }



    public String getCurrentDate(){
        StringBuilder builder=new StringBuilder();

        builder.append((yourEditText2.getMonth() + 1)+"/");//month is 0 based
        builder.append(yourEditText2.getDayOfMonth()+"/");
        builder.append(yourEditText2.getYear());
        return builder.toString();
    }

    public void open5(View v){
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


//    public void allert()//This is of no use now
//    {
//        AlertDialog.Builder ad1=new AlertDialog.Builder(getContext());
//        ad1.setMessage("You are not Logedin");
//        ad1.setCancelable(false);
//
//
//
//
//        ad1.setPositiveButton("Login", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface arg0, int arg1) {
//
//                Login login = new Login();
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.frame, login);
//                fragmentTransaction.commit();
//
//
//            }
//        });
//        AlertDialog alert=ad1.create();
//        alert.show();
//    }


    //***********************************Filling the TextView by Default********************
    private void showJSON(String response)
    {
        try
        {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Variables.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);

//            if(collegeData.getString(Variables.user_name).equals(null))
//            {
//
//            }

//            else {

                pName.setText(collegeData.getString(Variables.user_name));
                pPhone.setText(collegeData.getString(Variables.user_phone));
                pEmail.setText(collegeData.getString(Variables.user_email));
                //.setText(collegeData.getString(Variables.user_address));

            if(pName.getText().toString().equals("null"))
            {
                pLocation.setSelection(city.indexOf("City"));
            }
            else {
                pLocation.setSelection(city.indexOf(collegeData.getString(Variables.user_city)));
            }

                //per_current_location.setText(collegeData.getString(Variables.user_city));// we dont require email but we get it from the server







            pBirth.setText(collegeData.getString(Variables.user_birth));







                Variables.city=pLocation.getSelectedItem().toString();







                Variables.Name=pName.getText().toString();
                Variables.vphone=pPhone.getText().toString();
                Variables.vemail=pEmail.getText().toString();
                Variables.vdateofbirth=pBirth.getText().toString();

                Variables.vgender = collegeData.getString(Variables.user_gender);
                //Variables.vgender=per_gender.getText().toString();

                if(Variables.vgender.equals("Male"))
                {
                    male.setChecked(true);
                    gender="Male";

                }
                else if(Variables.vgender.equals("Female"))
                {
                    female.setChecked(true);
                    gender="Female";

                }
                else
                {
                    male.setChecked(false);
                    female.setChecked(false);
                }


            }
          //  }

 catch (JSONException e) {
            e.printStackTrace();

















        }
//        city2.setSelection(students2.indexOf(city5));
//        Spinner3.setSelection(students.indexOf(business5));
//        Name1.setText(name5);
//        Bname1.setText(bname5);
//        Address1.setText(address5);
//        EtPhoneNumber1.setText(contact5);
//        Password1.setText(password5);
//        Config.city=city5;
//        Config.b=business5;
//        Config.n=Name1.getText().toString();
//        Config.a=Address1.getText().toString();
//        Config.e=myString2;//TvEmailAddrss1.getText().toString();
//        Config.p=EtPhoneNumber1.getText().toString();
//        Config.c=Cpass1.getText().toString();
//        Config.pa=Password1.getText().toString();
//        Config.busname=Bname1.getText().toString();
//**********************************Not Working Code******************
//**************************************************************************
//        if(Name1.getText().toString().equals(""))
//        {Config.refresh++;
//            if(Config.refresh>2)
//            {
//                Refresh();
//            }
//            else
//            {
//                Intent intent = new Intent(Update2.this, Update2.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("cemail1", myString2);
//                intent.putExtras(bundle);
//                startActivity(intent);
//            }
//
//
//
//        }
//        if(Config.city.equals("City") || Config.b.equals("Business Type"))
//        {
//            Config.refresh++;
//
//            if(Config.refresh>2)
//            {
//                Refresh();
//            }
//            else
//            {
//                Intent intent = new Intent(Update2.this, Update2.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("cemail1", myString2);
//                intent.putExtras(bundle);
//                startActivity(intent);
//            }
//        }
//===================================================================
    }
    //******************************************Json parsing ends here*************************
}
