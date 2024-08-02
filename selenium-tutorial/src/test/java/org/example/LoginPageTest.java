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

public class LoginPageTest {

    @Test
    public void testLogin() {
        // Set up the Chrome driver (ensure chromedriver is in your PATH)
        WebDriver driver = new ChromeDriver();

        try {
            // Wait object to implicitly tell the script how long to wait until a certain element is visible or available to proceed
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            // Maximize the window
            driver.manage().window().maximize();

            // Navigate to the login URL
            driver.get("http://127.0.0.1:8000/login");

            // Locate the username and password fields and enter credentials
            WebElement emailField = driver.findElement(By.id("email"));
            WebElement passwordField = driver.findElement(By.id("password"));

            emailField.sendKeys("sachithmaduranga2001@gmail.com"); // Replace with valid email
            passwordField.sendKeys("12345678"); // Replace with valid password

            // Locate the login button and click it to submit the form
            WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
            loginButton.click();

            // Wait until the successful login redirect occurs and verify the landing page
            WebElement expectedElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dashboard"))); // Replace with an actual element ID on the landing page after login

            // Verify successful login
            String landingPageContent = expectedElement.getText(); // Replace with actual element ID and verification logic
            System.out.println("Successfully logged in, landing page content: " + landingPageContent);

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
