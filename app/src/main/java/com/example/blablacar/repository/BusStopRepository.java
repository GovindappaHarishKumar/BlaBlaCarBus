package com.example.blablacar.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.blablacar.ApiServices.ApiEndPointCall;
import com.example.blablacar.ApiServices.ApiRetrofitClient;
import com.example.blablacar.model.request.VehicleClientRequest;
import com.example.blablacar.model.response.Stops;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusStopRepository {
    private static BusStopRepository busStopRepository;
    private final MutableLiveData<Stops> responseLiveData = new MutableLiveData<>();
    private final ApiEndPointCall endPointCall;

    private BusStopRepository() {
        endPointCall = ApiRetrofitClient.getInstance().getApiCall();

    }

    public static BusStopRepository getInstance() {
        if (busStopRepository == null) {
            busStopRepository = new BusStopRepository();
        }
        return busStopRepository;
    }

    public LiveData<Stops> getStopsLiveApiData() {
        Call<Stops> responseCall = endPointCall.vehicleDetails();
        responseCall.enqueue(new Callback<Stops>() {
            @Override
            public void onResponse(Call<Stops> call, Response<Stops> response) {
                if (response.isSuccessful()) {
                    Stops vehicleData = response.body();
                    responseLiveData.postValue(vehicleData);

                } else {
                    Stops errorResponse = new Stops();

                    responseLiveData.postValue(errorResponse);
                }
            }

            @Override
            public void onFailure(Call<Stops> call, Throwable t) {
                Stops errorResponse = new Stops();
                responseLiveData.postValue(errorResponse);
            }
        });
        return responseLiveData;
    }
}
