package com.softserve.academy.core;

import org.junit.jupiter.api.BeforeEach;

public class RunnerExtensions extends TestRunner {

    @BeforeEach
    void openUrl() {
        driver.navigate().to("https://www.greencity.cx.ua/#/greenCity");
    }
}
