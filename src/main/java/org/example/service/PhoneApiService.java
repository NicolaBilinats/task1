package org.example.service;

import org.example.model.Country;

import java.io.IOException;
import java.util.List;

public interface PhoneApiService {
    List<Country> getFreeCountryList() throws IOException;
}
