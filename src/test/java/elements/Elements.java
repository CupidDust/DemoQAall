package elements;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class Elements {

	public static void main(String args[]) throws IOException

	{
		System.setProperty("webdriver.edge.driver", "C:\\Web Driver\\Edge Driver\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.get("https://demoqa.com/");
		driver.manage().window().maximize();
		System.out.println("Website reached");
		
		//initializing JavaScript Executor
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		/*
		 * Scroll directly to that element Hence storing the element
		 */
		
		WebElement elementsHomePage = driver.findElement(By.xpath("//div[contains(@class,'card mt-4')]//h5[contains(text(),'Elements')]"));
		js.executeScript("arguments[0].scrollIntoView(true);", elementsHomePage);
		System.out.println("Scrolled");
		
		//tapping on Elements
		driver.findElement(By.xpath("//div[contains(@class,'card mt-4')]//h5[contains(text(),'Elements')]")).click();
//		driver.findElement(By.xpath("//div[@class='icon']/child::*[name()='svg'][@fill='currentColor']")).click();
		driver.findElement(By.xpath("//li[@id = 'item-1']/span[contains(text(), 'Check Box')]")).click();
		System.out.println("Clicked on Checkbox");
		
		driver.findElement(By.xpath("//button[@title = 'Toggle']/*[name()='svg']")).click();
		System.out.println("Home is expanded");
		
		
		WebElement homeCheckbox = driver.findElement(By.xpath("(//*[@class='rct-icon rct-icon-uncheck'])[1]"));
		if (homeCheckbox.isSelected())
		{
			System.out.println("Home Checkbox is selected");
		}
		
		else
		{
			homeCheckbox.click();
			System.out.println("Home checkBox is selected by automation");
		}
		
		
		
		
		
		
		
		
		
	}
}
