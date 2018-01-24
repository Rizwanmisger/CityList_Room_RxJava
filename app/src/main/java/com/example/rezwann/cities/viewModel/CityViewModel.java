package com.example.rezwann.cities.viewModel;

import android.databinding.BaseObservable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.rezwann.cities.model.Cities;

import java.util.Observable;

/**
 * Created by RezwanN on 22-01-2018.
 */

public class CityViewModel extends BaseObservable {
    final static String TAG = CityViewModel.class.getName();
  Cities city;

  public CityViewModel(Cities city){
      this.city = city;
  }

  public String getCityName(){
      return city.getName();
  }

  public void setCity(Cities city){
        this.city = city;
        notifyChange();
  }

  public void onClick(View view){
      Log.v(TAG, "city selected " + getCityName());
      Toast.makeText(view.getContext(),getCityName(), Toast.LENGTH_LONG).show();
  }
}
