package com.softianstech.jopportal;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;
import static com.softianstech.jopportal.Image.UPLOAD_KEY;

/**
 * Created by Lenovo on 6/9/2017.
 */

public class Personal extends Fragment {

    Bitmap defaultimage;
    public static SharedPreferences sharedpreferences;
    Button per_image,per_upload;
    public static TextView welcome2;
    TextView per_name,per_email,per_phone,per_current_location,per_gender,
            per_birth,per_total_experience,per_sub,per_qualification,per_notice,
            per_institute,per_course_tupe,per_pass_year, per_place_teach,pathresume,per_experience,per_education,per_location,per_personal,per_resume;
    RequestQueue requestQueue;

  public static  ImageView imageView2;

    int onactivity=0,sub=0;

    ProgressDialog dialog;
    private int PICK_IMAGE_REQUEST = 1;
    private static final int PICK_FILE_REQUEST = 1;
    private String selectedFilePath;
    private String SERVER_URL = Links.USER_RESUME;

    private Bitmap bitmap;

    private Uri filePath2;

    Context context;

   // *****SHARRED PREFERENCES TO STORE DATA****
    public static final String mypreference = "mypref";
    public static final String Password = "nameKey";
    public static final String Email = "emailKey";
//****************************END HERE*************************

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);
        final View v = inflater.inflate(R.layout.personal, container, false);



        if(Variables.crash==1)
        {
            Variables.crash=0;
            Toast.makeText(getContext(), "Sorry could not upload File please try again", Toast.LENGTH_LONG).show();
        }

        defaultimage= BitmapFactory.decodeResource(getContext().getResources(),R.drawable.prifileimage);


        sharedpreferences = getContext().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        requestQueue = com.android.volley.toolbox.Volley.newRequestQueue(getContext());
        per_name= (TextView) v.findViewById(R.id.per_name);
        per_email= (TextView) v.findViewById(R.id.per_email);
        per_phone= (TextView) v.findViewById(R.id.per_phone);
        per_current_location= (TextView) v.findViewById(R.id.per_current_location);
        per_gender= (TextView) v.findViewById(R.id.per_gender);
        per_birth= (TextView) v.findViewById(R.id.per_birth);

        per_total_experience= (TextView) v.findViewById(R.id.per_total_experience);
        per_sub= (TextView) v.findViewById(R.id.per_subjects);
        per_notice= (TextView) v.findViewById(R.id.per_notice);


        per_institute= (TextView) v.findViewById(R.id.per_institute);
        per_course_tupe= (TextView) v.findViewById(R.id.per_couse_type);
        per_pass_year= (TextView) v.findViewById(R.id.per_pass_year);
         per_qualification= (TextView) v.findViewById(R.id.per_qualification);


        per_place_teach= (TextView) v.findViewById(R.id.per_places_teach);

        pathresume= (TextView) v.findViewById(R.id.resumepath);



        per_experience= (TextView) v.findViewById(R.id.per_experience);
        per_education= (TextView) v.findViewById(R.id.per_education);
        per_location= (TextView) v.findViewById(R.id.per_location_teach);
        per_personal= (TextView) v.findViewById(R.id.per_personal);

//        per_image= (Button) v.findViewById(R.id.per_picture);
        per_resume= (TextView) v.findViewById(R.id.per_resume);

        per_upload= (Button) v.findViewById(R.id.per_upload);

        imageView2=(ImageView) v.findViewById(R.id.image2);

        welcome2= (TextView) v.findViewById(R.id.welcome2);

