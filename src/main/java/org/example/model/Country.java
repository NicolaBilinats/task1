package org.example.model;

import java.util.List;

public class Country {
    private String code;
    private String country;
    private List<String> phones;

    private String country_text;

    public int getCode() {
        return code == null ? 0 : Integer.parseInt(code);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public String getCountry_text() {
        return country_text;
    }

    public void setCountry_text(String country_text) {
        this.country_text = country_text;
    }

    public String convertToCsv() {
        return code + "," +
                country_text + "," +
                phones + "\n";
    }
}
