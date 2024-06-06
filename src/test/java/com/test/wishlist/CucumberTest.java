package com.test.wishlist;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.test.wishlist",
        plugin = { "pretty", "summary" }
)
@ActiveProfiles("test")
public class CucumberTest {
}
