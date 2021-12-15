package com.example.blablacar.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Stops {
    @Expose
    @SerializedName("stops")
    private List<StopDetail> stops;

    public List<StopDetail> getStops() {
        return stops;
    }

    public void setStops(List<StopDetail> stops) {
        this.stops = stops;
    }
}
