package Widgets;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class SelectMenu 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		System.setProperty("webdriver.edge.driver", "C:\\Web Driver\\Edge Driver\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.get("https://demoqa.com/select-menu");
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.cssSelector(".text-center")));
		driver.findElement(By.xpath("//div[@id='withOptGroup']//*[@class='css-19bqh2r']")).click();
		driver.findElement(By.xpath("//div[contains(@class,'css-26l3qy-menu')]//div[contains(@class,'option') and contains(text(),'Group 1, option 1')]")).click();
		
		js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath("//div[@class='row']/div/p/b[contains(text(),'Standard')]")));
		WebElement multiSelect = driver.findElement(By.xpath("//div[@class='col-md-6 col-sm-12']/*[@id='cars']"));
		Select select = new Select(multiSelect);
		Actions a = new Actions(driver);
		a.keyDown(Keys.CONTROL)
		.click(select.getOptions().get(0))
		.click(select.getOptions().get(3))
		.keyUp(Keys.CONTROL)
		.build()
		.perform();
		
		if(select.getOptions().get(0).isSelected() && select.getOptions().get(3).isSelected())
		{
			System.out.println("Displayed");
		}
		else
			System.out.println("Not Displayed");
		
	}

}
