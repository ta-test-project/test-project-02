package com.softserve.academy.tests;

import com.softserve.academy.core.RunnerExtensions;
import com.softserve.academy.data.RegistrationData;
import com.softserve.academy.data.UserRepository;
import com.softserve.academy.pages.HomePage;
import com.softserve.academy.pages.RegistrationModal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GreenCityRegistrationTests extends RunnerExtensions {

    static Stream<Arguments> registrationData() {
        return new UserRepository("/registration_data.csv").loadUsers();
    }

    @ParameterizedTest(name = "Data from file - Email: {0}")
    @DisplayName("Check registration via CsvFileSource (external file)")
    @MethodSource("registrationData")
    void testWithCsvFileSource(RegistrationData data) {
        RegistrationData uniqueData = new RegistrationData(
                generateUniqueEmail(data.getEmail()),
                data.getName(),
                data.getPassword());

        HomePage homePage = new HomePage(driver);
        RegistrationModal registrationModal = homePage.openRegistrationModal();
        registrationModal.fillRegistrationForm(uniqueData);
        registrationModal.submitRegistration();

        String msg = registrationModal.getSuccessMessageText();
        assertTrue(msg.contains("successfully registered"),
                "Success message should contain: 'successfully registered'");
    }

    private String generateUniqueEmail(String email) {
        return "test" + UUID.randomUUID().toString().substring(0, 8) + email;
    }
}