//      //  sharedpreferences2 = getSharedPreferences(MainActivity.mypreference, Context.MODE_PRIVATE);
//        if(Variables.client_email!=null || Variables.client_email!="")
//        {
////            Variables.a++;
////            Variables.islogin=2;
////            Variables.client_email = sharedpreferences.getString(Email, "");
//
//            getImage();
//
//        }

        if(sharedpreferences.contains(Email))
        {
            getImage();

        }



        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onactivity=1;
                showFileChooser();
            }
        });

        dialog=new ProgressDialog(getContext());





        per_name.setText(Variables.Name);
        per_email.setText(Variables.vemail);
        per_phone.setText(Variables.vphone);
        per_current_location.setText(Variables.vcurrentlocation);
        per_gender.setText(Variables.vgender);
        per_birth.setText(Variables.vdateofbirth);
        per_total_experience.setText(Variables.vtotexperience);
        per_sub.setText(Variables.vsubjects);
        per_qualification.setText(Variables.vqualification);
        per_institute.setText(Variables.vinstitute);
        per_course_tupe.setText(Variables.vcourse);
        per_notice.setText(Variables.vnoticeperiod);

        per_pass_year.setText(Variables.vyearpass);
        per_place_teach.setText(Variables.vplaces);

        welcome2.setText(Variables.client_email);

        //getImage();


