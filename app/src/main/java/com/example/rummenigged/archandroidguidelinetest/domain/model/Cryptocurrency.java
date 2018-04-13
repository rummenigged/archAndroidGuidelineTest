package com.example.rummenigged.archandroidguidelinetest.domain.model;

/**
 * Created by rummenigged on 20/03/18.
 */

public class Cryptocurrency {
    private String name;
    private double value;
    private long savedAt;

    public Cryptocurrency(String name, double value, Long savedAt) {
        this.name = name;
        this.value = value;
        this.savedAt = savedAt;
    }

    public Cryptocurrency() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Long getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(Long savedAt) {
        this.savedAt = savedAt;
    }
}
