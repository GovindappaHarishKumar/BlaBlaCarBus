package com.example.blablacar.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleClientRequest {
    @Expose
    @SerializedName("clientid")
    private int clientId;
    @Expose
    @SerializedName("mno")
    private String phoneNumber;
    @Expose
    @SerializedName("passcode")
    private int passCode;
    @Expose
    @SerializedName("enterprise_code")
    private int enterpriseCode;

    public VehicleClientRequest(int clientId, String phoneNumber, int passCode, int enterpriseCode) {
        this.clientId = clientId;
        this.phoneNumber = phoneNumber;
        this.passCode = passCode;
        this.enterpriseCode = enterpriseCode;
    }
}
