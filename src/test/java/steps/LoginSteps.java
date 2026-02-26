package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class LoginSteps {

    private WebDriver driver;
    private String url = "https://www.saucedemo.com/";

    // Initialize WebDriver and open the browser
    @Before
    public void setup() {
        driver = new ChromeDriver();
    }

    // Step definition for "Given the user is on the login page"
    @Given("the user is on the login page")
    public void userOnLoginPage() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    // Step definition for "When the user enters valid username and password"
    @When("the user enters valid username and password")
    public void userEntersValidCredentials() {
        WebElement userName = this.driver.findElement(By.id("user-name"));
        userName.sendKeys("standard_user");

        WebElement passWord = this.driver.findElement(By.id("password"));
        passWord.sendKeys("secret_sauce");

        WebElement loginBtn = this.driver.findElement(By.id("login-button"));
        loginBtn.click();
    }

    // Step definition for "Then the user should be redirected to the homepage"
    @Then("the user should be redirected to the homepage")
    public void userRedirectedToHomepage() {
        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.equals("https://www.saucedemo.com/inventory.html");
    }

    // Cleanup after each test
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}