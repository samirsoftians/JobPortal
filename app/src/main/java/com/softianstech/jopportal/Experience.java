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
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 6/12/2017.
 */

public class Experience extends Fragment {

   Spinner experience,noticePeriod;
    TextView subjects;
    Button expSubmit;
    private ArrayList<String> expRience;
    private ArrayList<String> noticeperiod;
    private ArrayList<String> locality;
    private ArrayList<String> subj;
    protected ArrayList<CharSequence> selectedColours = new ArrayList<CharSequence>();
    protected ArrayList<CharSequence> selectedSubjects = new ArrayList<CharSequence>();
   int  sub=0;
    RequestQueue requestQueue8;
    String message;
    int valid=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // return super.onCreateView(inflater, container, savedInstanceState);
        View v=inflater.inflate(R.layout.experience,container,false);


        requestQueue8 = com.android.volley.toolbox.Volley.newRequestQueue(getContext());


        experience= (Spinner) v.findViewById(R.id.experience);
        noticePeriod= (Spinner) v.findViewById(R.id.notice);
        subjects= (TextView) v.findViewById(R.id.subjects);
        expSubmit=(Button) v.findViewById(R.id.expbutton);

        expRience = new ArrayList<String>();
        noticeperiod= new ArrayList<String>();
        locality= new ArrayList<String>();
        subj= new ArrayList<String>();

        getexperience();
        notice();

//        experience.setSelection(expRience.indexOf(Variables.vtotexperience));
//        noticePeriod.setSelection(noticeperiod.indexOf(Variables.vnoticeperiod));
//        subjects.setText(Variables.subjects);



        subjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sub=1;
                // open2(v);
                subj.clear();
                showSelectColoursDialog();

            }
        });

       // requestQueue7 = Volley.newRequestQueue(getContext());
        expSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                valid=0;
                if (subjects.getText().toString()=="") {
                    //subjects.setError("please enter name");
                    valid++;
                }
                if (experience.getSelectedItem().toString().equals("Experience")) {
                    //Put an allert dialog

//                    if(valid>0)
                    // {
                   // getexperience();
                      Toast.makeText(getContext(), "please enter qualification", Toast.LENGTH_SHORT).show();

                    // }
                    //else
                    //{
                    //open2(v);

                    valid++;


                    //}
                }

                //***************************************************
                if (noticePeriod.getSelectedItem().toString().equals("Notice Period")) {
                    //Put an allert dialog

//                    if(valid>0)
                    // {
                   // notice();
                       Toast.makeText(getContext(), "please enter Notice Period", Toast.LENGTH_SHORT).show();

                    // }
                    //else
                    //{
                    // open2(v);

                    valid++;


                    //}
                }
                //***************************************************************


//                if (Qualification2 > 0 && CourseType2 > 0 && Year2 > 0) {
//                    message = "Please choose Qualification,Course,Passout Year";
//                    open2(v);
//                }
//                if (Qualification2 > 0 && CourseType2 > 0 && Year2 < 0) {
//                    message = "Please choose Qualification,Course";
//                    open2(v);
//                }
//                if (Qualification2 > 0 && CourseType2 < 0 && Year2 > 0) {
//                    message = "Please choose Qualification,Passout Year";
//                    open2(v);
//                }
//                    if (Qualification2 < 0 && CourseType2 > 0 && Year2 > 0) {
//                        message = "Please choose Course,Passout Year";
//                        open2(v);
//                    }
//                    if (Qualification2 < 0 && CourseType2 < 0 && Year2 > 0) {
//                        message = "Please choose Passout Year";
//                        open2(v);
//                    }
//                    if (Qualification2 < 0 && CourseType2 > 0 && Year2 < 0) {
//                        message = "Please choose Course";
//                        open2(v);
//                    }
//                    if (Qualification2 > 0 && CourseType2 < 0 && Year2 < 0) {
//                        message = "Please choose Qualification";
//                        open2(v);
//                    }

                if(valid>0) {

                    message=" Please fill in Details";
                  open3(v);
                }
                else
                {
                    Variables.vnoticeperiod=  noticePeriod.getSelectedItem().toString();
                    Variables.vtotexperience=experience.getSelectedItem().toString();
                    Variables.vsubjects=subjects.getText().toString();
                    //************************Volley Used Here********************************
                    StringRequest request = new StringRequest(Request.Method.POST, Links.USER_EXPERIENCE, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

//                                Variables.CheckSecondNavigation=1;

                           // Toast.makeText(getContext(),"Successfully Saved", Toast.LENGTH_SHORT).show();



                            Toast.makeText(getContext(),"Successfully Updated", Toast.LENGTH_SHORT).show();
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

                            parameters.put("user_subjects", subjects.getText().toString());
                            parameters.put("user_experience", experience.getSelectedItem().toString());
                            parameters.put("user_noticeperiod", noticePeriod.getSelectedItem().toString());

                            parameters.put("user_email", Variables.client_email);
                            //parameters.put("user_city", city.getText().toString());




                            return parameters;
                        }
                    };
                    requestQueue8.add(request);


                    //**************************Volley Ends Here***************************





                }
