package com.softianstech.jopportal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Context c;
    Bitmap defaultimage;

//
    SharedPreferences sharedpreferences;

    public static int h=0;

    String userinterface;
    // int count=0;
    // int splash=0;
    // int a=0;
    public static ImageView imageView1;
    Button navlogin;
    Button navregister;

    public static TextView welcome1;

    public static Button loginregester2,loginlogin2;

    public static int count=0;


    //TextView welcome1,welcome2;

    //*****************************SHARRED PREFERENCES TO STORE DATA****
    public static final String mypreference = "mypref";
    public static final String Password = "nameKey";
    public static final String Email = "emailKey";
    //****************************END HERE*************************z

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        FirebaseMessaging.getInstance().subscribeToTopic("Test");
        FirebaseInstanceId.getInstance().getToken();
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        FirebaseInstanceIDService.sharedpreferences1 = getSharedPreferences(FirebaseInstanceIDService.myToken, Context.MODE_PRIVATE);

        defaultimage= BitmapFactory.decodeResource(MainActivity.this.getResources(),R.drawable.prifileimage);



        if(sharedpreferences.contains(Email))
        {
            Variables.a++;
            Variables.islogin=2;
            Variables.client_email = sharedpreferences.getString(Email, "");
           // getImage();

        }

        if(Variables.islogin==1 && Variables.a <= 0)
        {

            setContentView(R.layout.logo);
            if (Variables.a <= 0) {


                Variables.a++;
                // setContentView(R.layout.logo);

                new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        // Start your app main activity
//

                        // close this activity


                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }, Variables.SPLASH_TIME_OUT);

            }

        }

        else {
//           finish();
            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

//*****************************************************************************************************************************************************

//           /* if (sharedpreferences.contains(Email)) {
//                Display display = new Display();
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.frame, display);
//                fragmentTransaction.commit();
//                Variables.count++;
//            }*/
//          //************************************************************************************************************************************************

            if(sharedpreferences.contains(Email))
            {
                int h=1;
                count=1;
                Variables.islogin=2;
                Variables.client_email = sharedpreferences.getString(Email, "");
                AllJobs myJob = new AllJobs();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, myJob);
                fragmentTransaction.commit();
            }
            else {

                if (Variables.count <= 0) {


                    ApplyManually applyManually = new ApplyManually();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, applyManually);
                    fragmentTransaction.commit();
                    Variables.count++;
                    // Handle the camera action
                }
            }
            Variables.CheckSecondNavigation = 0;//making it to execute when registration is done
            //  Variables.client_email



            if (sharedpreferences.contains(Email)) {
                Variables.islogin=2;
                Variables.client_email = sharedpreferences.getString(Email, "");//will have both values
            }


            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            welcome1 = (TextView) navigationView.getHeaderView(0).findViewById(R.id.welcome1);
            imageView1 = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.image1);

            loginlogin2=(Button) navigationView.getHeaderView(0).findViewById(R.id.loginlogin2);

            loginregester2=(Button) navigationView.getHeaderView(0).findViewById(R.id.loginregister2);


            if (sharedpreferences.contains(Email)) {
                getImage();
                welcome1.setText("Welcome");
                Variables.islogin=2;
                Variables.client_email = sharedpreferences.getString(Email, "");//will have both values
            }


            loginlogin2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Login login = new Login();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, login);
                    fragmentTransaction.commit();

                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);

                }
            });

            loginregester2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoginRegistration allertDialogBox = new LoginRegistration();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, allertDialogBox);
                    fragmentTransaction.commit();

                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);

                }
            });


            if (sharedpreferences.contains(Email)) {
                Variables.islogin=2;

                loginregester2.setVisibility(View.INVISIBLE);
                loginlogin2.setVisibility(View.INVISIBLE);

            }
//            else
//            {
//                imageView1.setVisibility(View.INVISIBLE);
//            }


            if (sharedpreferences.contains(Email)) {
                Variables.islogin=2;
                welcome1.setText(sharedpreferences.getString(Email, ""));//will have both values

                //getImage();
            }
            navigationView.setNavigationItemSelectedListener(this);


//            imageView= (ImageView) findViewById(R.id.imageView);
//           navlogin= (Button) findViewById(R.id.navlogin);
//            navregister= (Button) findViewById(R.id.navregister);

//




        }


    }


