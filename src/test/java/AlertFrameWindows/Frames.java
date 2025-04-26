package AlertFrameWindows;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class Frames {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.edge.driver", "C:\\Web Driver\\Edge Driver\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.get("https://demoqa.com/frames");
		driver.manage().window().maximize();
		WebElement frameHeading = driver.findElement(By.cssSelector(".text-center"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", frameHeading);
		System.out.println("Scroll successful");
		
		WebElement parentFrame = driver.findElement(By.id("framesWrapper"));
		WebElement childFrame1 = driver.findElement(By.id("frame1"));
		WebElement childFrame2 = driver.findElement(By.id("frame2"));
		
		System.out.println(driver.findElement(By.xpath("//div[contains(text(),'Sample Iframe')]")).getText());
		
		driver.switchTo().frame(childFrame1);
		System.out.println("The 2nd frame text is: "+driver.findElement(By.xpath("//h1[@id='sampleHeading']")).getText());
		
		System.out.println("swiching 2nd frame");
		driver.switchTo().defaultContent();
		driver.switchTo().frame(childFrame2);
		System.out.println("The 2nd frame text is: "+driver.findElement(By.xpath("//h1[@id='sampleHeading']")).getText());
		
	}

}
