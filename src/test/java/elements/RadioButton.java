package elements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.edge.EdgeDriver;

public class RadioButton {

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
		driver.findElement(By.xpath("//li[@id = 'item-2']//span[text()='Radio Button']")).click();
		System.out.println("Radio button from left is clicked");
		Thread.sleep(5000);

		js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.cssSelector(".mb-3")));
		System.out.println("ScrollSuccess");
		Thread.sleep(5000);

		List<WebElement> listOfRadioButtons = driver.findElements(By.cssSelector(".custom-control-label"));
		 for (i = 0; i < listOfRadioButtons.size(); i++) 
		 {
			
			listOfRadioButtons.get(i).click();
			if (!listOfRadioButtons.get(i).isSelected()) {
				Thread.sleep(2000);
				System.out.println(listOfRadioButtons.get(i).isSelected());
//				listOfRadioButtons.get(i).click(); // Thread.sleep(2000);
//				System.out.println(listOfRadioButtons.getClass());
			}

			else {
				System.out.println("Issue");
			}
		
		}
		 
		

	}

}
