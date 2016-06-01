package com.example.subba.androidinsertblobdataintosqlite;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class EmployeeImageAdapter extends ArrayAdapter<Employee> {
    Context context;
    int layoutResourceId;
    ArrayList<Employee> data=new ArrayList<Employee>();
    public EmployeeImageAdapter(Context context, int layoutResourceId, ArrayList<Employee> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ImageHolder holder = null;
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ImageHolder();
            holder.txtTitle = (TextView)row.findViewById(R.id.name);
            holder.age = (TextView)row.findViewById(R.id.age);
            holder.imgIcon = (ImageView)row.findViewById(R.id.imag);
            row.setTag(holder);
        }
        else
        {
            holder = (ImageHolder)row.getTag();
        }
        Employee picture = data.get(position);
        holder.txtTitle.setText(picture ._name);
        holder.age.setText(String.valueOf(picture ._age));
//convert byte to bitmap take from Employee class
        byte[] outImage=picture._image;
        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        holder.imgIcon.setImageBitmap(theImage);
        return row;
    }
    static class ImageHolder
    {
        ImageView imgIcon;
        TextView txtTitle;
        TextView age;
    }


}
