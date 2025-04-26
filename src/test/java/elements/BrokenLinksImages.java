package elements;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class BrokenLinksImages {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws MalformedURLException, IOException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.edge.driver", "C:\\Web Driver\\Edge Driver\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.get("https://demoqa.com/broken");
		driver.manage().window().maximize();
		WebElement validLink = driver.findElement(By.linkText("Click Here for Valid Link"));
		String hrefValidLink = validLink.getDomAttribute("href");
		try {
			HttpURLConnection connection = (HttpURLConnection) (new URI(hrefValidLink).toURL().openConnection());
			connection.setRequestMethod("GET");
			connection.connect();

			int validLinkResponseCode = connection.getResponseCode();
			if (validLinkResponseCode == 200) {
				System.out.println("Response Code is:" + " " + validLinkResponseCode);
			} else if (validLinkResponseCode == 301 || validLinkResponseCode == 302) {
				String newValidLinkLocation = connection.getHeaderField("location");
				System.out.println("New Location" + " " + newValidLinkLocation);
			} else {
				System.out.println("Response Code is:" + " " + validLinkResponseCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		WebElement brokenLink = driver.findElement(By.linkText("Click Here for Broken Link"));
		String brokenLinkAttribute = brokenLink.getDomAttribute("href");

		try 
		{
			HttpURLConnection connection2 = (HttpURLConnection)(new URI(brokenLinkAttribute).toURL().openConnection());
			connection2.setRequestMethod("GET");
			connection2.connect();
			if(connection2.getResponseCode()==400 || connection2.getResponseCode()==401)
			{
				System.out.println("Response Code is:"+" "+connection2.getResponseCode());
			}
			else 
			{
				System.out.println("Other Response Code is:"+" "+connection2.getResponseCode());
			}

		}

		catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement validImage = driver.findElement(By.xpath("//p[text()='Valid image']/following-sibling::img[contains(@src, 'Toolsqa.jpg')]"));
		String validImageURI = validImage.getDomAttribute("src");
		
		try
		{
			if(!validImageURI.startsWith("http"))
			{
				validImageURI = "https://demoqa.com" + validImageURI;
			}
			HttpURLConnection validImageConnection = (HttpURLConnection)(new URI(validImageURI).toURL().openConnection());
			validImageConnection.setRequestMethod("GET");
			validImageConnection.connect();
			if(validImageConnection.getResponseCode()==200 || validImageConnection.getResponseCode()==201)
			{
				System.out.println("Response Code:"+" "+validImageConnection.getResponseCode());
				System.out.println("Response Code:"+" "+validImageConnection.getResponseMessage());
			}
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

}
