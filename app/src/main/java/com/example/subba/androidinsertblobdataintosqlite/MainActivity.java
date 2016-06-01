package com.example.subba.androidinsertblobdataintosqlite;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Employee> imageArry = new ArrayList<Employee>();
    EmployeeImageAdapter adapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBhelper db = new DBhelper(this);
// get image from drawable
        Bitmap image = BitmapFactory.decodeResource(getResources(),
                R.mipmap.acad);

// convert bitmap to byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 90, stream);
        byte[] imageInByte = stream.toByteArray();

        Log.d("Insert: ", "Inserting ..");
     db.addEmployee(new Employee("Venkat",25,imageInByte));


// Reading all employees from database
        List<Employee> contacts = db.getEmployeeList();
        for (Employee cn : contacts) {
            String log = "ID:" + cn.getID() + " Name: " + cn.getName() +" ,Age: " + cn.getAge()
                    + " ,Image: " + cn.getImage();

            Log.d("Result: ", log);
            imageArry.add(cn);

        }
        adapter = new EmployeeImageAdapter(this, R.layout.screen_list,
                imageArry);
        ListView dataList = (ListView) findViewById(R.id.list);
        dataList.setAdapter(adapter);
    }
}
