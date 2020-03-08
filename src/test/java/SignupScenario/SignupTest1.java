package SignupScenario;

import Util.ReadExcel;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SignupTest1 {
    public WebDriver driver;
    public Map<String, List<String>> excelData = new HashMap();
    public ReadExcel readExcel = new ReadExcel();
    @Test
    public void testSignup() throws Exception {
        Thread.sleep(8000);
        driver.findElement(By.className("navbar-toggler-icon")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("loginSignupBtn")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("signupButton")).click();
        Thread.sleep(2000);
        Map<String, List<String>> signupData = readExcel.readExcelData(excelData, "Signup");
        int cnt = 0;
        Select roleSelect = new Select(driver.findElement(By.id("formBasicUsername")));
        roleSelect.selectByVisibleText(signupData.get("Role").get(0));
        Thread.sleep(1000);
        WebElement usernameEle = driver.findElement(By.id("username"));
        usernameEle.sendKeys(signupData.get("Username").get(0));
        Thread.sleep(1000);
        WebElement emailEle = driver.findElement(By.id("email"));
        emailEle.sendKeys(signupData.get("Email").get(0));
        Thread.sleep(1000);
        WebElement dobEle = driver.findElement(By.id("dob"));
        dobEle.sendKeys(signupData.get("DOB").get(0));
        WebElement welcomeEle = driver.findElement(By.id("contained-modal-title-vcenter"));
        welcomeEle.click();

        Thread.sleep(1000);
        if (signupData.get("Gender").get(0).equals("Male")){
            WebElement maleEle = driver.findElement(By.id("maleRadio"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", maleEle);
        }
        else{
            WebElement femaleEle = driver.findElement(By.id("femaleRadio"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", femaleEle);
            System.out.println("Selection status: "+ femaleEle.isSelected());
        }
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement passwordEle = driver.findElement(By.id("password"));
        passwordEle.sendKeys(signupData.get("Password").get(0));
        Thread.sleep(1000);
        WebElement confirmPasswordEle = driver.findElement(By.id("confirm"));
        confirmPasswordEle.sendKeys(signupData.get("ConfirmPassword").get(0));
        Thread.sleep(1000);
        WebElement signupBtnEle = driver.findElement(By.id("signupBtn"));
        signupBtnEle.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        WebElement toast = driver.findElement(By.className("Toastify"));
        String msg = toast.getText();
        System.out.println("???");
        System.out.println("done signup");


    }
    @BeforeTest
    public void beforeTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://127.0.0.1:3000/#/");
    }


    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
