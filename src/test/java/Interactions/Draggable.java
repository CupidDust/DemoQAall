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

public class Draggable {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new EdgeDriver();
		driver.get("https://demoqa.com/dragabble");
		driver.manage().window().maximize();
		scroll(driver, driver.findElement(By.cssSelector(".text-center")));
		screenshot(driver);
		driver.findElement(By.id("draggableExample-tab-cursorStyle")).click();
		WebElement centreCursor = driver.findElement(By.id("cursorCenter"));
		WebElement topLeftCursor = driver.findElement(By.id("cursorTopLeft"));
		WebElement bottomCursor = driver.findElement(By.id("cursorBottom"));

		Actions actions = new Actions(driver);

		// Move to centreCursor and get updated cursor
		actions.moveToElement(centreCursor).perform();
		Thread.sleep(500); // allow effect to apply
		System.out.println("Center Cursor: " + centreCursor.getCssValue("cursor"));

		// Move to topLeftCursor
		actions.moveToElement(topLeftCursor).perform();
		Thread.sleep(500);
		System.out.println("Top Left Cursor: " + topLeftCursor.getCssValue("cursor"));

		// Move to bottomCursor
		actions.moveToElement(bottomCursor).perform();
		Thread.sleep(500);
		System.out.println("Bottom Cursor: " + bottomCursor.getCssValue("cursor"));
		
	}
	
	private static void scroll(WebDriver driver, WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}
	
	private static void screenshot(WebDriver driver) throws IOException
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest = new File("./Screenshots/"+System.currentTimeMillis()+".png");
		dest.getParentFile().mkdirs();
		FileUtils.copyFile(src, dest);
	}

}
