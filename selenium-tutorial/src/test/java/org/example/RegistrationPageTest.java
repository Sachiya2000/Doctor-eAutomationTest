package org.example;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPageTest {

    @Test
    public void testRegistration() {
        // Set up the Chrome driver (ensure chromedriver is in your PATH)
        WebDriver driver = new ChromeDriver();

        try {
            // Wait object to implicitly tell the script how long to wait until a certain element is visible or available to proceed
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            // Maximize the window
            driver.manage().window().maximize();

            // Navigate to the registration URL
            driver.get("http://127.0.0.1:8000/register");

            // Locate the registration fields and enter data
            WebElement nameField = driver.findElement(By.id("name"));
            WebElement emailField = driver.findElement(By.id("email"));
            WebElement phoneField = driver.findElement(By.id("phone"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement confirmPasswordField = driver.findElement(By.id("password-confirm"));

            nameField.sendKeys("Sachith Maduranga");
            emailField.sendKeys("sachithmaduranga2001@gmail.com");
            phoneField.sendKeys("0712345678"); // Replace with a valid phone number
            passwordField.sendKeys("12345678");
            confirmPasswordField.sendKeys("12345678");

            // Locate the register button and click it to submit the form
            WebElement registerButton = driver.findElement(By.cssSelector("button[type='submit']"));
            registerButton.click();

            // Wait until the successful registration redirect occurs and verify the landing page
            WebElement expectedElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dashboard"))); // Replace with an actual element ID on the landing page after registration

            // Verify successful registration
            String landingPageContent = expectedElement.getText(); // Replace with actual element ID and verification logic
            System.out.println("Successfully registered, landing page content: " + landingPageContent);

        } catch (Exception e) {
            e.printStackTrace();
            // Optionally, print the current page source for debugging
            System.out.println(driver.getPageSource());
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