//
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
 if (id == R.id.myjob) {


     if(Variables.islogin==1) {
         Toast.makeText(MainActivity.this, "Please Login", Toast.LENGTH_SHORT).show();
         Variables.check=1;
         Login myJob = new Login();
         FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
         fragmentTransaction.replace(R.id.frame, myJob);
         fragmentTransaction.commit();


     }
     else {

         AllJobs applyedJob = new AllJobs();
         FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
         fragmentTransaction.replace(R.id.frame, applyedJob);
         fragmentTransaction.commit();
//         MyJob myJob = new MyJob();
//         FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//         fragmentTransaction.replace(R.id.frame, myJob);
//         fragmentTransaction.commit();
     }
        }

        if (id == R.id.appplyedjob) {

            if(Variables.islogin==1) {
                Toast.makeText(MainActivity.this, "Please Login", Toast.LENGTH_SHORT).show();
                Variables.check=1;
                Login myJob = new Login();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, myJob);
                fragmentTransaction.commit();


            }
            else {
                ApplyedJob applyedJob = new ApplyedJob();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, applyedJob);
                fragmentTransaction.commit();
            }

        }


        if (id == R.id.nav_manage) {
            ApplyManually applyManually = new ApplyManually();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, applyManually);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_share) {

            if(Variables.islogin==1) {
                Toast.makeText(MainActivity.this, "Please Login", Toast.LENGTH_SHORT).show();
                Variables.check=1;
                Login myJob = new Login();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, myJob);
                fragmentTransaction.commit();


            }
            else
            {
                Personal personal = new Personal();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, personal);
                fragmentTransaction.commit();
            }

        }
//    else if (id == R.id.nav_send) {
//            Image image=new Image();
//            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.frame,image);
//            fragmentTransaction.commit();
//        }

//        else if (id == R.id.personaldetails) {
//
//            if(Variables.islogin==1) {
//                Toast.makeText(MainActivity.this, "Please Login", Toast.LENGTH_SHORT).show();
//                Variables.check=1;
//                Login myJob = new Login();
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.frame, myJob);
//                fragmentTransaction.commit();
//
//
//            }
//            else {
//                PersonalInfo personalInfo = new PersonalInfo();
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.frame, personalInfo);
//                fragmentTransaction.commit();
//            }
//        }
            else if (id == R.id.experience) {

            if(Variables.islogin==1) {
                Toast.makeText(MainActivity.this, "Please Login", Toast.LENGTH_SHORT).show();
                Variables.check=1;
                Login myJob = new Login();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, myJob);
                fragmentTransaction.commit();


            }
            else {
                Experience experience = new Experience();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, experience);
                fragmentTransaction.commit();
            }
       }
      // else if (id == R.id.education) {
//
//            if(Variables.islogin==1) {
//                Toast.makeText(MainActivity.this, "Please Login", Toast.LENGTH_SHORT).show();
//                Variables.check=1;
//                Login myJob = new Login();
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.frame, myJob);
//                fragmentTransaction.commit();
//
//
//            }
//            else {
//
//                Education education = new Education();
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.frame, education);
//                fragmentTransaction.commit();
//            }
//        } else if (id == R.id.location) {
//
//            if(Variables.islogin==1) {
//                Toast.makeText(MainActivity.this, "Please Login", Toast.LENGTH_SHORT).show();
//                Variables.check=1;
//                Login myJob = new Login();
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.frame, myJob);
//                fragmentTransaction.commit();
//
//
//            }
//            else {
//                PlaceTeach placeTeach = new PlaceTeach();
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.frame, placeTeach);
//                fragmentTransaction.commit();
//            }
//        }
        else if (id == R.id.logout) {

            updatetoken1();
//            Variables.islogin=1;
//            SharedPreferences preferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.clear();
//            editor.commit();
//
//            welcome1.setText("Welcome");
//
//            imageView1.setImageBitmap(defaultimage);
//
//          //  Personal.welcome2.setText("Welcome");
//            loginregester2.setVisibility(View.VISIBLE);
//            loginlogin2.setVisibility(View.VISIBLE);
//           // imageView1.setVisibility(View.INVISIBLE);
//            Variables.subject="";
//
//            Variables.client_email="";
//                    Variables.Name="";
//                    Variables.client_email2="";
//            Variables.location="City";
//            Variables.qualification="Qualification";
//            Variables.subjects="Subjects";
//            Variables.placetoteach="Prefered Location";
//                    Variables.b="";
//            Variables.city="";
//            Variables.vname="";Variables.vemail="";Variables.vphone="";Variables.vcurrentlocation="";
//            Variables.vdateofbirth="";
//            Variables.vgender="null";Variables.vtotexperience="";
//            Variables.vsubjects="";Variables.vnoticeperiod="";
//            Variables.vqualification="";
//            Variables.vinstitute="";
//            Variables.vcourse="";
//            Variables.vyearpass="";
//            Variables.vplaces="";Variables.vresumelocation="";
//
//            Login login = new Login();
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.frame, login);
//            fragmentTransaction.commit();




        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void onBackPressed() {

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }

        //super.onBackPressed();
        Log.d("back button", "back button pressed");
        AlertDialog.Builder ad1 = new AlertDialog.Builder(this);
        ad1.setMessage("Are you sure you want to exit ?");
        ad1.setCancelable(false);


        ad1.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {


            }
        });

        ad1.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                startActivity(intent);
                finish();
                System.exit(0);


            }
        });
        AlertDialog alert = ad1.create();
        alert.show();

    }


