package Widgets;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Slider {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		System.setProperty("webdriver.edge.driver", "C:\\Web Driver\\Edge Driver\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.get("https://demoqa.com/slider");
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath("//form//h1")));
		System.out.println("Scroll successful");
		
		WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));
		WebElement sliderValue = driver.findElement(By.id("sliderValue"));
		int currentValue = Integer.parseInt(sliderValue.getDomAttribute("value"));
		System.out.println("CurrentValue: "+ currentValue);
		int desiredValue = 79;
		
		int sliderWidth = slider.getSize().getWidth();
		int xOffset = ((desiredValue * sliderWidth) / 100) - (sliderWidth / 2) - 2;
		Actions a = new Actions(driver);
		a.moveToElement(slider).clickAndHold().moveByOffset(xOffset, 0).release().build().perform();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='range']")));
		String newValue = sliderValue.getDomProperty("value");
		System.out.println("NewValue: "+ newValue);
		
	}
	

}
