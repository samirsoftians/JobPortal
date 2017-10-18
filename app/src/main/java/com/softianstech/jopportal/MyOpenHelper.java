package com.softianstech.jopportal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Lenovo on 7/8/2017.
 */

public class MyOpenHelper  extends SQLiteOpenHelper
{

    private static final String Database_Name="StudentDataBase";
    private static final int Database_Version=3;
    public static final String Table_Name="Student";



    Context mycontext;

    public MyOpenHelper(Context context) {
        super(context,Database_Name,null,Database_Version);
        this.mycontext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table Student(Id Text Primary Key ,Email Text ,Pass Text)");



//        ContentValues ltable=new ContentValues();
//        ltable.put("LoginId",1);
//        ltable.put("Stu_Id",1);
//        ltable.put("UserName","admin");
//        ltable.put("Password","admin");
//        long newrow=db.insert(L_Table_Name,null,ltable);
//        if (newrow>=0)
//        {
//            mToast("Insertion successful");
//        }
//        else
//        {
//            mToast("Insertion failed");
//        }


    }
    private void mToast(String str)
    {
        Toast.makeText(mycontext, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("Drop Table if Exists "+Table_Name);

        onCreate(db);
    }
}
