package com.softserve.academy.data;

import org.junit.jupiter.params.provider.Arguments;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class UserRepository {

    private final String csvResource;

    public UserRepository(String csvResource) {
        this.csvResource = csvResource;
    }

    public Stream<Arguments> loadUsers() {
        List<Arguments> args = new ArrayList<>();
        try (InputStream is = getClass().getResourceAsStream(csvResource);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                RegistrationData data = new RegistrationData(
                        parts[0].trim(), parts[1].trim(), parts[2].trim());
                args.add(Arguments.of(data));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load users from CSV: " + csvResource, e);
        }
        return args.stream();
    }
}
