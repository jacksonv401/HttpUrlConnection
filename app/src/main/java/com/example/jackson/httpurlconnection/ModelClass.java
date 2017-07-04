package com.example.jackson.httpurlconnection;

import android.graphics.PorterDuff;

/**
 * Created by jackson on 27/5/17.
 */

public class ModelClass {
    private String name;
    private String secondName;

    public ModelClass(String name , String secondName){
        this.name = name;
        this.secondName = secondName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getName() {
        return name;
    }
}
