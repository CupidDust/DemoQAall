package elements;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

public class Buttons {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.edge.driver", "C:\\Web Driver\\Edge Driver\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		
//		Giving Implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.get("https://demoqa.com/buttons");
		driver.manage().window().maximize();
		
		
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath("//h1[text()='Buttons']")));
		System.out.println("Scrolling is successsful to Button middle text");
		
//		Actions class initialization
		Actions a = new Actions(driver);
		
//		Code to DoubleClick
		WebElement doubleClickBtn = driver.findElement(By.id("doubleClickBtn"));
		a.doubleClick(doubleClickBtn).perform();
		
//		after double click waiting for cnfrmtn msg
		
		if(driver.findElement(By.id("doubleClickMessage")).isDisplayed())
		{
			System.out.println("The Message shown is:"+" "+driver.findElement(By.id("doubleClickMessage")).getText());
		}
		else
		{
			System.out.println("error with double click");
		}
		
//		Code to rightClick
		WebElement rightClick = driver.findElement(By.id("rightClickBtn"));
		a.contextClick(rightClick).perform();
		
//		after rightClick waiting for cnfrmtn msg
		if(driver.findElement(By.id("rightClickMessage")).isDisplayed())
		{
			System.out.println("The Message shoown is:"+" "+driver.findElement(By.id("rightClickMessage")).getText());
		}
		else
		{
			System.out.println("error with right click");
		}
		
//		code for single click
		WebElement singleClick = driver.findElement(By.xpath("//button[text()='Click Me']"));
		a.click(singleClick).perform();
		
//		after singleClick waiting for cnfrmtnmsg
		if(driver.findElement(By.xpath("//button[text()='Click Me']")).isDisplayed())
		{
			System.out.println("The Message shown is:"+" "+driver.findElement(By.id("dynamicClickMessage")).getText());
		}
		else
		{
			System.out.println("Error with single click");
		}
	}

}
