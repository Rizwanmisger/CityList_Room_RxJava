package com.example.rezwann.cities;

import android.app.SearchManager;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.rezwann.cities.app.App;
import com.example.rezwann.cities.databinding.ActivityMainBinding;
import com.example.rezwann.cities.viewModel.CitiesViewModel;

import java.util.Observable;
import java.util.Observer;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements Observer{
    final static String TAG = MainActivity.class.getName();
    @Inject
    CitiesViewModel viewModel;

    ActivityMainBinding binding;
    private SearchView searchView;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App)getApplication()).getAppComponent().inject(this);
        initDataBinding();
        setSupportActionBar(binding.toolbar);
        setupRecyclerView(binding.cityRecycler);
        setupObserver();
    }

    private void initDataBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setCitiesViewModel(viewModel);
    }

    private void setupRecyclerView(RecyclerView recyclerView){
        this.recyclerView = recyclerView;
        CitiesAdapter adapter = new CitiesAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void setupObserver(){viewModel.addObserver(this);}

    @Override
    public void update(Observable observable, Object o) {
        Log.v(TAG, "update called");
        CitiesAdapter adapter = (CitiesAdapter)binding.cityRecycler.getAdapter();
        adapter.setCitiesList(((CitiesViewModel) observable).getCitiesList());
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                ((CitiesAdapter)recyclerView.getAdapter()).getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                ((CitiesAdapter)recyclerView.getAdapter()).getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