//****************************************Volley to get data fill in the Edittext**********************

        StringRequest stringRequest841 = new StringRequest(Links.User_GETDATA+Variables.client_email, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                //myPd_ring.dismiss();

              // Toast.makeText(getContext(),response,Toast.LENGTH_LONG ).show();


                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {

                        if (error instanceof NetworkError)
                        {
                            neterror(v);
                            //Toast.makeText(getContext(),"Cannot connect to Internet...Please check your connection!",Toast.LENGTH_LONG ).show();
                        }
                        else if (error instanceof ServerError)
                        {
                            Toast.makeText(getContext(),"The server could not be found. Please try again after some time!!",Toast.LENGTH_LONG ).show();
                        }
                        else if (error instanceof AuthFailureError)
                        {
                            neterror(v);
                           // Toast.makeText(getContext(),"Cannot connect to Internet...Please check your connection !",Toast.LENGTH_LONG ).show();
                        }
                        else if (error instanceof ParseError)
                        {
                            Toast.makeText(getContext(),"Parsing error! Please try again after some time !!",Toast.LENGTH_LONG ).show();

                        }
                        else if (error instanceof NoConnectionError)
                        {
                            neterror(v);
                            //Toast.makeText(getContext(),"Cannot connect to Internet...Please check your connection !",Toast.LENGTH_LONG ).show();
                        }
                        else if (error instanceof TimeoutError)
                        {
                            neterror(v);
                           // Toast.makeText(getContext(),"Cannot connect to Internet...Please check your connection !",Toast.LENGTH_LONG ).show();
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
        requestQueue841.add(stringRequest841);

        //************************************End of volley here*************************************************

//        per_image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });


        per_resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onactivity=2;


                showFileChooser();

            }
        });



        per_upload.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (selectedFilePath != null) {
                    dialog = ProgressDialog.show(getContext(), "", "Uploading File...", true);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //creating new thread to handle Http Operations
                            uploadFile(selectedFilePath);
                        }
                    }).start();
                } else {
                    Toast.makeText(getContext(), "Please choose a File First", Toast.LENGTH_SHORT).show();
                }

            }
        });




        per_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonalInfo personalInfo = new PersonalInfo();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, personalInfo);
                fragmentTransaction.commit();
            }
        });

        per_experience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Experience experience = new Experience();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, experience);
                fragmentTransaction.commit();
            }
        });

        per_education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Education education = new Education();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, education);
                fragmentTransaction.commit();
            }
        });

        per_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaceTeach placeTeach = new PlaceTeach();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, placeTeach);
                fragmentTransaction.commit();
            }
        });
        return v;
    }
    public void showFileChooser()
    {


        if(onactivity==1)
        {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        }
        else {


            Intent intent = new Intent();
            //sets the select file to all types of files
            intent.setType("*/*");
            //allows to select data and return it
            intent.setAction(Intent.ACTION_GET_CONTENT);
            //starts new activity to select file and return data
            startActivityForResult(Intent.createChooser(intent, "Choose File to Upload.."), PICK_FILE_REQUEST);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(onactivity==1)
        {
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

                filePath2 = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), filePath2);//did put getcontext
                    imageView2.setImageBitmap(bitmap);
                    MainActivity.imageView1.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            uploadImage();
        }

        else {


            if (resultCode == RESULT_OK) {
                if (requestCode == PICK_FILE_REQUEST) {
                    if (data == null) {
                        //no data present
                        return;
                    }


                    Uri selectedFileUri = data.getData();
                    selectedFilePath = FilePath.getPath(getActivity(), selectedFileUri);
                    //Log.i(TAG,"Selected File Path:" + selectedFilePath);

                    if (selectedFilePath != null && !selectedFilePath.equals("")) {
                        pathresume.setText(selectedFilePath);
                    } else {
                        Toast.makeText(getContext(), "Cannot upload file to server", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        }


    }

    //*******************Code to upload the file to the mysql server************************
    //android upload file to server
    public int uploadFile2(final String selectedFilePath){
        // Toast.makeText(getApplicationContext(), Variables.client_email, Toast.LENGTH_SHORT).show();
        //Toast.makeText(Details.this, Variables.client_email, Toast.LENGTH_SHORT).show();
        int serverResponseCode = 0;

        HttpURLConnection connection;
        DataOutputStream dataOutputStream;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";


        int bytesRead,bytesAvailable,bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File selectedFile = new File(selectedFilePath);


        String[] parts = selectedFilePath.split("/");
        final String fileName = parts[parts.length-1];

        if (!selectedFile.isFile()){
            dialog.dismiss();

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    pathresume.setText("Source File Doesn't Exist: " + selectedFilePath);
                }
            });
            return 0;
        }else{
            try{
                FileInputStream fileInputStream = new FileInputStream(selectedFile);
                URL url = new URL(SERVER_URL);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);//Allow Inputs
                connection.setDoOutput(true);//Allow Outputs
                connection.setUseCaches(false);//Don't use a cached Copy
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("ENCTYPE", "multipart/form-data");
                connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                connection.setRequestProperty("uploaded_file",selectedFilePath);
                //connection.setRequestProperty("user_email",Variables.client_email);

                //creating new dataoutputstream
                dataOutputStream = new DataOutputStream(connection.getOutputStream());

                //writing bytes to data outputstream
                dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                        + selectedFilePath + "\"" + lineEnd);

                dataOutputStream.writeBytes(lineEnd);

                //returns no. of bytes present in fileInputStream
                bytesAvailable = fileInputStream.available();
                //selecting the buffer size as minimum of available bytes or 1 MB
                bufferSize = Math.min(bytesAvailable,maxBufferSize);
                //setting the buffer as byte array of size of bufferSize
                buffer = new byte[bufferSize];

                //reads bytes from FileInputStream(from 0th index of buffer to buffersize)
                bytesRead = fileInputStream.read(buffer,0,bufferSize);

                //loop repeats till bytesRead = -1, i.e., no bytes are left to read
                while (bytesRead > 0){
                    //write the bytes read from inputstream
                    dataOutputStream.write(buffer,0,bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable,maxBufferSize);
                    bytesRead = fileInputStream.read(buffer,0,bufferSize);
                }

                dataOutputStream.writeBytes(lineEnd);
                dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                serverResponseCode = connection.getResponseCode();
                String serverResponseMessage = connection.getResponseMessage();

                //Log.i(TAG, "Server Response is: " + serverResponseMessage + ": " + serverResponseCode);

                //response code of 200 indicates the server status OK
                if(serverResponseCode == 200){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // tvFileName.setText("File Upload completed.\n\n You can see the uploaded file here: \n\n" + "http://coderefer.com/extras/uploads/"+ fileName);
                        }
                    });
                }

                //closing the input and output streams
                fileInputStream.close();
                dataOutputStream.flush();
                dataOutputStream.close();



            } catch (FileNotFoundException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(),"File Not Found",Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "URL error!", Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Cannot Read/Write File!", Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
            return serverResponseCode;
        }

    }


    //***********************************The End****************************************************
    private void showJSON(String response)
    {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Variables.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);

