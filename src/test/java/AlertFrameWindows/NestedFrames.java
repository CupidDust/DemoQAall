package AlertFrameWindows;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class NestedFrames {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		System.setProperty("webdriver.edge.driver", "C:\\Web Driver\\Edge Driver\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.get("https://demoqa.com/nestedframes");
		driver.manage().window().maximize();
		WebElement frameHeading = driver.findElement(By.cssSelector(".text-center"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", frameHeading);
		System.out.println("Scroll successful");
		
		System.out.println(driver.findElement(By.xpath("//div[@id='framesWrapper']//div[contains(text(),'Sample Nested')]")).getText());
		
		WebElement parentFrame = driver.findElement(By.id("frame1"));
		
		driver.switchTo().frame(parentFrame);
		
		System.out.println("parent Frame text: "+driver.findElement(By.tagName("body")).getText());
		
		WebElement childFrame = driver.findElement(By.xpath("//iframe[@srcdoc='<p>Child Iframe</p>']"));

		
		driver.switchTo().frame(childFrame);
		System.out.println(driver.findElement(By.tagName("body")).getText());
				
	}

}
