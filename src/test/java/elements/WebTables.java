package elements;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebTables {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		 int i;
			System.setProperty("webdriver.edge.driver", "C:\\Web Driver\\Edge Driver\\msedgedriver.exe");
			WebDriver driver = new EdgeDriver();
			driver.get("https://demoqa.com/");
			driver.manage().window().maximize();
			WebElement elementsText = driver.findElement(By.xpath("//h5[contains(text(), 'Elements')]"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true)", elementsText);
			System.out.println("Scroll to element success");
			elementsText.click();
			driver.findElement(By.xpath("//*[@id='item-3']//span[text()='Web Tables']")).click();
			System.out.println("Web Tables from left is clicked");
			Thread.sleep(5000);
			js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.cssSelector(".text-center")));
			WebElement tableDriver = driver.findElement(By.cssSelector(".web-tables-wrapper"));
		 
		 List<WebElement> rows = tableDriver.findElements(By.cssSelector(".rt-tr-group"));
		 for(i=0; i<rows.size(); i++)
		 {
			 String rowText = rows.get(i).getText().trim();
			 if(!rowText.isEmpty())
			 {
				 System.out.println(rowText);
			 }
				/*
				 * else if(rowText.isEmpty()) { System.out.println("There are some empty rows");
				 * }
				 */
		 }
		 tableDriver.findElement(By.id("addNewRecordButton")).click();
		 
//		 Implicit wait to check the popup visibility
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 if(wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("registration-form-modal")))) != null)
		 System.out.println("popupDisplayed");
		 
		 driver.findElement(By.id("firstName")).sendKeys("1stName");
		 driver.findElement(By.id("lastName")).sendKeys("lastName");
		 driver.findElement(By.id("userEmail")).sendKeys("useremail@useremail.com");
		 driver.findElement(By.id("age")).sendKeys("18");
		 driver.findElement(By.id("salary")).sendKeys("7500");
		 driver.findElement(By.id("department")).sendKeys("tester");
		 driver.findElement(By.id("submit")).click();
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".text-center")));
		 System.out.println("WebTables middle of screen text displayed");
		 
		 System.out.println("New added Data");
//		 iterate through all rows to get newly added data
		 List<WebElement> newDataRows = driver.findElements(By.cssSelector(".rt-tr")); 
		 for(i = 0; i< newDataRows.size(); i++)
		 {
			 if(!newDataRows.get(i).getText().trim().isEmpty())
			 {
			 System.out.println(newDataRows.get(i).getText());
			 }
			 else
			 {
				 System.out.println("Row is empty");
			 }
		 }
	}

}
