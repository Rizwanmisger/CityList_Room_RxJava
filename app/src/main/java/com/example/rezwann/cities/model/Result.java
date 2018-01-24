package com.example.rezwann.cities.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RezwanN on 22-01-2018.
 */

public class Result {
    List<Cities> objects;

    public List<Cities> getObjects() {
        return objects;
    }

    public void setObjects(List<Cities> objects) {
        this.objects = objects;
    }
}
