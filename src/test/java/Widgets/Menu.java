package Widgets;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class Menu {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		System.setProperty("webdriver.edge.driver",  "C:\\Web Driver\\Edge Driver\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.get("https://demoqa.com/menu");
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.cssSelector(".text-center")));
		List<WebElement> mainItems = driver.findElements(By.xpath("//ul[@id='nav']/li"));
		
		String menuName;
		boolean subMenu;
		for(WebElement mainItem : mainItems)
		{
			menuName = mainItem.findElement(By.tagName("a")).getText();
			subMenu = mainItem.findElements(By.tagName("ul")).size()>0;
		
			if(subMenu)
			{
				System.out.println(menuName + ": subMenu present");
			}
			else
			{
				System.out.println(menuName + ": no subMenu");
			}	
		}
	}

}
