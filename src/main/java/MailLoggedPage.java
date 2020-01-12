import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MailLoggedPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public MailLoggedPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    private By newMessageButton = By.cssSelector("span[data-title-shortcut = \"N\"]");

    private By appLoader = By.id("app-loader");

    private By addressField = By.xpath("/html/body/div[16]/div[2]/div/div[1]/div[2]/div[3]/div[2]/div/div/div[1]/div/div[2]/div/div/label/div/div/input");

    private By subjectField = By.xpath("/html/body/div[15]/div[2]/div/div[1]/div[2]/div[3]/div[3]/div[1]/div[2]/div/input");

    private By textField = By.xpath("/html/body/div[15]/div[2]/div/div[1]/div[2]/div[3]/div[5]/div/div/div[2]/div[1]");

    private By sendButton = By.xpath("/html/body/div[15]/div[2]/div/div[2]/div[1]/span[1]");

    private By sentMessagesButton = By.xpath("//a[@href=\"/sent/\"]");

    private By logOutButton = By.xpath("//*[@id=\"PH_logoutLink\"]");


    public MailLoggedPage clickToNewMessageButton() {
        WebElement app = driver.findElement(appLoader);
        wait.until(ExpectedConditions.invisibilityOf(app));
        driver.findElement(newMessageButton).click();
        return this;
    }

    public MailLoggedPage clickToAddressField(String email) {
        wait.until(ExpectedConditions.presenceOfElementLocated(addressField));
        driver.findElement(addressField).sendKeys(email);
        return this;
    }

    public MailLoggedPage clickToSubjectField(String subject) {
        wait.until(ExpectedConditions.presenceOfElementLocated(subjectField));
        driver.findElement(subjectField).sendKeys(subject);
        return this;
    }

    public MailLoggedPage clickToTextField(String text) {
        wait.until(ExpectedConditions.presenceOfElementLocated(textField));
        driver.findElement(textField).sendKeys(text);
        return this;
    }

    public MailLoggedPage clickToSendButton() {
        wait.until(ExpectedConditions.presenceOfElementLocated(sendButton));
        driver.findElement(sendButton).click();
        return this;
    }

    public MailLoggedPage sendMessage(String email, String title, String text) {

        clickToNewMessageButton();
        clickToAddressField(email);
        clickToSubjectField(title);
        clickToTextField(text);
        clickToSendButton();
        return new MailLoggedPage(driver);
    }

    public int checkMailIsSent() {
        String title;
        int countsOfMessages;
        wait.until(ExpectedConditions.visibilityOfElementLocated(sentMessagesButton));
        title = driver.findElement(sentMessagesButton).getAttribute("title");
        String result = title.replaceAll("[^0-9]", "");
        countsOfMessages = Integer.parseInt(result);
        System.out.println(countsOfMessages);
        return countsOfMessages;
    }
}
