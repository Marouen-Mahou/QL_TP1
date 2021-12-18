package tp3;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AppTest {
    
    public static WebDriver driver;
    
    @BeforeAll
    public static void setup() {
        System.setProperty("webdriver.chrome.driver","F:\\chromedriver.exe");
    }
    
    @BeforeEach
    public static void prepare(){
        driver = new ChromeDriver();
    }
    
    @Test
    public void testTodoWebSite() {
        //Open URL
        driver.get("https://www.todomvc.com/");
        
        //Select Language
        selectLanguage("Backbone.js");
        
        //Add todos
        addTodo("Meet a Friend");
        addTodo("Buy meat");
        addTodo("Wash car");
        
        //Check input
        driver.findElement(By.cssSelector("body > section > section > ul > li:nth-child(1) > div > input")).click();
        
        //Get Item number
        WebElement count = driver.findElement(By.cssSelector("body > section > footer > span > strong"));
        
        //Test
        assertEquals("2",count.getText());
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"Backbone.js","Dojo","Vue.js"})
    public void testTodoWebSite(String language) {
        //Open URL
        driver.get("https://www.todomvc.com/");
        
        //Select Language
        selectLanguage(language);
        
        //Add todos
        addTodo("Meet a Friend");
        addTodo("Buy meat");
        addTodo("Wash car");
        
        //Check input
        driver.findElement(By.cssSelector("body > section > section > ul > li:nth-child(1) > div > input")).click();
        
        //Get Item number
        WebElement count = driver.findElement(By.cssSelector("body > section > footer > span > strong"));
        
        //Test
        assertEquals("2",count.getText());
    }
    
    public void selectLanguage(String language){
        driver.findElement(By.linkText(language)).click();
    }
    
    public void addTodo(String todo){
        WebElement input = driver.findElement(By.xpath("/html/body/section/header/input"));
        input.sendKeys(todo);
        input.sendKeys(Keys.ENTER);
        input.clear();
    }
    
    @AfterEach
    public static void teardown() {
        driver.quit();
    }
}
