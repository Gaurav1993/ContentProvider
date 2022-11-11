package com.example.allprepare;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ListView mlist_view;
    List<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mlist_view = findViewById(R.id.list_view);t
        arrayList=new ArrayList<String>();
        featchContact();


    }

    private void featchContact() {
        Uri uri= ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection={ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER};
        String selection="";
        String[] selectionArgs=null;
        String sortOrder="";

        ContentResolver contentResolver = getContentResolver();
        Cursor cursor=contentResolver.query(uri,projection,selection,selectionArgs,sortOrder);

        while(cursor.moveToNext())
        {
            @SuppressLint("Range") String Name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            @SuppressLint("Range") String Number=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            Log.d("Data_Value","Name"+Name+"number"+Number);
            arrayList.add(Name+"\n"+Number);
        }

        mlist_view.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList));

        mlist_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Log.d("Item",adapterView.getAdapter().getItem(i).toString());
            }
        });

    }


}
