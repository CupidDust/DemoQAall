package Interactions;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class Selectable 
{
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		System.setProperty("webdriver.edge.driver","C:\\Web Driver\\Edge Driver\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.get("https://demoqa.com/selectable");
		driver.manage().window().maximize();
		WebElement text = driver.findElement(By.cssSelector(".text-center"));
		/*
		 * JavascriptExecutor js = (JavascriptExecutor)driver;
		 * js.executeScript("arguments[0].scrollIntoView(true)", text);
		 */
		Selectable s = new Selectable();
		
		s.scrollMethod(driver, text);
		s.screenShot(driver);
				
	}
	public void scrollMethod(WebDriver driver, WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}
	
	protected void screenShot(WebDriver driver)
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try
		{
			String fileName = "SS"+System.currentTimeMillis()+".png";
			File dest = new File("./Screenshots/"+fileName);
			dest.getParentFile().mkdirs();
			FileUtils.copyFile(src, dest);
			System.out.println("Screenshot captured");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

}
