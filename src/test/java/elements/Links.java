package elements;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Links {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.edge.driver", "C:\\Web Driver\\Edge Driver\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.get("https://demoqa.com/links");
		driver.manage().window().maximize();
		
		WebElement linksText = driver.findElement(By.xpath("//h1[text()='Links']"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", linksText);
		
//		Click on the HomeLink
		driver.findElement(By.id("simpleLink")).click();
		Set <String> windowHandles = driver.getWindowHandles();
		Iterator<String> it = windowHandles.iterator();
		
		String parentWindow = it.next();
		String childWindow = it.next();
		
		System.out.println("1st Link:"+" "+driver.switchTo().window(parentWindow).getCurrentUrl());
		System.out.println("2nd Link:"+" "+driver.switchTo().window(childWindow).getCurrentUrl());
		
//		Switching back to parent window from Child Window
		driver.switchTo().window(parentWindow);
		
		
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement link = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dynamicLink")));
		link.click();
		
		Set <String> newWindowHandles = driver.getWindowHandles();
		Iterator<String> newIt = windowHandles.iterator();
		String newParentWindow = newIt.next();
		String grandChildWindow = newIt.next();
		System.out.println("3rd link:"+" "+driver.switchTo().window(grandChildWindow).getCurrentUrl());
		
	}

}
