package org.example.model;

public class PhoneNumber {
    private String number;
    private int country;
    private String full_number;
    private String country_text;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public String getFull_number() {
        return full_number;
    }

    public void setFull_number(String full_number) {
        this.full_number = full_number;
    }

    public String getCountry_text() {
        return country_text;
    }

    public void setCountry_text(String country_text) {
        this.country_text = country_text;
    }
}
