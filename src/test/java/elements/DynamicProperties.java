package elements;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DynamicProperties {

	public static void main(String[] args) {
		System.setProperty("webdriver.edge.driver", "C:\\Web Driver\\Edge Driver\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.get("https://demoqa.com/dynamic-properties");
		driver.manage().window().maximize();

		WebElement middleText = driver.findElement(By.xpath("//div//h1[@class='text-center']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", middleText);

		// Color Change (Before)
		String beforeColorChange = driver.findElement(By.id("colorChange")).getCssValue("color");
		System.out.println("Before color change: " + beforeColorChange);

		// WebDriverWait instance
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Waiting until enableAfter button becomes clickable
		WebElement enabledAfterCTA = driver.findElement(By.id("enableAfter"));
		wait.until(ExpectedConditions.elementToBeClickable(enabledAfterCTA));

		// Waiting until visibleAfter button becomes visible (using By locator to avoid NoSuchElementException)
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("visibleAfter")));
		WebElement noCTA = driver.findElement(By.id("visibleAfter"));

		// Your original logic with &&
		if (enabledAfterCTA.isEnabled() && noCTA.isDisplayed()) {
			enabledAfterCTA.click();
			System.out.println("Clicked after CTA is enabled");
			System.out.println("Invisible CTA is now visible");
		} else {
			System.out.println("Something else is happening");
		}

		// Color Change (After)
		String afterColorChange = driver.findElement(By.id("colorChange")).getCssValue("color");
		System.out.println("After color change: " + afterColorChange);

		driver.quit();
	}
}
