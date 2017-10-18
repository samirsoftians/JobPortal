package com.softianstech.jopportal;

import android.content.DialogInterface;
import android.graphics.Color;
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

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

//import android.app.Fragment;

/**
 * Created by Lenovo on 6/1/2017.
 */

public class LoginRegistration extends Fragment {
    Button reglogin;
    Button regregister;
    Animation shake,bounce;
    EditText name,phone,address,email,password,confirmpassword;
    Spinner city2;
   // EditText city;
    Button submit;
    private JSONArray result;
    private ArrayList<String> students;
    Variables variables;
    RequestQueue requestQueue;
    int valid=0;
    private ArrayList<String> subj;
    protected ArrayList<CharSequence> selectedSubjects = new ArrayList<CharSequence>();
    protected ArrayList<CharSequence> selectedColours = new ArrayList<CharSequence>();
    int sub;
    int spin=0;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        bounce = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
        // return super.onCreateView(inflater, container, savedInstanceState);
        View v=inflater.inflate(R.layout.loginregistration,container,false);




        name= (EditText) v.findViewById(R.id.Reg_Name);
        confirmpassword= (EditText) v.findViewById(R.id.Reg_ConfirmPassword);
        phone= (EditText) v.findViewById(R.id.Reg_Phone);
        address= (EditText) v.findViewById(R.id.Reg_Address);
       city2= (Spinner) v.findViewById(R.id.Reg2_City);

   //     city= (EditText) v.findViewById(R.id.Reg_City);

        email= (EditText) v.findViewById(R.id.Reg_Email);

        password= (EditText) v.findViewById(R.id.Reg_Password);
        submit= (Button) v.findViewById(R.id.Reg_Submit);
        students=new ArrayList<String>();


        variables=new Variables();

        subj=new ArrayList<String>();


        requestQueue = com.android.volley.toolbox.Volley.newRequestQueue(getContext());

       // getStudents();



       // city2.setOnClickListener(new View.OnClickListener() {
          //  @Override
          //  public void onClick(View v) {

              //  spin++;
               // place();
                getStudents();
          //  }
       // });
        reglogin= (Button) v.findViewById(R.id.reglogin);
        regregister= (Button) v.findViewById(R.id.regregister);
// regregister.setBackgroundColor(Color.YELLOW);
        regregister.setBackgroundColor(Color.parseColor("#fd8701"));
        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                submit.startAnimation(bounce);
                valid=0;


                if (!checkEmail(email.getText().toString())) {
                    email.setError("Invalid Email");

                    // Toast.makeText(SignUpActivity.this, "Paswword lenght should be greater than 6 char", Toast.LENGTH_SHORT).show();
                    valid++;

                }


                if (!isValidPassword(password.getText().toString())) {
                    password.setError("Password length should be greater than 4 character");

                    // Toast.makeText(SignUpActivity.this, "Paswword lenght should be greater than 6 char", Toast.LENGTH_SHORT).show();
                    valid++;

                }

                if (phone.getText().toString().length() < 10 || phone.getText().toString().length() >= 12) {

                    phone.setError("Please enter valid phone number");
                    // Toast.makeText(SignUpActivity.this,"Please enter valid phone number",Toast.LENGTH_SHORT).show();
                    valid++;
                }

                if(!confirmpassword.getText().toString().equals(password.getText().toString()))
                {

                    confirmpassword.setError("Password did not matched");
                    valid++;
                }


