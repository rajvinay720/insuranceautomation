package com.insurance.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class InsuranceSteps {
    WebDriver driver;
    private static String generatedEmail;
    private static String generatedPassword;

    public static String generateRandomEmail() {
        return "user" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";
    }

    public static String generateRandomPassword() {
        return "Pass@" + UUID.randomUUID().toString().substring(0, 6);
    }

    public static void logCredentialsToFile(String userType, String email, String password) {
        try (FileWriter writer = new FileWriter("test-reports/credentials.log", true)) {
            writer.write(userType + " Registered: " + email + " | Password: " + password + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Given("User is on the insurance portal homepage")
    public void user_is_on_homepage() {
        System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000");
    }

    
    @When("User navigates to {string} and registers with {string} and {string}")
    public void user_navigates_to_and_registers_with_and(String userType, String email, String password) {
        driver.findElement(By.xpath("//button[contains(text(), 'Register')]")).click();
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("//button[contains(text(), 'Register')]")).click();
        
        System.out.println(userType + " Registered: " + email + " | Password: " + password);
        logCredentialsToFile(userType, email, password);
    }
	/*
	 * @When("User navigates to {string} and registers with {string} and {string}")
	 * public void user_navigates_to_and_registers_with_and(String userType, String
	 * string2, String string3) { generatedEmail = generateRandomEmail();
	 * generatedPassword = generateRandomPassword();
	 * 
	 * driver.findElement(By.xpath("//button[contains(text(), 'Register')]")).click(
	 * ); driver.findElement(By.name("email")).sendKeys(generatedEmail);
	 * driver.findElement(By.name("password")).sendKeys(generatedPassword);
	 * driver.findElement(By.xpath("//button[contains(text(), 'Submit')]")).click();
	 * 
	 * System.out.println(userType + " Registered: " + generatedEmail +
	 * " | Password: " + generatedPassword); logCredentialsToFile(userType,
	 * generatedEmail, generatedPassword); }
	 */    //=========
	/*
	 * @When("User navigates to {string} and registers with random credentials")
	 * public void user_registers(String userType) {
	 * 
	 * }
	 */

    
    @When("User logs in as {string} with {string} and {string}")
    public void user_logs_in_as_with_and(String userType, String string2, String string3) {
    	
    	 driver.findElement(By.xpath("//button[contains(text(), '" + userType + " Login')]")).click();
         driver.findElement(By.name("email")).sendKeys(generatedEmail);
         driver.findElement(By.name("password")).sendKeys(generatedPassword);
         driver.findElement(By.xpath("//button[contains(text(), 'Login')]")).click();
    	
    }
    
    
    

//====
    
	/*
	 * @When("User logs in as {string} with generated credentials") public void
	 * user_logs_in(String userType) {
	 * driver.findElement(By.xpath("//button[contains(text(), '" + userType +
	 * " Login')]")).click();
	 * driver.findElement(By.name("email")).sendKeys(generatedEmail);
	 * driver.findElement(By.name("password")).sendKeys(generatedPassword);
	 * driver.findElement(By.xpath("//button[contains(text(), 'Login')]")).click();
	 * }
	 */
    @When("Agent creates a new policy with {string} and {string}")
    public void agent_creates_policy(String annuitantName, String amount) {
        driver.findElement(By.xpath("//button[contains(text(), 'Create Policy')]")).click();
        driver.findElement(By.name("annuitant")).sendKeys(annuitantName);
        driver.findElement(By.name("amount")).sendKeys(amount);
        driver.findElement(By.xpath("//button[contains(text(), 'Submit')]")).click();
    }

    @Then("Admin should see the policy for {string} in the list")
    public void admin_sees_policy(String annuitantName) {
    	
    	annuitantName="John Doe";
        WebElement policyRow = driver.findElement(By.xpath("//td[contains(text(), '" + annuitantName + "')]"));
        Assert.assertNotNull(policyRow);
    }

    @Then("Close the browser")
    public void close_browser() {
        driver.quit();
    }
}
