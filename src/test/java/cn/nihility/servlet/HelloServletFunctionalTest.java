package cn.nihility.servlet;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Hello Servlet Functional Test
 * @author muscari
 * @date 2019-05-29 13:29
 */
public class HelloServletFunctionalTest {

    private WebDriver driver;

    @BeforeClass
    public static void setUpClass() {
        ChromeDriverManager.getInstance(ChromeDriver.class).setup();
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }

    @Test
    public void sayHello() {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://localhost:8080/spring-web-gradle/servlet.jsp");

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.findElement(By.id("say-hello-text-input")).sendKeys("Dolly");
        driver.findElement(By.id("say-hello-button")).click();

        Assert.assertEquals("servlet response", driver.getTitle());
        Assert.assertEquals("Hello, Dolly!", driver.findElement(By.tagName("h2")).getText());

    }
}
