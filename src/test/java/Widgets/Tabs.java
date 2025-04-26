package Widgets;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class Tabs 
{
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		System.setProperty("webdriver.edge.driver", "C:\\Web Driver\\Edge Driver\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.get("https://demoqa.com/tabs");
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.cssSelector(".text-center")));
		
		int tappableTabs = 0;
		int nonTappableTabs = 0;
		List<WebElement> tabs =  driver.findElements(By.xpath("//nav[@role='tablist']//a[@role='tab']"));
		int totalTabs = tabs.size();
		for(WebElement tab:tabs)
		{
			try
			{
				if(!tab.getDomAttribute("class").contains("disabled"))
				{
					tab.click();
					tappableTabs++;
					String tabName = tab.getDomAttribute("id").split("-")[2].trim();
					String tabState = tab.getDomAttribute("aria-selected");
					if(tabState.equalsIgnoreCase("true"))
					{
						String paraText = driver.findElement(By.xpath("//nav[@role='tablist']/following-sibling::div[@class='tab-content']//div[@id='demo-tabpane-"+tabName+"']//p")).getText();
						System.out.println(tabName+":"+"\n"+paraText);
					}
				}
				else
				{
					nonTappableTabs++;
					System.out.println("Disabled tab: "+tab.getDomAttribute("id"));
				}
			}
			catch(Exception e)
			{
				System.out.println("Error msg is: "+e.getMessage());
			}
		}

		System.out.println("Summary:");
		System.out.println("Total tabs: "+totalTabs);
		System.out.println("Tappable tabs: "+tappableTabs);
		System.out.println("Non Tappable tabs: "+nonTappableTabs);
	}
}