//            userName = collegeData.getString(Variables.user_name);
//            bname5 = collegeData.getString(Variables.user_phone);
//            address5 = collegeData.getString(Variables.user_email);
//            contact5 = collegeData.getString(Variables.user_address);
//            email5 = collegeData.getString(Variables.user_city);// we dont require email but we get it from the server
//            password5 = collegeData.getString(Variables.user_qualification);
//            business5 = collegeData.getString(Variables.user_experience);
//            city5 = collegeData.getString(Variables.user_gender);
//            bname5 = collegeData.getString(Variables.user_places);
//            address5 = collegeData.getString(Variables.user_birth);
//            contact5 = collegeData.getString(Variables.user_institute);
//            email5 = collegeData.getString(Variables.user_coursetype);// we dont require email but we get it from the server
//            password5 = collegeData.getString(Variables.user_passyear);
//            business5 = collegeData.getString(Variables.user_noticeperiod);
//            city5 = collegeData.getString(Variables.user_subjects);


            per_name.setText(collegeData.getString(Variables.user_name));
            per_phone.setText(collegeData.getString(Variables.user_phone));
            per_email.setText(collegeData.getString(Variables.user_email));
            //.setText(collegeData.getString(Variables.user_address));
            per_current_location.setText(collegeData.getString(Variables.user_city));// we dont require email but we get it from the server
            per_qualification.setText(collegeData.getString(Variables.user_qualification));
            per_total_experience.setText(collegeData.getString(Variables.user_experience));
            per_gender.setText(collegeData.getString(Variables.user_gender));
            per_place_teach.setText(collegeData.getString(Variables.user_places));

            per_birth.setText(collegeData.getString(Variables.user_birth));
            per_institute.setText(collegeData.getString(Variables.user_institute));
            per_course_tupe.setText(collegeData.getString(Variables.user_coursetype)); // we dont require email but we get it from the server
            per_pass_year.setText(collegeData.getString(Variables.user_passyear));
            per_notice.setText(collegeData.getString(Variables.user_noticeperiod));
            per_sub.setText(collegeData.getString(Variables.user_subjects));




            Variables.Name=per_name.getText().toString();
            Variables.vphone=per_phone.getText().toString();
            Variables.vemail=per_email.getText().toString();
            Variables.vcurrentlocation=per_current_location.getText().toString();
            Variables.vdateofbirth=per_birth.getText().toString();
            Variables.vgender=per_gender.getText().toString();
            Variables.vtotexperience=per_total_experience.getText().toString();
                    Variables.vsubjects=per_sub.getText().toString();
            Variables.vnoticeperiod=per_notice.getText().toString();
            Variables.vqualification=per_qualification.getText().toString();
            Variables.vinstitute=per_institute.getText().toString();
            Variables.vcourse=per_course_tupe.getText().toString();
            Variables.vyearpass=per_pass_year.getText().toString();
            Variables.vplaces=per_place_teach.getText().toString();

        } catch (JSONException e) {
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
//*********************************************************************************Upload to server**********************
public int uploadFile(final String selectedFilePath){
    // Toast.makeText(getApplicationContext(), Variables.client_email, Toast.LENGTH_SHORT).show();
    //Toast.makeText(Details.this, Variables.client_email, Toast.LENGTH_SHORT).show();
    int serverResponseCode = 0;

    HttpURLConnection connection;
    DataOutputStream dataOutputStream;
    String lineEnd = "\r\n";
    String twoHyphens = "--";
    String boundary = "*****";


    int bytesRead,bytesAvailable,bufferSize;
    byte[] buffer;
    int maxBufferSize = 1 * 1024 * 1024;
    File selectedFile = new File(selectedFilePath);


    String[] parts = selectedFilePath.split("/");
    final String fileName = parts[parts.length-1];

    if (!selectedFile.isFile()){
        dialog.dismiss();

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pathresume.setText("Source File Doesn't Exist: " + selectedFilePath);
            }
        });
        return 0;
    }else{
        try{
            FileInputStream fileInputStream = new FileInputStream(selectedFile);
            URL url = new URL(SERVER_URL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);//Allow Inputs
            connection.setDoOutput(true);//Allow Outputs
            connection.setUseCaches(false);//Don't use a cached Copy
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("ENCTYPE", "multipart/form-data");
            connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            connection.setRequestProperty("uploaded_file",selectedFilePath);
            connection.setRequestProperty("user_email",Variables.client_email);
           // Toast.makeText(getContext(), Variables.client_email, Toast.LENGTH_SHORT).show();
            //creating new dataoutputstream
            dataOutputStream = new DataOutputStream(connection.getOutputStream());

            //writing bytes to data outputstream
            dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
            dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                    + selectedFilePath + "\"" + lineEnd);

            dataOutputStream.writeBytes(lineEnd);

            //returns no. of bytes present in fileInputStream
            bytesAvailable = fileInputStream.available();
            //selecting the buffer size as minimum of available bytes or 1 MB
            bufferSize = Math.min(bytesAvailable,maxBufferSize);
            //setting the buffer as byte array of size of bufferSize
            buffer = new byte[bufferSize];

            //reads bytes from FileInputStream(from 0th index of buffer to buffersize)
            bytesRead = fileInputStream.read(buffer,0,bufferSize);

            //loop repeats till bytesRead = -1, i.e., no bytes are left to read
            while (bytesRead > 0){
                //write the bytes read from inputstream
                dataOutputStream.write(buffer,0,bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable,maxBufferSize);
                bytesRead = fileInputStream.read(buffer,0,bufferSize);
            }

            dataOutputStream.writeBytes(lineEnd);
            dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            serverResponseCode = connection.getResponseCode();
            String serverResponseMessage = connection.getResponseMessage();

            Log.i(TAG, "Server Response is: " + serverResponseMessage + ": " + serverResponseCode);

            //response code of 200 indicates the server status OK
            if(serverResponseCode == 200){
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "File uploaded successfully", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            //closing the input and output streams
            fileInputStream.close();
            dataOutputStream.flush();
            dataOutputStream.close();



        } catch (FileNotFoundException e) {
            e.printStackTrace();
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    //******************************Allert added to avoid crash*********************
//                    AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(getContext());
//                    alertDialogBuilder2.setMessage("Please Check Your Internet Connection or the File");
//
//                    alertDialogBuilder2.setPositiveButton("Refresh",
//                            new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface arg0, int arg1) {
//                                    Personal personalInfo = new Personal();
//                                    FragmentManager fragmentManager = getFragmentManager();
//                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                                    fragmentTransaction.replace(R.id.frame, personalInfo);
//                                    fragmentTransaction.commit();
//                                }
//                            });
//
//                    AlertDialog alertDialog2 = alertDialogBuilder2.create();
//                    alertDialog2.show();
                    //***********************************Allert ended*****************************
                    //Toast.makeText(getContext(),"File Not Found",Toast.LENGTH_SHORT).show();

                    Variables.crash=1;
                    Personal personalInfo = new Personal();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame, personalInfo);
                    fragmentTransaction.commit();
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();

            //******************************Allert added to avoid crash*********************
//            AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(getContext());
//            alertDialogBuilder2.setMessage("Please Check Your Internet Connection or the File");
//
//            alertDialogBuilder2.setPositiveButton("Refresh",
//                    new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface arg0, int arg1) {
//                            Personal personalInfo = new Personal();
//                            FragmentManager fragmentManager = getFragmentManager();
//                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                            fragmentTransaction.replace(R.id.frame, personalInfo);
//                            fragmentTransaction.commit();
//                        }
//                    });
//
//            AlertDialog alertDialog2 = alertDialogBuilder2.create();
//            alertDialog2.show();
            //***********************************Allert ended*****************************
           // Toast.makeText(getContext(), "URL error!", Toast.LENGTH_SHORT).show();

            Variables.crash=1;
            Personal personalInfo = new Personal();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame, personalInfo);
            fragmentTransaction.commit();

        } catch (IOException e) {
            e.printStackTrace();

//            //******************************Allert added to avoid crash*********************
//            AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(getContext());
//            alertDialogBuilder2.setMessage("Please Check Your Internet Connection or the File");
//
//            alertDialogBuilder2.setPositiveButton("Refresh",
//                    new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface arg0, int arg1) {
//                            Personal personalInfo = new Personal();
//                            FragmentManager fragmentManager = getFragmentManager();
//                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                            fragmentTransaction.replace(R.id.frame, personalInfo);
//                            fragmentTransaction.commit();
//                        }
//                    });
//
//            AlertDialog alertDialog2 = alertDialogBuilder2.create();
//            alertDialog2.show();
            //***********************************Allert ended*****************************
          //  Toast.makeText(getContext(), "Cannot Read/Write File!", Toast.LENGTH_SHORT).show();
                            Variables.crash=1;
                             Personal personalInfo = new Personal();
                            FragmentManager fragmentManager = getFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.frame, personalInfo);
                            fragmentTransaction.commit();
        }
        dialog.dismiss();
        return serverResponseCode;
    }

}

    //*******************************************************************************************************************
