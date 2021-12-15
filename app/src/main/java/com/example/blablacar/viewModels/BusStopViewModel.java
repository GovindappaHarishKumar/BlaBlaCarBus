package com.example.blablacar.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.blablacar.model.response.Stops;
import com.example.blablacar.repository.BusStopRepository;

public class BusStopViewModel extends AndroidViewModel {
    private LiveData<Stops> stopsLiveData;
    private final BusStopRepository busStopRepository;

    public BusStopViewModel(@NonNull Application application) {
        super(application);
        busStopRepository = BusStopRepository.getInstance();
    }

    public LiveData<Stops> getStopsLiveData() {
        if (stopsLiveData == null) {
            stopsLiveData = busStopRepository.getStopsLiveApiData();
        }
        return stopsLiveData;
    }

}
