package AlertFrameWindows;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class BrowserWindows {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		System.setProperty("webdriver.edge.driver", "C:\\Web Driver\\Edge Driver\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.get("https://demoqa.com/browser-windows");
		driver.manage().window().maximize();
		WebElement browserWindowText = driver.findElement(By.xpath("//div[@id='browserWindows']//h1[contains(text(),'Browser')]"));
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", browserWindowText);
		
//		New tab button click
		driver.findElement(By.id("tabButton")).click();
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		
		String parentWindow = it.next();
		String childWindow = it.next();
		
		driver.switchTo().window(childWindow);
		String urlNewWindow = driver.getCurrentUrl();
		System.out.println(urlNewWindow);
		driver.switchTo().window(parentWindow);
		
		String parentWindow2 = driver.getWindowHandle();
		driver.findElement(By.id("windowButton")).click();
		Set<String> twoWindows = driver.getWindowHandles();
		for(String window : twoWindows)
		{
			if(!window.equals(parentWindow2))
			{
				driver.switchTo().window(window);
				System.out.println("The windows opened are:"+" "+driver.getCurrentUrl());
				
			}
		}
		
		driver.switchTo().window(parentWindow2);
		driver.findElement(By.id("messageWindowButton")).click();
		
		Set<String> allWindows = driver.getWindowHandles();
		for(String finalWindow : allWindows)
		{
			if(finalWindow.equals(parentWindow2))
			{
				driver.switchTo().window(finalWindow);
				System.out.println("The windows opened are:"+" "+driver.getCurrentUrl());
			}
		}
		
	}

}
