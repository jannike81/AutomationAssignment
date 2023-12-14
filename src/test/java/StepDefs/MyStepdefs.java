package StepDefs;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class MyStepdefs {
    private WebDriver driver;
    private String username;

    private WebElement element;

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("I'm using {string} as browser")
    public void iMUsingAsBrowser(String browser) {
        switch (browser) {
            case ("Chrome"):
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                driver = new ChromeDriver(chromeOptions);
                break;
            case ("Firefox"):
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case ("Edge"):
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                driver = new EdgeDriver(edgeOptions);
                break;
        }
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");

    }

    @And("I have typed in date of birth")
    public void iHaveTypedInDateOfBirth() {
        driver.findElement(By.cssSelector("#dp")).sendKeys("30/11/1981");
        //click down the calendar that pops-up
        Actions actions = new Actions(driver);
        actions.click().perform();
    }

    @And("I have typed in first name {string}")
    public void iHaveTypedInFirstName(String firstname) {
        driver.findElement(By.cssSelector("#member_firstname")).sendKeys(firstname);
    }

    @And("I have typed in last name {string}")
    public void iHaveTypedInLastName(String lastname) {
        driver.findElement(By.cssSelector("#member_lastname")).sendKeys(lastname);
    }

    @And("I have typed in email {string}")
    public void iHaveTypedInEmail(String email)  {
        getRandomUsername(); //generate a random username and save it to username
        driver.findElement(By.cssSelector("#member_emailaddress")).sendKeys(username + email);
    }

    @And("I have typed in confirmation of email {string}")
    public void iHaveTypedInConfirmationOfEmail(String emailConfirm) {
        driver.findElement(By.cssSelector("#member_confirmemailaddress")).sendKeys(username + emailConfirm);
    }

    @And("I have typed in password {string}")
    public void iHaveTypedInPassword(String password) {
        driver.findElement(By.cssSelector("#signupunlicenced_password")).sendKeys(password);
    }

    @And("I have typed in confirmation of password {string}")
    public void iHaveTypedInConfirmationOfPassword(String confirmPassword) {
        driver.findElement(By.cssSelector("#signupunlicenced_confirmpassword")).sendKeys(confirmPassword);
    }

    @And("I have {string} the terms and conditions box")
    public void iHaveCheckedTheTermsAndConditionsBox(String action) {
        if (action.equals("check")) {
            driver.findElement(By.cssSelector("label[for='sign_up_25']")).click();
        }

    }

    @And("I have checked the aged over 18 box")
    public void iHaveCheckedTheAgedOverBox() {
        driver.findElement(By.cssSelector("label[for='sign_up_26']")).click();

    }

    @And("I have checked the ethics and conduct box")
    public void iHaveCheckedTheEthicsAndConductBox() {
        driver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct']")).click();
    }

    @When("I click on the Confirm and join button")
    public void iClickOnTheConfirmAndJoinButton() {
        //driver.findElement(By.cssSelector("input[name='join']")).click();
        click(driver, By.cssSelector("input[name='join']"));

    }

    @Then("I get a registration confirmation text saying {string}")
    public void iGetARegistrationConfirmationText(String expected) {
        String actual = driver.findElement(By.cssSelector("div.page-content-wrapper > div > h2")).getText();
        assertEquals(expected, actual);
    }

    @Then("I get a text saying {string}")
    public void iGetAnErrorSaying(String expected) {
        String actual;

        switch (expected) {
            case ("Last Name is required"):
                actual = driver.findElement(By.cssSelector(".row:nth-child(6) .warning > span")).getText();
                assertEquals(expected, actual);
                break;
            case ("Password did not match"):
                actual = driver.findElement(By.cssSelector(".row:nth-child(2) .warning > span")).getText();
                assertEquals(expected, actual);
                break;
            case ("You must confirm that you have read and accepted our Terms and Conditions"):
                actual = driver.findElement(By.cssSelector(".warning > span")).getText();
                assertEquals(expected, actual);
                break;
            case ("THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND"):
                element = driver.findElement(By.cssSelector("div.page-content-wrapper > div > h2"));
                actual = waitForText(driver, element);
                assertEquals(expected, actual);
                break;

        }
    }

    private void getRandomUsername() {
        int min = 1;
        int max = 1000;
        int range = max - min + 1;
        int rand = (int) (Math.random() * range) + min;
        username = "jannikeTest" + rand;

    }

    private static void click(WebDriver driver, By by) {
        (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.elementToBeClickable(by));

        driver.findElement(by).click();
    }

    private String waitForText(WebDriver driver, WebElement element) {
        (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.textToBePresentInElement(element, "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND"));

        return element.getText();
    }
}