//**********************************************To get the image from the server who everlogin************


    //***************************************************************************************************************************
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
                if(b!= null)
                {
                    imageView1.setImageBitmap(b);
                }
                else
                {
                    imageView1.setImageBitmap(defaultimage);

                }

               // Personal.imageView2.setImageBitmap(b);
            }

            @Override
            protected Bitmap doInBackground(String... params) {
                String id = params[0];
                String add = "http://makable-spill.000webhostapp.com/job/getImage.php?user_email=" + sharedpreferences.getString(Email, "");
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

    public void updatetoken1()
    {
        StringRequest request = new StringRequest(Request.Method.POST, Links.DELETE_TOKEN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Variables.islogin=1;
                SharedPreferences preferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();

                welcome1.setText("Welcome");

                imageView1.setImageBitmap(defaultimage);

                //  Personal.welcome2.setText("Welcome");
                loginregester2.setVisibility(View.VISIBLE);
                loginlogin2.setVisibility(View.VISIBLE);
                // imageView1.setVisibility(View.INVISIBLE);
                Variables.subject="";

                Variables.client_email="";
                Variables.Name="";
                Variables.client_email2="";
                Variables.location="City";
                Variables.qualification="Qualification";
                Variables.subjects="Subjects";
                Variables.placetoteach="Prefered Location";
                Variables.b="";
                Variables.city="";
                Variables.vname="";Variables.vemail="";Variables.vphone="";Variables.vcurrentlocation="";
                Variables.vdateofbirth="";
                Variables.vgender="null";Variables.vtotexperience="";
                Variables.vsubjects="";Variables.vnoticeperiod="";
                Variables.vqualification="";
                Variables.vinstitute="";
                Variables.vcourse="";
                Variables.vyearpass="";
                Variables.vplaces="";Variables.vresumelocation="";

                Login login = new Login();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, login);
                fragmentTransaction.commit();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                                myPd_ring.dismiss();
                if (error instanceof NetworkError) {
                    Toast.makeText(MainActivity.this, "Cannot connect to Internet...Please check your connection!", Toast.LENGTH_LONG).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(MainActivity.this, "The server could not be found. Please try again after some time!!", Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(MainActivity.this, "Cannot connect to Internet...Please check your connection !", Toast.LENGTH_LONG).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(MainActivity.this, "Parsing error! Please try again after some time !!", Toast.LENGTH_LONG).show();

                } else if (error instanceof NoConnectionError) {
                    Toast.makeText(MainActivity.this, "Cannot connect to Internet...Please check your connection !", Toast.LENGTH_LONG).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(MainActivity.this, "Cannot connect to Internet...Please check your connection !", Toast.LENGTH_LONG).show();
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

                parameters.put("user_email",Variables.client_email);
                return parameters;
            }
        };
        RequestQueue requestQueue = com.android.volley.toolbox.Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);
    }
}

//******************************************************************************************************************
/*imageView1= (ImageView) findViewById(R.id.image1);
           imageView2= (ImageView) findViewById(R.id.image2);
           welcome1= (TextView) findViewById(R.id.welcome1);
           welcome2= (TextView) findViewById(R.id.welcome2);

           welcome1.setText(Variables.client_email);
           welcome2.setText(Variables.client_email);*/