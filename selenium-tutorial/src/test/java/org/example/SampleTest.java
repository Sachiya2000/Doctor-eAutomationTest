package org.example;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SampleTest {

    @Test
    public void test() {
        // Initializing the WebDriver - Here we are going to open a Chrome web page
        WebDriver driver = new ChromeDriver();

        // Wait object to implicitly tell the script how long to wait until a certain element is visible or available to proceed
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Maximize the window
        driver.manage().window().maximize();

        // Navigate to the URL
        driver.get("https://www.saegis.ac.lk/");

        // Looking for the web element having the text "LIFE AT SAEGIS" and then perform the click function
        WebElement e = driver.findElement(By.linkText("LIFE AT SAEGIS"));
        e.click();

        // Wait until the "LIFE AT SAEGIS" menu item is visible
        wait.until(d -> d.findElement(By.className("entry-title")).isDisplayed());

        // Switch to the correct window if multiple windows are involved
        String originalWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        // Get the content of the LIFE AT SAEGIS page
        String studentLife = driver.findElement(By.xpath("//*[@id='content']/div/div[1]/section[1]/div/div/div/div/div")).getText();
        System.out.println("This is the LIFE AT SAEGIS page content:");
        System.out.println(studentLife);

        // Click on the "CONTACT US" link
        driver.findElement(By.linkText("CONTACT US")).click();

        // Wait until the "CONTACT US" section is visible
        wait.until(d -> d.findElement(By.className("elementor-heading-title")).isDisplayed());

        // Print the "CONTACT US" sections
        System.out.println("==========CONTACT US===========");
        List<WebElement> elements = new ArrayList<>(driver.findElements(By.xpath("/html/body/div[2]/section[2]/div[2]/div/div/section[2]/div/div")));
        for (WebElement element : elements) {
            System.out.println(element.getText());
            System.out.println("--------");
        }

        // Close the browser
        driver.quit();
    }
}
