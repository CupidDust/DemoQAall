package Interactions;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

public class Sortable 
{
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		System.setProperty("webdriver.edge.driver", "C:\\Web Driver\\Edge Driver\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.get("https://demoqa.com/sortable");
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.cssSelector(".text-center")));
		
		List<WebElement> allList = driver.findElements(By.xpath("//div[@class='vertical-list-container mt-4']/div[@class='list-group-item list-group-item-action']"));
		Actions a = new Actions(driver);
		for(WebElement list : allList)
		{
			if(list.getText().equalsIgnoreCase("One"))
			{
				a.clickAndHold(list)
				.moveToElement(driver.findElement(By.xpath("//div[@id='demo-tabpane-list']//div[contains(text(),'Three')]")))
				.release()
				.build()
				.perform();
				
//				Taking screenshot
				File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				try
				{
					File dest = new File("./Screenshots/drag1to3.png");
					dest.getParentFile().mkdir();
					FileUtils.copyFile(src, dest);
					System.out.println("scrnsht captured");
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
				break;
			}
		}
	}

}
