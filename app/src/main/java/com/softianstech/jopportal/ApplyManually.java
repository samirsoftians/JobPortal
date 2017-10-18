package com.softianstech.jopportal;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Lenovo on 6/1/2017.
 */

public class ApplyManually extends Fragment {

    Spinner subject2,location2,experience2;
    Button submit2;
    private ArrayList<String> students2,students3,students4;
    Animation shake,bounce;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // return super.onCreateView(inflater, container, savedInstanceState);
        View v=inflater.inflate(R.layout.layout,container,false);
        bounce = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);

        subject2= (Spinner) v.findViewById(R.id.subjects2);
         location2= (Spinner) v.findViewById(R.id.location2);
         experience2= (Spinner) v.findViewById(R.id.experience2);

        submit2= (Button) v.findViewById(R.id.submit2);
        students2=new ArrayList<String>();
        students3=new ArrayList<String>();
        students4=new ArrayList<String>();


        getStudents();
        getStudents2();
        getStudents3();

        submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit2.startAnimation(bounce);

                   if(subject2.getSelectedItem().toString().equals("Select Subject"))
                {


                    Toast.makeText(getContext(), "Please Select Subjects", Toast.LENGTH_SHORT).show();
                }

                   else if(location2.getSelectedItem().toString().equals("Select City"))
{

    Toast.makeText(getContext(), "Please Select City", Toast.LENGTH_SHORT).show();
}

//              else   if(subject2.getSelectedItem().toString().equals("Select Subject"))
//                {
//
//                    Toast.makeText(getContext(), "Please Select Subjects", Toast.LENGTH_SHORT).show();
//                }
else   if(experience2.getSelectedItem().toString().equals("Select Qulification"))
{

    Toast.makeText(getContext(), "Please Select Qulification", Toast.LENGTH_SHORT).show();
}
else {


    Variables.location=location2.getSelectedItem().toString();
    Variables.subjects=subject2.getSelectedItem().toString();
    Variables.qualification=experience2.getSelectedItem().toString();

    JobFilter jobFilter = new JobFilter();
    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.frame, jobFilter);
    fragmentTransaction.commit();
}

               // Toast.makeText(getContext(), "Still the code is pending , Please check the login and the Registration page", Toast.LENGTH_SHORT).show();
            }
        });




        Button manlogin= (Button) v.findViewById(R.id.manlogin);
        Button manregister= (Button) v.findViewById(R.id.manregister);

        manlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login fragment2 = new Login();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment2);
                fragmentTransaction.commit();
            }
        });



        manregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        students2.add("Select City");
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
//                    students.add(json.getString("business_type"));//Config.TAG_USERNAME
//
//                }
//            } catch (JSONException e)
//            {
//                e.printStackTrace();
//            }
//        }
        students2.add("Amar");
        students2.add("Aundh");
        students2.add("Balewadi");
        students2.add("Baner");
        students2.add("Bavdhan");
        students2.add("Botanical Garden");
        students2.add("Dapodi");
        students2.add("Dhankawadi");
        students2.add("Gardon House");
        students2.add("Hadapsar");
        students2.add("Hingne");
        students2.add("Kadki East");
        students2.add("Kadki West");
        students2.add("Kondhwa kh");
        students2.add("Katraj");
        students2.add("Khadki");
        students2.add("Kharadi");
        students2.add("Kinara");
        students2.add("Laxmi Narayan");
        students2.add("Mohamadwadi");
        students2.add("warje");
        students2.add("wadgaon BK.");
        students2.add("wadgaon Kh");
        students2.add("Kothrud");
        students2.add("Wanawadi");
        students2.add("Viman Nagar");
        students2.add("Tilak Vidyapeeth");
        students2.add("Tanji Wadi");
        students2.add("Swargate");
        students2.add("Sutarwadi");
        students2.add("Shivane");
        students2.add("Shivajinagar");
        students2.add("Shalimar");
        students2.add("Sangavi");
        students2.add("Raviraj");
        students2.add("RajBhavan");
        students2.add("Pashan");
        students2.add("Parvati");
        students2.add("Neelayam");
//        students.add("Kolkatta");
//        students.add("Chennai");
//        students.add("Banglore");
//        students.add("Noida");
//        students.add("Gurgaon");
//        students.add("Faridabad");
//        students.add("Kochi");
//        students.add("Jaipur");
//        students.add("Chandigarh");
//        students.add("Ahmedabad");
//        students.add("Others");
        location2.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, students2));
        Variables.location=location2.getSelectedItem().toString();
        location2.setSelection(students2.indexOf(Variables.location));

    }
//**************************************

    public void getStudents2()//here it was JSONArray j
    {

        students3.add("Select Subject");
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
//                    students.add(json.getString("business_type"));//Config.TAG_USERNAME
//
//                }
//            } catch (JSONException e)
//            {
//                e.printStackTrace();
//            }
//        }

        students3.add("Accountancy");
        students3.add("Archaelogy");
        students3.add("Biology");
        students3.add("Business Studies");
        students3.add("C");
        students3.add("C#");
        students3.add("Chemistry");
        students3.add("Civics");
        students3.add("Computers");
        students3.add("Economics");
        students3.add("English");
        students3.add("Geography");
        students3.add("Geology");
        students3.add("Hindi");
        students3.add("History");
        students3.add("Java");
        students3.add("Maths");
        students3.add("Perl");
        students3.add("Philosophy");
        students3.add("Physics");
        students3.add("Political Science");
        students3.add("Principal of Management");
        students3.add("Psychology");
        students3.add("Sanskrit");
        students3.add("Sql");
        subject2.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, students3));
        Variables.subjects=subject2.getSelectedItem().toString();
        subject2.setSelection(students3.indexOf(Variables.subjects));

    }

    //******************

    public void getStudents3()//here it was JSONArray j
    {
        students4.add("Select Qulification");
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
//                    students.add(json.getString("business_type"));//Config.TAG_USERNAME
//
//                }
//            } catch (JSONException e)
//            {
//                e.printStackTrace();
//            }
//        }

        students4.add("APGDCA");
        students4.add("BA");
        students4.add("BBA");
        students4.add("BCA");
        students4.add("BCOM");
        students4.add("BE");
        students4.add("BSC");
        students4.add("BTECH");
        students4.add("ITI");
        students4.add("MA");
        students4.add("MBA");
        students4.add("MCA");
        students4.add("MCOM");
        students4.add("Sql");
        students4.add("MS");
        students4.add("ME");
        students4.add("MSC");
        students4.add("ME");
        students4.add("MTECH");
        experience2.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, students4));
        Variables.qualification=experience2.getSelectedItem().toString();
        experience2.setSelection(students4.indexOf(Variables.qualification));

    }

}
