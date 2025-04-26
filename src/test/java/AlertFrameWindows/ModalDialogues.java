package AlertFrameWindows;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class ModalDialogues {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		System.setProperty("webdriver.edge.driver", "C:\\Web Driver\\Edge Driver\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.get("https://demoqa.com/modal-dialogs");
		driver.manage().window().maximize();
		
		WebElement modalText = driver.findElement(By.xpath("//h1[@class='text-center']"));
//		Explicit Wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOf(modalText));
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", modalText);
		
		String instruction = driver.findElement(By.xpath("//div[contains(text(),'Click')]")).getText();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(instruction, "Click on button to see modal", "Text is incorrect, hence failed");
		softAssert.assertAll();
		
//		Small modal
		driver.findElement(By.id("showSmallModal")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal-content")));
		
		System.out.println(driver.findElement(By.xpath("//div[@id='example-modal-sizes-title-sm']")).getText());
		System.out.println(driver.findElement(By.xpath("//div[@class='modal-body']")).getText());
		driver.findElement(By.id("closeSmallModal")).click();
		
//		large modal
		driver.findElement(By.id("showLargeModal")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal-content")));
		
		System.out.println(driver.findElement(By.xpath("//div[@id='example-modal-sizes-title-lg']")).getText());
		System.out.println(driver.findElement(By.xpath("//div[@class='modal-body']")).getText());
		driver.findElement(By.xpath("//button[@type='button']/span[@aria-hidden='true']")).click();
		

	}

}
