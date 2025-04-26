package AlertFrameWindows;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Alerts {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		System.setProperty("webdriver.edge.driver", "C:\\Web Driver\\Edge Driver\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.get("https://demoqa.com/alerts");
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath("//div//h1")));
		
		driver.findElement(By.xpath("//span[@class='mr-3']/ancestor::div[@class='row']//button[@id='alertButton']")).click();
		
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
		System.out.println("1st Alert accepted");
		
		driver.findElement(By.xpath("//span[@class='mr-3']/ancestor::div[@class='mt-4 row']//button[@id='timerAlertButton']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.alertIsPresent());
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
		
		if(driver.findElement(By.xpath("//span[contains(text(), 'after 5 seconds')]")).isDisplayed())
		{
			System.out.println("2nd alert text is working");
		}
		
//		3rd alert button
		driver.findElement(By.id("confirmButton")).click();
		driver.switchTo().alert().dismiss();
		
		if(driver.findElement(By.id("confirmResult")).getText().equalsIgnoreCase("you selected cancel"))
		{
			System.out.println(driver.findElement(By.id("confirmResult")).getText());
			driver.findElement(By.id("confirmButton")).click();
			driver.switchTo().alert().accept();
			if(driver.findElement(By.id("confirmResult")).getText().equalsIgnoreCase("you selected ok"))
			{
				System.out.println(driver.findElement(By.id("confirmResult")).getText());
				System.out.println("3rd alert working too completed");
			}
		}
		
		String alertTextInput = "testing";
		driver.findElement(By.xpath("//span[@class='mr-3']/ancestor::div//button[@id='promtButton']")).click();
		driver.switchTo().alert().sendKeys(alertTextInput);
		driver.switchTo().alert().accept();
		
		String alertTextOutput = driver.findElement(By.id("promptResult")).getText();
		
		if(alertTextOutput.equalsIgnoreCase("You entered "+ alertTextInput))
		{
			System.out.println("4th alert passed");
		}
		
	}

}
