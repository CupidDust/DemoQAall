package Interactions;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;

public class Selectable 
{
	public static void main(String[] args) throws InterruptedException 
	{
		System.setProperty("webdriver.edge.driver", "C:\\Web Driver\\Edge Driver\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.get("https://demoqa.com/selectable");
		driver.manage().window().maximize();

		WebElement text = driver.findElement(By.cssSelector(".text-center"));
		Selectable s = new Selectable();		
		s.scrollMethod(driver, text);
		s.screenShot(driver);		

		driver.findElement(By.id("demo-tab-grid")).click();
		Thread.sleep(1000);

		List<WebElement> listOfRows = driver.findElements(By.xpath("//div[@id='gridContainer']/div[contains(@class,'list-group')]"));

		for (WebElement row : listOfRows) {
			List<WebElement> items = row.findElements(By.tagName("li"));

			for (WebElement item : items) {
				if (item.getText().equalsIgnoreCase("Nine")) {
					item.click();
					Thread.sleep(2000);
					if (item.getDomAttribute("class").contains("active")) {
						System.out.println("Item 'Nine' is clicked and active. SS is taken.");
						s.screenShot(driver);
					} else {
						System.out.println("Item 'Nine' is not active after click.");
					}
					break;
				}
			}
		}
		driver.quit();
	}

	public void scrollMethod(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}

	protected void screenShot(WebDriver driver) {
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			String fileName = "SS_" + System.currentTimeMillis() + ".png";
			File dest = new File("./Screenshots/" + fileName);
			dest.getParentFile().mkdirs();
			FileUtils.copyFile(src, dest);
			System.out.println("Screenshot captured: " + fileName);
			System.out.println("Screenshot saved at: " + dest.getAbsolutePath());
		} catch (Exception e) {
			System.out.println("Screenshot error: " + e.getMessage());
		}
	}
}
