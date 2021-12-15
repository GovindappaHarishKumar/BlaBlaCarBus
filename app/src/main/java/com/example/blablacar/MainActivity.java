package com.example.blablacar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.blablacar.adapter.StopListAdapter;
import com.example.blablacar.databinding.ActivityMainBinding;
import com.example.blablacar.model.response.StopDetail;
import com.example.blablacar.viewModels.BusStopViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, StopListAdapter.StopSelection {
    private BusStopViewModel viewModel;
    private StopListAdapter adapter;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        adapter = new StopListAdapter(this);
        binding.vehicleType.setAdapter(adapter);
        viewModel = new ViewModelProvider(this).get(BusStopViewModel.class);
        viewModel.getStopsLiveData().observe(this, stopsData -> {
            if (stopsData != null) {
                if (stopsData.getStops() != null) {
                    adapter.setStopDetails(stopsData.getStops());
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        viewModel.getStopsLiveData().removeObservers(this);
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void onStopClick(StopDetail detail) {
        Intent main = new Intent(MainActivity.this, StopDetailActivity.class);
        main.putExtra("stopId", detail.getId());
        startActivity(main);
    }
}