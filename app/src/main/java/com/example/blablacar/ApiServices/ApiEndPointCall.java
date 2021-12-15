package com.example.blablacar.ApiServices;

import com.example.blablacar.model.request.VehicleClientRequest;
import com.example.blablacar.model.response.Stops;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface ApiEndPointCall {
    @GET("stops")
    Call<Stops> vehicleDetails();
}
