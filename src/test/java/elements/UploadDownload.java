package elements;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UploadDownload {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		int timer = 10;
		boolean fileFound = false;
		// Set Edge options
		EdgeOptions options = new EdgeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("download.default_directory", "C:\\Users\\bandh\\Downloads");
		prefs.put("profile.default_content_setting_values.automatic_downloads", 1);
		options.setExperimentalOption("prefs", prefs);
		System.setProperty("webdriver.edge.driver","C:\\Web Driver\\Edge Driver\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver(options);
		driver.get("https://demoqa.com/upload-download");
		driver.manage().window().maximize();
		WebElement uploadDownloadText = driver.findElement(By.xpath("//h1[contains(text(), 'Upload')]"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", uploadDownloadText);
		System.out.println("Scroll success");
		
		
		
		driver.findElement(By.id("downloadButton")).click();
		
		File downloadedFile = new File("C:\\Users\\bandh\\Downloads\\sampleFile.jpeg");
		while (timer > 0)
		{
			if(downloadedFile.exists())
			{
				System.out.println("File is present");
				fileFound = true;
				break;				
			}
			System.out.println("⏳ Waiting for file to download... " + timer + "s left");
		    Thread.sleep(1000); // Wait 1 second before retrying
			timer--;
		}
		if(!fileFound)
		{
		System.out.println("Couldn't find the file");
		}
		
		
		//Upload file
		driver.findElement(By.id("uploadFile")).sendKeys("C:\\Users\\bandh\\Downloads\\sampleFile.jpeg");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement uploadFilePath = driver.findElement(By.id("uploadedFilePath"));
		wait.until(ExpectedConditions.visibilityOf(uploadFilePath));
		
		
		
//		Delete File
		if(uploadFilePath.isDisplayed())
		{
			downloadedFile.delete();
			System.out.println("File is deleted successfully");
		}
		else
		{
			System.out.println("Can't delete");
		}
		
		
	}
	
	


}
