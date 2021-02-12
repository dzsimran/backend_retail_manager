package com.geninvo.backend.modal.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddressCreatedDto {

    @NotBlank
    private String city;

    @NotBlank
    public String state;

    @NotNull
    public double latitude;

    @NotNull
    public double longitude;

    public String address;

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(final double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(final double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