//                else {
//                    message=" Please fill in Details";
//                    open3(v);
//                }
            }

        });




        //*************************************************












        return v;


    }



    private void getexperience()//JSONArray j
    {
        expRience.add("Experience");

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
        expRience.add("0-6 Months");
        expRience.add("1-2 Years");
        expRience.add("2-5 Years");
        expRience.add("5-10 Years");
        expRience.add("Greater Then 10 Years");
        experience.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, expRience));
        experience.setSelection(expRience.indexOf(Variables.b));

    }
    //********************************
    private void notice()//JSONArray j
    {
        noticeperiod.add("Notice Period");

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
        noticeperiod.add("1 Months");
        noticeperiod.add("2 Months");
        noticeperiod.add("3 Months");
        noticeperiod.add("4 Months");
        noticeperiod.add("5 Months");
        noticePeriod.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, noticeperiod));
        noticePeriod.setSelection(expRience.indexOf(Variables.b));

    }

    //**************************************************************************************************************************
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    protected void showSelectColoursDialog()
    {

        if(sub==1)
        {
            subj.add("Accountancy");
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


            subj.add("Archaelogy");
            subj.add("Biology");
            subj.add("Business Studies");
            subj.add("C");
            subj.add("C#");
            subj.add("Chemistry");
            subj.add("Civics");
            subj.add("Computers");
            subj.add("Economics");
            subj.add("English");
            subj.add("Geography");
            subj.add("Geology");
            subj.add("Hindi");
            subj.add("History");
            subj.add("Java");
            subj.add("Maths");
            subj.add("Perl");
            subj.add("Philosophy");
            subj.add("Physics");
            subj.add("Political Science");
            subj.add("Principal of Management");
            subj.add("Psychology");
            subj.add("Sanskrit");
            subj.add("Sql");


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

            builder.setTitle("Select Subjects");

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

                    subjects.setText(stringBuilder.toString());
                    a++;
                }
                else
                {
                    stringBuilder.append("," + colour);
                    subjects.setText(stringBuilder.toString());
                }
        }
        else
        {
            // StringBuilder stringBuilder = new StringBuilder();

            for(CharSequence colour : selectedColours)

                if(a==0)
                {
                    stringBuilder.append(colour);

                    subjects.setText(stringBuilder.toString());
                    a++;
                }
                else
                {
                    stringBuilder.append("," + colour);
                    subjects.setText(stringBuilder.toString());

                }
        }


//        stringBuilder.append(prefix + colour);
//        prefix=",";
//        textPlace.setText(stringBuilder.toString());

    }
//**************************************************************************************************************
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            filePath = data.getData();
//
//        }
//    }
//
//    //******************************************************************
//
//    private void requestStoragePermission() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
//            return;
//
//        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
//            //If the user has denied the permission previously your code will come to this block
//            //Here you can explain why you need this permission
//            //Explain here why you need this permission
//        }
//        //And finally ask for the permission
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
//    }
//    //*************************
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        //Checking the request code of our request
//        if (requestCode == STORAGE_PERMISSION_CODE) {
//
//            //If permission is granted
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                //Displaying a toast
//                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
//            } else {
//                //Displaying another toast if permission is not granted
//                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
//            }
//        }
//    }
//    //*********************************************
//
//    public void uploadMultipart() {
//        //getting name for the pdf
//        String name = resumeText.getText().toString().trim();
//
//        //getting the actual path of the pdf
//        String path = FilePath.getPath(this, filePath);
//
//        if (path == null) {
//
//            Toast.makeText(this, "Please move your .pdf file to internal storage and retry", Toast.LENGTH_LONG).show();
//        } else {
//            //Uploading code
//            try {
//                String uploadId = UUID.randomUUID().toString();
//
//                //Creating a multi part request
//                new MultipartUploadRequest(this, uploadId, UPLOAD_URL)//it is simillar to volley to insert data to server
//                        .addFileToUpload(path, "pdf") //Adding file
//                        .addParameter("name", name) //Adding text parameter to the request
//                        .setNotificationConfig(new UploadNotificationConfig())
//                        .setMaxRetries(2)
//                        .startUpload(); //Starting the upload
//
//            } catch (Exception exc) {
//                Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }
//    }


public void open3(View v){
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
}