                if(name.getText().toString().trim().equals(""))
                {
                    name.setError("please enter name");
                    valid++;
                }
                if(phone.getText().toString().trim().equals(""))
                {
                    phone.setError("please enter name");
                    valid++;
                }
                if(address.getText().toString().trim().equals(""))
                {
                    address.setError("please enter name");
                    valid++;
                }

//                if(city.getText().toString().equals("City"))
//                {
//                    //Put an allert dialog box
//                    Toast.makeText(getContext(), "please enter your City", Toast.LENGTH_SHORT).show();
//                    valid++;
//                }
                if(email.getText().toString().trim().equals(""))
                {
                    email.setError("please enter name");
                    valid++;
                }
                if(password.getText().toString().trim().equals(""))
                {
                    password.setError("please enter name");
                    valid++;
                }
                if(city2.getSelectedItem().toString().equals("City"))
                {
                    //Put an allert dialog

                    if(valid>0)
                    {
                        getStudents();
                        Toast.makeText(getContext(), "please enter your City", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        open(v);

                        valid++;
                    }



                }


                if(valid>0)
                {
                    //Toast.makeText(getContext(), "Please fill details", Toast.LENGTH_SHORT).show();
                }
                else {
                    // Toast.makeText(Registration.this, "hi samir", Toast.LENGTH_SHORT).show();
                    Variables.Name="";
                    Variables.vphone="";
                    Variables.vemail=email.getText().toString();
                    Variables.vcurrentlocation="";
                    Variables.vdateofbirth="";
                    Variables.vgender="";
                    Variables.vtotexperience="";
                    Variables.vsubjects="";
                    Variables.vnoticeperiod="";
                    Variables.vqualification="";
                    Variables.vinstitute="";
                    Variables.vcourse="";
                    Variables.vyearpass="";
                    Variables.vplaces="";
                    Variables.client_email=email.getText().toString();

                  //  Toast.makeText(getContext(), sharedpreferences.getString(FirebaseInstanceIDService.userToken, ""), Toast.LENGTH_SHORT).show();
                  //  String tempstr = String.valueOf(sharedpreferences.getString(FirebaseInstanceIDService.userToken,""));
                  //  Log.d("Token***",tempstr);

                    StringRequest request = new StringRequest(Request.Method.POST, Links.DATA_INSERT, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {





                            Variables.CheckSecondNavigation=1;


                           // Toast.makeText(getContext(),  String.valueOf(sharedpreferences.getString(FirebaseInstanceIDService.userToken, "")), Toast.LENGTH_SHORT).show();
                           // Toast.makeText(getContext(), "Registered successfully", Toast.LENGTH_SHORT).show();

                            Education education= new Education();
                            FragmentManager fragmentManager = getFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.frame, education);
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
                            parameters.put("user_name", name.getText().toString());
                            parameters.put("user_phone", phone.getText().toString());
                            parameters.put("user_address", address.getText().toString());
                            parameters.put("user_email", email.getText().toString());
                            parameters.put("user_password", password.getText().toString());
                            parameters.put("user_city", city2.getSelectedItem().toString());
                           // parameters.put("user_token","fRMD-UZqu5E:APA91bEyeVuaoiBo6wb_y5Pce9dOIFNvB4e_kmIDghFd6sqWY5d-7_PRF-rtS51mmj4YIolW7qRfLJvvHUuL24RW-Wv3Fw5Q8LPPnpTIoFLz9IUd3QY5vzMzy60ute_gXnv1UP6sHmAT"/*String.valueOf(sharedpreferences.getString(FirebaseInstanceIDService.userToken, ""))*/);
                            //parameters.put("user_city", city.getText().toString());

                            Variables.Name=name.getText().toString();
                            Variables.vemail=email.getText().toString();
                            Variables.vphone=phone.getText().toString();
                            Variables.vcurrentlocation=city2.getSelectedItem().toString();


                            return parameters;
                        }
                    };
                    requestQueue.add(request);
                }
            }
        });

        //***************************Set on click listener ends here****************



















        reglogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit.startAnimation(bounce);

                Login fragment2 = new Login();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment2);
                fragmentTransaction.commit();
            }
        });



        regregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit.startAnimation(bounce);

                LoginRegistration fragment3 = new LoginRegistration();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment3);
                fragmentTransaction.commit();
            }
        });






        return v;


    }

    public void getStudents()//here it was JSONArray j
    {
        students.add("City");

        students.add("Mumbai");
        students.add("Pune");
        students.add("Hydrabad");
        students.add("Delhi");
        students.add("Kolkatta");
        students.add("Chennai");
        students.add("Banglore");

        city2.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, students));
        city2.setSelection(students.indexOf(Variables.location));

    }

/******************************************************************************************************
 *
 */
//*********************************************************************************************************************
public void place()
{

    subj.add("Physics");

    subj.add("Maths");
    subj.add("English");
    subj.add("Economics");
    subj.add("Chemistry");
    subj.add("History");
    subj.add("Hindi");


    //*****************************************************
    boolean[] checkedSubjects = new boolean[subj.size()];

    int count = subj.size();

    for (int i = 0; i < count; i++)

        checkedSubjects[i] = selectedSubjects.contains(subj.get(i));

    DialogInterface.OnMultiChoiceClickListener coloursDialogListener2 = new DialogInterface.OnMultiChoiceClickListener()
    {

        @Override

        public void onClick(DialogInterface dialog, int which, boolean isChecked)
        {


            if (isChecked)

                selectedSubjects.add(subj.get(which));

            else

                selectedSubjects.remove(subj.get(which));

            //onChangeSelectedColours();

        }

    };

    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

    builder.setTitle("Select Subjects");

    builder.setPositiveButton("Submit",
            new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int id)
                {
                    //spinner.setSelection(students.indexOf("Business Type"));
                    ///  colours.substring(0, colours.length() - 1) ;

                    dialog.cancel();
                }
            });
             String[] subje = subj.toArray(new String[subj.size()]);

             builder.setMultiChoiceItems(subje, checkedSubjects, coloursDialogListener2);
                AlertDialog dialog = builder.create();

                dialog.show();
    }




//    protected void onChangeSelectedColours() {
//        int a = 0;
//        StringBuilder stringBuilder = new StringBuilder();
//        if (sub == 1) {
//            //StringBuilder stringBuilder2 = new StringBuilder();
//            for (CharSequence colour : selectedSubjects)
//
//                if (a == 0) {
//                    stringBuilder.append(colour);
//
//                    city.setText(stringBuilder.toString());
//                    a++;
//                } else {
//                    stringBuilder.append("," + colour);
//                    city.setText(stringBuilder.toString());
//                }
//        } else {
//            // StringBuilder stringBuilder = new StringBuilder();
//
//            for (CharSequence colour : selectedColours)
//
//                if (a == 0) {
//                    stringBuilder.append(colour);
//
//                    city.setText(stringBuilder.toString());
//                    a++;
//                } else {
//                    stringBuilder.append("," + colour);
//                    city.setText(stringBuilder.toString());
//                }
//        }
//
//
////        stringBuilder.append(prefix + colour);
////        prefix=",";
//        city.setText(stringBuilder.toString());
//
//    }

    public void open(View v){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage("Please Select City");

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

    private boolean isValidPassword(String pass)
    {
        if (pass != null && pass.length() > 4) {
            return true;
        }
        return false;

    }

    private boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }
}
