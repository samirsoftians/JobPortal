package com.softianstech.jopportal;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 2/28/2017.
 */

public class CustomList extends ArrayAdapter<String>  {
    Animation shake,bounce;
    String mm;
    private String[] ids;
    private String[] locations;
    private String[] qualifications;
    private String[] experiences;
    private String[] designations;
    private String[] subjects;
    private String[] image;
private String[] useremail;
    private Activity context;

    ImageView butapply;

    RequestQueue requestQueue;

    public FragmentActivity myContext;

    public Fragment fragment;

    private boolean[] itemToggled;

    Bitmap bitmap1;
    Bitmap bitmap2;

    String checkEmail;








    public CustomList(Activity context, String[] ids, String[] locations, String[] qualifications, String[] experiences,  String[] subjects, String[] useremail) {
        super(context, R.layout.displyasshine, ids);


        this.context = context;
        this.ids = ids;
        this.locations = locations;
        this.qualifications = qualifications;
        this.experiences = experiences;
        //this.designations = designations;
        this.subjects = subjects;
        this.useremail = useremail;
       // this.image = designations;


    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.displyasshine, null, true);



         bitmap1= BitmapFactory.decodeResource(context.getResources(),R.drawable.applyedjob);
        bitmap2= BitmapFactory.decodeResource(context.getResources(),R.drawable.applyjob);

        bounce = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);




        //it was list_view_layout

        TextView textId = (TextView) listViewItem.findViewById(R.id.listview_id);
        TextView textLocation= (TextView) listViewItem.findViewById(R.id.listview_location);
     //   TextView textQualification = (TextView) listViewItem.findViewById(R.id.listview_qualification);
        TextView textExperience = (TextView) listViewItem.findViewById(R.id.listview_experience);
      //  TextView textDesignation = (TextView) listViewItem.findViewById(R.id.listview_designation);
        TextView textSubjects = (TextView) listViewItem.findViewById(R.id.listview_subjects);
        butapply = (ImageView) listViewItem.findViewById(R.id.listview_apply);
//        butapply.setImageResource(itemToggled[position] ? R.drawable.applyedjob : R.drawable.applyjob);

        requestQueue = com.android.volley.toolbox.Volley.newRequestQueue(context);













        butapply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                bounce = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);







                mm=ids[position];
                checkEmail=useremail[position];


                if(Variables.islogin==1) {

                    Variables.check=1;
                    FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


                    Login fragment = new Login();
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.commit();


                }


                else if(checkEmail.equals(Variables.client_email))
                {
                    butapply.setImageBitmap(bitmap1);
                    Toast.makeText(getContext(), "Job Already Applyed", Toast.LENGTH_SHORT).show();
                }
                else {



                    //********************************Volley Starts here*****************
                    StringRequest request = new StringRequest(Request.Method.POST, Links.ApplyJob, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
//                        Bitmap bitmap1= BitmapFactory.decodeResource(context.getResources(),R.drawable.applyedjob);
//                        Bitmap bitmap2= BitmapFactory.decodeResource(context.getResources(),R.drawable.applyjob);
                            Toast.makeText(context, "Successfully Applied", Toast.LENGTH_SHORT).show();



                            FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            AllJobs fragment = new AllJobs();
                            fragmentTransaction.replace(R.id.frame, fragment);
                            fragmentTransaction.commit();



                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
//                                myPd_ring.dismiss();
                            if (error instanceof NetworkError) {
                                Toast.makeText(context, "Cannot connect to Internet...Please check your connection!", Toast.LENGTH_LONG).show();
                            } else if (error instanceof ServerError) {
                                Toast.makeText(context, "The server could not be found. Please try again after some time!!", Toast.LENGTH_LONG).show();
                            } else if (error instanceof AuthFailureError) {
                                Toast.makeText(context, "Cannot connect to Internet...Please check your connection !", Toast.LENGTH_LONG).show();
                            } else if (error instanceof ParseError) {
                                Toast.makeText(context, "Parsing error! Please try again after some time !!", Toast.LENGTH_LONG).show();

                            } else if (error instanceof NoConnectionError) {
                                Toast.makeText(context, "Cannot connect to Internet...Please check your connection !", Toast.LENGTH_LONG).show();
                            } else if (error instanceof TimeoutError) {
                                Toast.makeText(context, "Cannot connect to Internet...Please check your connection !", Toast.LENGTH_LONG).show();
                            }
                            // counter++;
                        }

                        {

                        }
                    }) {

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> parameters = new HashMap<String, String>();
                            parameters.put("job_id", mm);
                            parameters.put("user_email", Variables.client_email);//Variables.client_email


                            return parameters;
                        }
                    };
                    requestQueue.add(request);
                }

                //******************************Volley ends here*****************
            }
        });





        listViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(Variables.client_email.isEmpty()) {

                    Toast.makeText(context, "Please login to apply", Toast.LENGTH_SHORT).show();
//                    Login login = new Login();
//                    FragmentTransaction fragmentTransaction = myContext.getSupportFragmentManager().beginTransaction();
//                    fragmentTransaction.replace(R.id.frame, login);
//                    fragmentTransaction.commit();
                }



                Toast.makeText(context, "Click Apply", Toast.LENGTH_SHORT).show();



            }
        });



        String i,l,q,e,s,d;

        i="Job Id ";
        l="Location-:";
        q=" Gradutes";
        e="Course ";
        s="Subject ";
        d="Designation";

        // mm=locations[position];

//        textId.setText(i+ids[position]);
//        textLocation.setText(l+locations[position]);
//        textQualification.setText(q+qualifications[position]);
//        textExperience.setText(e+experiences[position]);
//        // textDesignation.setText(d+designations[position]);
//        textSubjects.setText(s+subjects[position]);



  if (useremail[position].equals(Variables.client_email))
          {
              textId.setText(i+ids[position]);
              textLocation.setText(l+locations[position]);
             // textQualification.setText(qualifications[position]+q);
              textExperience.setText(experiences[position]+q);
              // textDesignation.setText(d+designations[position]);
              textSubjects.setText(s+subjects[position]);
              butapply.setImageBitmap(bitmap1);

          }
          else
          {
              textId.setText(i+ids[position]);
              textLocation.setText(l+locations[position]);
            //  textQualification.setText(q+qualifications[position]);
              textExperience.setText(experiences[position]+q);
              // textDesignation.setText(d+designations[position]);
              textSubjects.setText(s+subjects[position]);
              butapply.setImageBitmap(bitmap2);
          }









        return listViewItem;
    }



}




