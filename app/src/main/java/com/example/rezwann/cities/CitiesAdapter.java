package com.example.rezwann.cities;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.rezwann.cities.databinding.CityBinding;
import com.example.rezwann.cities.model.Cities;
import com.example.rezwann.cities.viewModel.CityViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by RezwanN on 22-01-2018.
 */

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.Holder>
                            implements Filterable {
    final static String TAG = CitiesAdapter.class.getName();
    List<Cities> citiesList;
    List<Cities> citiesListFiltered;
    public CitiesAdapter(){
        this.citiesList = Collections.emptyList();
        this.citiesListFiltered = Collections.emptyList();
    }
    public void setCitiesList(List<Cities> citiesList){
        Log.v(TAG, "List Size " + citiesList.size());
        this.citiesList = citiesList;
        this.citiesListFiltered = citiesList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        CityBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.city, parent, false);
        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bindCity(citiesListFiltered.get(position));
    }

    @Override
    public int getItemCount() {
        return citiesListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                Log.v(TAG, "Filter called " + charString);
                if (charString.isEmpty()) {
                    citiesListFiltered = citiesList;
                } else {
                    List<Cities> filteredList = new ArrayList<>();
                    for (Cities row : citiesList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    citiesListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = citiesListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                citiesListFiltered = (ArrayList<Cities>) filterResults.values;
                Log.v(TAG, "Filter applied " + citiesListFiltered.size());
                notifyDataSetChanged();
            }
        };
    }

    public class Holder extends RecyclerView.ViewHolder{
        CityBinding cityBinding;
        public Holder(CityBinding cityBinding){
            super(cityBinding.city);
            this.cityBinding = cityBinding;
        }
        public void bindCity(Cities city){
            if(cityBinding.getCityViewModel() == null){
                cityBinding.setCityViewModel(new CityViewModel(city));
            }
            else{
                cityBinding.getCityViewModel().setCity(city);
            }
            Log.v(TAG, "City Name " + cityBinding.getCityViewModel().getCityName());
        }
    }
}
