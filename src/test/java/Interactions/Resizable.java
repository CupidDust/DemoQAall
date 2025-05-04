package Interactions;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

public class Resizable 
{
	public static void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stub
		System.setProperty("webdriver.edge.driver", "C:\\Web Driver\\Edge Driver\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.get("https://demoqa.com/resizable");
		driver.manage().window().maximize();
		scroll(driver, driver.findElement(By.cssSelector(".text-center")));
		screenshot(driver);
		String values = driver.findElement(By.id("resizableBoxWithRestriction")).getDomAttribute("style");
		String splitValue[] = values.split(";");
		int initialWidth = Integer.parseInt(splitValue[0].replaceAll("[^0-9]", "").trim());
		int initialHeight = Integer.parseInt(splitValue[1].replaceAll("[^0-9]", "").trim());
		int expectedWidth = 200;
		int expectedHeight = 200;
		int xOffSet = expectedWidth - initialWidth;
		int yOffSet = expectedHeight - initialHeight;
		WebElement resizeHolder = driver.findElement(By.xpath("//div[@id='resizableBoxWithRestriction']//span[contains(@class, 'react')]"));
		Actions a = new Actions(driver);
		a.clickAndHold(resizeHolder)
		.moveByOffset(xOffSet, yOffSet)
		.release(resizeHolder)
		.build()
		.perform();
		screenshot(driver);
		int finalWidth = Integer.parseInt(splitValue[0].replaceAll("[^0-9]", "").trim());
		int finalHeight = Integer.parseInt(splitValue[1].replaceAll("[^0-9]", "").trim());
		if(finalWidth==expectedWidth && finalHeight==expectedHeight)
		{
			System.out.println("both width or height is reached");
		}
		else
		{
			System.out.println("200 not attained");
		}
		
	}
	private static void scroll(WebDriver driver, WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}
	private static void screenshot(WebDriver driver) throws IOException
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String fileName = System.currentTimeMillis()+".png";
		File dest = new File("./Screenshots/"+fileName);
		dest.getParentFile().mkdirs();
		FileUtils.copyFile(src, dest);
		System.out.println("SS stored:"+dest.getAbsolutePath());
	}
}
