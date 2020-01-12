import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MailMainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public MailMainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    private By loginField = By.xpath("//input[@id='mailbox:login']");

    private By submitLoginButton = By.xpath("//*[@id=\"mailbox:submit\"]/input");

    private By passwordField = By.xpath("//*[@id=\"mailbox:password\"]");

    private By submitForm = By.xpath("//*[@id=\"mailbox:submit\"]/input");

    public MailMainPage clickToLoginField(String login) {
        wait.until(ExpectedConditions.presenceOfElementLocated(loginField));
        driver.findElement(loginField).sendKeys(login);
        return this;
    }

    public MailMainPage clickToSubmitLoginButton() {
        wait.until(ExpectedConditions.presenceOfElementLocated(submitLoginButton));
        driver.findElement(submitLoginButton).click();
        return this;
    }

    public MailMainPage clickToPasswordField(String password) {
        wait.until(ExpectedConditions.presenceOfElementLocated(passwordField));
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public MailMainPage clickToSubmitButton() {
        wait.until(ExpectedConditions.presenceOfElementLocated(submitForm));
        driver.findElement(submitForm).click();
        return this;
    }

    public MailLoggedPage loggingToMail(String login, String password) {
        clickToLoginField(login);
        clickToSubmitLoginButton();
        clickToPasswordField(password);
        clickToSubmitButton();
        return new MailLoggedPage(driver);
    }
}
