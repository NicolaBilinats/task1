package org.example.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.example.dto.ApiResponse;
import org.example.model.Country;
import org.example.model.PhoneNumber;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OnlineSimPhoneApiService implements PhoneApiService {

    private static final String BASE_URL = "https://onlinesim.ru/api/";
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();
    private static final Type PHONE_LIST_TYPE = new TypeToken<ApiResponse>() {}.getType();

    private static final Type COUNTRY_LIST_TYPE = new TypeToken<ApiResponse>() {}.getType();

    @Override
    public List<Country> getFreeCountryList() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + "getFreeCountryList")
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = Objects.requireNonNull(response.body()).string();
            ApiResponse apiResponse = gson.fromJson(responseBody, COUNTRY_LIST_TYPE);
            List<Country> countries = apiResponse.getCountries();

            for (Country country : countries) {
                country.setCode(country.getCountry());
                List<String> phones = getFreePhoneList(country.getCountry());
                country.setPhones(phones);
            }

            return countries;
        }
    }

    private List<String> getFreePhoneList(String countryCode) throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + "getFreePhoneList?country=" + countryCode)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = Objects.requireNonNull(response.body()).string();
            ApiResponse apiResponse = gson.fromJson(responseBody, PHONE_LIST_TYPE);

            if (apiResponse.getNumbers() == null) {
                return new ArrayList<>();
            }

            List<String> phoneList = new ArrayList<>();
            for (PhoneNumber phoneNumber : apiResponse.getNumbers()) {
                phoneList.add(phoneNumber.getFull_number());
            }
            return phoneList;
        }
    }


}
