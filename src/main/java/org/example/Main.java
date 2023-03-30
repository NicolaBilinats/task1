package org.example;

import org.example.frame.PhoneListFrame;
import org.example.model.Country;
import org.example.service.OnlineSimPhoneApiService;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        OnlineSimPhoneApiService apiService = new OnlineSimPhoneApiService();

        SwingUtilities.invokeLater(() -> {
            PhoneListFrame frame = new PhoneListFrame();
            frame.setVisible(true);

            try {
                List<Country> countries = apiService.getFreeCountryList();

                frame.appendText("Code,Country,Phone");

                for (Country country : countries) {
                    frame.appendText(country.convertToCsv());
                }
            } catch (IOException e) {
                e.printStackTrace();
                frame.appendText("Error: " + e.getMessage());
            }
        });
    }
}


