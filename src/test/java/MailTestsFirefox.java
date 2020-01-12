import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MailTestsFirefox {

    private WebDriver driver;
    private MailMainPage mainPage;
    private MailLoggedPage loggedPage;

    @BeforeMethod
    public void setUpFirefox() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\arisa\\IdeaProjects\\MavenProjectTest1\\src\\main\\resources\\drivers\\geckodriver64.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://mail.ru/");
    }

    @AfterMethod
    public void closeGeckoDriver() {
        driver.close();
    }

    @Test(description = "Main test title")
    public void mainTitleTestInFirefox() {
        Assert.assertEquals(driver.getTitle(), "Mail.ru: почта, поиск в интернете, новости, игры");
    }

    @Test(description = "Testing login on main page")
    public void testLoginToMailInFirefox() {
        mainPage = new MailMainPage(driver);
        mainPage.loggingToMail("lasttestlast@mail.ru", "fhfh38643864");
        Assert.assertEquals(driver.getTitle().contains("Входящие - Почта Mail.ru"), mainPage.toString().contains("Входящие - Почта Mail.ru"));
    }

    @Test(description = "Checking send a message")
    public void testSendingMessageInFirefox() {
        loggedPage = new MailLoggedPage(driver);
        mainPage = new MailMainPage(driver);

        loggedPage = mainPage.loggingToMail("lasttestlast@mail.ru", "fhfh38643864");

        loggedPage.sendMessage("aris.arvin@gmail.com", "Проверка автосообщения", "текст для проверки автосообщения для тестирования");

        String text = driver.findElement(By.xpath("//a[@class='layer__link']/..")).getText();

        Assert.assertEquals(text, "Письмо отправлено");
    }

    @Test(description = "Checking counts of Sent messages in Firefox")
    public void testXCountOfSentMessagesInFirefox() {
        loggedPage = new MailLoggedPage(driver);
        mainPage = new MailMainPage(driver);

        int countOfSentMessagesBefore;

        loggedPage = mainPage.loggingToMail("lasttestlast@mail.ru", "fhfh38643864");

        countOfSentMessagesBefore = loggedPage.checkMailIsSent();

        loggedPage.sendMessage("aris.arvin@gmail.com", "Проверка автосообщения", "текст для проверки автосообщения для тестирования");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(loggedPage.checkMailIsSent(), countOfSentMessagesBefore + 1);
    }
}



