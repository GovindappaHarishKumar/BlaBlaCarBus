package com.example.blablacar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.blablacar.adapter.StopListAdapter;
import com.example.blablacar.databinding.ActivityStopDetailBinding;
import com.example.blablacar.model.response.StopDetail;
import com.example.blablacar.viewModels.BusStopViewModel;

import java.util.ArrayList;
import java.util.List;

public class StopDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private BusStopViewModel viewModel;
    private StopListAdapter adapter;
    private ActivityStopDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_stop_detail);
        binding.setLifecycleOwner(this);
        adapter = new StopListAdapter(null);
        binding.vehicleType.setAdapter(adapter);
        viewModel = new ViewModelProvider(this).get(BusStopViewModel.class);
        viewModel.getStopsLiveData().observe(this, stopsData -> {
            if (stopsData != null) {
                if (stopsData.getStops() != null) {
                    updateSelectedStopAndDesignations(stopsData.getStops());
                }
            }
        });

    }

    private void updateSelectedStopAndDesignations(List<StopDetail> stops) {
        Intent selected = getIntent();
        if (selected != null && selected.getExtras() != null) {
            StopDetail selectedStop = new StopDetail();
            selectedStop.setId(selected.getExtras().getInt("stopId"));
            int index = stops.indexOf(selectedStop);
            if (index != -1) {
                selectedStop = stops.get(index);
                binding.setStop(selectedStop);
                List<Integer> designationIds = selectedStop.getDestinations();
                if (designationIds != null && designationIds.size() > 0) {
                    List<StopDetail> designationStops = new ArrayList<>();
                    for (int id : designationIds) {
                        StopDetail designation = new StopDetail();
                        designation.setId(id);
                        int id_index = stops.indexOf(designation);
                        if (id_index != -1) {
                            designationStops.add(stops.get(id_index));
                        }
                    }
                    adapter.setStopDetails(designationStops);
                }
            }
        }

    }

    @Override
    protected void onDestroy() {
        viewModel.getStopsLiveData().removeObservers(this);
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
    }
}