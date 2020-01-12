import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;


public class MailTestsChrome {
    private WebDriver driver;
    private MailMainPage mainPage;
    private MailLoggedPage loggedPage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\arisa\\IdeaProjects\\MavenProjectTest1\\src\\main\\resources\\drivers\\chromedriver64.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://mail.ru/");
    }

    @AfterMethod
    public void closeDriver() {
        driver.close();
    }

    @Test(description = "Main test title")
    public void mainTitleTest() {
        Assert.assertEquals(driver.getTitle(), "Mail.ru: почта, поиск в интернете, новости, игры");
    }

    @Test(description = "Testing login on main page")
    public void testLoginToMail() {
        mainPage = new MailMainPage(driver);
        mainPage.loggingToMail("testlasttest", "fhfh3864");
        Assert.assertEquals(driver.getTitle().contains("Входящие - Почта Mail.ru"), mainPage.toString().contains("Входящие - Почта Mail.ru"));
    }

    @Test(description = "Checking send a message")
    public void testSendingMessage() {
        loggedPage = new MailLoggedPage(driver);
        mainPage = new MailMainPage(driver);

        loggedPage = mainPage.loggingToMail("testlasttest", "fhfh3864");

        loggedPage.sendMessage("aris.arvin@gmail.com", "Проверка автосообщения", "текст для проверки автосообщения для тестирования");

        String text = driver.findElement(By.xpath("//a[@class='layer__link']/..")).getText();

        Assert.assertEquals(text, "Письмо отправлено");
    }

    @Test(description = "Checking counts of Sent messages")
    public void testCountOfSentMessages() {
        loggedPage = new MailLoggedPage(driver);
        mainPage = new MailMainPage(driver);

        int countOfSentMessagesBefore;

        loggedPage = mainPage.loggingToMail("testlasttest", "fhfh3864");

        countOfSentMessagesBefore = loggedPage.checkMailIsSent();

        loggedPage.sendMessage("aris.arvin@gmail.com", "Проверка автосообщения", "текст для проверки автосообщения для тестирования");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(loggedPage.checkMailIsSent(), countOfSentMessagesBefore+1);
    }
}
