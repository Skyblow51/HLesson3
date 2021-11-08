

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.util.concurrent.TimeUnit;

public class
CrmTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://crm.geekbrains.space/user/login");
        login(driver);

        createContact(driver);
        createProject(driver);
    }

    static void login(WebDriver driver) {
        WebElement element = driver.findElement(By.id("prependedInput")); //ищет один конкретный элемент
        element.sendKeys("Applanatest1");
        driver.findElement(By.id("prependedInput2")).sendKeys("Student2020!");
        driver.findElement(By.id("_submit")).click();
    }


    static void createContact(WebDriver driver) {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//a/span[.='Контрагенты']"))).build().perform();
        driver.findElement(By.xpath("//span[.='Контактные лица']")).click();
        driver.findElement(By.xpath("//a[.='Создать контактное лицо']")).click();
        driver.findElement(By.xpath("//input[@name='crm_contact[lastName]']")).sendKeys("test");
        driver.findElement(By.xpath("//input[@name='crm_contact[firstName]']")).sendKeys("test");
        driver.findElement(By.xpath("//span[.='Укажите организацию']")).click();
        driver.findElement(By.xpath("//div[text()='12323142342134']")).click();
        driver.findElement(By.xpath("//input[@name='crm_contact[jobTitle]']")).sendKeys("test");
        driver.findElement(By.xpath("//button[@data-action='{\"route\":\"crm_contact_view\",\"params\":{\"id\":\"$id\"}}']")).click();

    }

    static void createProject(WebDriver driver) throws InterruptedException {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//span[.='Проекты']/ancestor::a"))).build().perform();
        driver.findElement(By.xpath("//span[.='Мои проекты']")).click();
        driver.findElement(By.xpath("//a[.='Создать проект']")).click();
        driver.findElement(By.xpath("//input[contains(@id, 'crm_project_name-uid')]")).sendKeys("TestProject");
        driver.findElement(By.xpath("//span[.='Укажите организацию']")).click();
        driver.findElement(By.xpath("//div[.='12323142342134']")).click();
        Select selectDepartment = new Select(driver.findElement(By.xpath("//select[contains(@id, 'crm_project_businessUnit-uid')]")));
        selectDepartment.selectByVisibleText("Research & Development");
        Select selectCurator = new Select(driver.findElement(By.name("crm_project[curator]")));
        selectCurator.selectByVisibleText("Ким Юрий");
        Select selectBoss = new Select(driver.findElement(By.name("crm_project[rp]")));
        selectBoss.selectByVisibleText("Ямпольский Михаил");
        Select selectManager = new Select(driver.findElement(By.name("crm_project[manager]")));
        selectManager.selectByVisibleText("Прохорова Екатерина");
        driver.findElement(By.xpath("//button[@data-action='{\"route\":\"crm_project_view\",\"params\":{\"id\":\"$id\"}}']")).click();
        Thread.sleep(5000);
        driver.quit();
    }

}
