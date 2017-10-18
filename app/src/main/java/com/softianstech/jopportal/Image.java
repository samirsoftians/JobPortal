package com.softianstech.jopportal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Lenovo on 6/12/2017.
 */

public class Image extends Fragment {

    ImageView imageView;
    TextView textView;
    RequestQueue requestQueue7;//
    Context context;
    String insertUrl7 =Links.USER_FORGET;

    //**********************************Pasting here from the JobProject Image*****************************
    public static final String UPLOAD_URL2=Links.USER_PHOTO; //="http://makable-spill.000webhostapp.com/upload.php";
    public static final String UPLOAD_KEY = "image";
    public static final String TAG2 = "MY MESSAGE";

    private int PICK_IMAGE_REQUEST = 1;

    private Bitmap bitmap;

    private Uri filePath2;
    //*********************************************END ING HERE*******************************************************

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // return super.onCreateView(inflater, container, savedInstanceState);
        View v=inflater.inflate(R.layout.nav_header_main,container,false);



        imageView= (ImageView) v.findViewById(R.id.image2);
        textView= (TextView) v.findViewById(R.id.welcome2);

        requestQueue7 = Volley.newRequestQueue(getContext());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder ad1 = new AlertDialog.Builder(getContext());
                ad1.setMessage("Click to upload picture");
                ad1.setCancelable(false);
                showfilechooser();
                textView.setText("Welcome "+Variables.Name);

                ad1.setPositiveButton("Click Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //finish();

                        Personal personal = new Personal();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, personal);
                fragmentTransaction.commit();
                    }
                });
                AlertDialog alert = ad1.create();
                alert.show();

            }
        });

       // submit.setOnClickListener(new View.OnClickListener() {
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
//                //=============================================================================================================
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

    public void showfilechooser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }


    //***************************on activity for resulet*************************
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

                filePath2 = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath2);
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }





    }

    //***************************************On activity for result ends here*************


    //***********************************************************************************************************
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
                Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();

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

                String result = rh.sendPostRequest(UPLOAD_URL2,data);

                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }

}