//**********************************Code to upload image to the server**********************
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    //**********************************************************************************************

    public void uploadImage(){
        class UploadImage extends AsyncTask<Bitmap,Void,String> {

            ProgressDialog loading;
            com.softianstech.jopportal.RequestHandler rh = new com.softianstech.jopportal.RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getContext(), "Uploading...", null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();

                if(s.isEmpty())
                {
                    Toast.makeText(getContext(),"Could not upload picture",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getContext(),"Image uploaded",Toast.LENGTH_LONG).show();

                }

//                Intent intent=new Intent(Details.this,Display.class);
//                startActivity(intent);
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);

                HashMap<String,String> data = new HashMap<>();
                data.put(UPLOAD_KEY, uploadImage);
                data.put("user_email", Variables.client_email);

                String result = rh.sendPostRequest(Links.USER_PHOTO,data);

                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }
    //*****************************************End here*******************************************

    //**********************************************To get the image from the server who everlogin************


    //***************************************************************************************************************************
    private void getImage() {
        //  String id = editTextId.getText().toString().trim();
        class GetImage extends AsyncTask<String, Void, Bitmap> {
            //ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();


                //loading = ProgressDialog.show(MainActivity.this, "Retriving image...", null, true, true);
            }

            @Override
            protected void onPostExecute(Bitmap b) {
                super.onPostExecute(b);
               // loading.dismiss();

                if(b!= null)
                {
                    imageView2.setImageBitmap(b);
                    MainActivity.imageView1.setImageBitmap(b);
                }
                else
                {
                    imageView2.setImageBitmap(defaultimage);
                    MainActivity.imageView1.setImageBitmap(defaultimage);

                }


            }

            @Override
            protected Bitmap doInBackground(String... params) {
                String id = params[0];
                String add = Links.USER_IMAGE+Variables.client_email;
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

    //*******************************Allert Dialog Box**************************
    public  void neterror(View view){
        AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(getContext());
        alertDialogBuilder2.setMessage("Please Check Your Internet Connection");

        alertDialogBuilder2.setPositiveButton("Refresh",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Personal personalInfo = new Personal();
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





