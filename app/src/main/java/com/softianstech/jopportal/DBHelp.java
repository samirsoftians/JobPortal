package com.softianstech.jopportal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Lenovo on 7/8/2017.
 */

public class DBHelp {

    SQLiteDatabase db;
    Context context;
    ArrayList<String> item_InFo = new ArrayList<String>() ;
    String id="1";
    public DBHelp(Context con)
    {
        this.context = con;

        SQLiteOpenHelper myHelper = new MyOpenHelper(this.context);
        this.db = myHelper.getWritableDatabase();
    }
    public void StuInsert(String email,String pass)
    {

//        String count = "SELECT Id(*) FROM Student";
//        Cursor mcursor = db.rawQuery(count, null);
//        mcursor.moveToFirst();
//        int icount = mcursor.getInt(0);

//        if(icount<=0)
//        {
//
//
//
//            ContentValues conV = new ContentValues();
//            conV.put("Id",id);
//            long newrow = db.insert(MyOpenHelper.Table_Name,null,conV);
//
//            if(newrow >= 0)
//            {
//                Toast.makeText(context, "Insertion successful", Toast.LENGTH_SHORT).show();
//            }
//            else
//            {
//                Toast.makeText(context, "Insertion unsuccessful", Toast.LENGTH_SHORT).show();
//            }
//        }
       // else
       // {
            ContentValues conV2 = new ContentValues();
        conV2.put("Id",id);
            conV2.put("Stu_Email",email);
            conV2.put("Stu_Pass",pass);



            String where = "Id = "+id;
            //int newrow2 =db.update(MyOpenHelper.Table_Name, conV2, where, null);

        long newrow2 = db.insert(MyOpenHelper.Table_Name,null,conV2);


            if(newrow2 >= 0)
            {
                Toast.makeText(context, "update successful", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "update unsuccessful", Toast.LENGTH_SHORT).show();
            }
        }





   // }

    public String GetQuestion(String column)
    {
        String ques="";
        Cursor c = db.rawQuery("SELECT * FROM Student where Id="+id+"",null);

        if(c!= null)
        {
            if(c.moveToFirst())
            {
                do
                {
                    ques = c.getString(c.getColumnIndex(column));
                }
                while(c.moveToNext());
            }
        }
        else
        {
            //mToast("No Questions...!");
            item_InFo.add("No Question....!");
        }
        c.close();
        return ques;
    }
}
