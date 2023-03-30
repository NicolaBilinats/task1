package org.example.dto;

import org.example.model.Country;
import org.example.model.PhoneNumber;

import java.util.List;

public class ApiResponse {
    int response;
    List<Country> countries;
    List<PhoneNumber> numbers;

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public List<PhoneNumber> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<PhoneNumber> numbers) {
        this.numbers = numbers;
    }
}