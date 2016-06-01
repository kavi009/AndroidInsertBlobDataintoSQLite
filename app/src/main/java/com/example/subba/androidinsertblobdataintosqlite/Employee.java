package com.example.subba.androidinsertblobdataintosqlite;

import android.graphics.Bitmap;

public class Employee {
    int _id;
    String _name;
    int _age;
    byte[] _image;

    public Employee() {

    }

    public Employee(int keyId, String name, int age, byte[] image) {
        this._id = keyId;
        this._name = name;
        this._age = age;
        this._image = image;

    }
    public Employee(String name, int age, byte[] image) {
        this._name = name;
        this._age = age;
        this._image = image;
    }

    public int getID() {
        return this._id;
    }


    public void setID(int keyId) {
        this._id = keyId;
    }


    public String getName() {
        return this._name;
    }


    public void setName(String name) {
        this._name = name;
    }

    public int getAge() {
        return this._age;
    }


    public void setAge(int age) {
        this._age = age;
    }
    public byte[] getImage() {
        return this._image;
    }

    public void setImage(byte[] image) {
        this._image = image;
    }
}
