package com.example.myscan.Model;

public class DeviceInfo {
    private String model;
    private String serialNumber;
    private String partNumber;
    private String manufactureDate;
    private String location;

    public DeviceInfo() {
        // Пустой конструктор требуется для Firebase
    }

    public DeviceInfo(String model, String serialNumber, String partNumber, String manufactureDate, String location) {
        this.model = model;
        this.serialNumber = serialNumber;
        this.partNumber = partNumber;
        this.manufactureDate = manufactureDate;
        this.location = location;
    }

    // Геттеры и сеттеры для каждого поля

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(String manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}