package tp3;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class App {
    
    public static WebDriver driver;
    
    public static void selectLanguage(String language){
        driver.findElement(By.linkText(language)).click();
    }
    
    public static void addTodo(String todo){
        WebElement input = driver.findElement(By.xpath("/html/body/section/header/input"));
        input.sendKeys(todo);
        input.sendKeys(Keys.ENTER);
        input.clear();
    }
    
     public static void main( String[] args )
    {

        System.setProperty("webdriver.chrome.driver","F:\\chromedriver.exe");
        driver = new ChromeDriver();
    	
        String baseUrl = "https://www.todomvc.com/";
        
        driver.get(baseUrl);
        
        //Select Language
        selectLanguage("Backbone.js");
        
        //Add todos
        addTodo("Meet a Friend");
        addTodo("Buy meat");
        addTodo("Wash car");
        
        //Check Todo
        driver.findElement(By.cssSelector("body > section > section > ul > li:nth-child(2) > div > input")).click();
        
        WebDriverWait myWaitVar = new WebDriverWait(driver, 10);

        WebElement count = driver.findElement(By.cssSelector("body > section > footer > span > strong"));

        System.out.println(myWaitVar.until(ExpectedConditions.textToBePresentInElement(count, "2")));
        
        driver.close();
    }
}
