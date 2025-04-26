package Forms;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PracticeForm {

	public static void main(String[] args) throws InterruptedException 
	{
		int i;
		String firstName = "Demo";
		String lastName = "QA";
		String email = "demoqa@demo.com";
		String phnNo = "5638592849";
		String yearDob = "1988";
		String monthDob = "February";
		String day = "1";
		String finalDay = String.format("%03d", Integer.parseInt(day)); // turns = 1 to 001
		String sub = "Co";
		String wantedSubjectName = "Commerce";
		String wantedHobbyName = "Music";
		String wantedGenderName = "Male";
		String currentAddress = "QAZEDC";
		String wantedState = "Rajasthan";
		String wantedCity = "Jaipur";

		System.setProperty("webdriver.edge.driver", "C:\\Web Driver\\Edge Driver\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.get("https://demoqa.com/automation-practice-form");
		driver.manage().window().maximize();

		WebElement practiceFormMiddleText = driver.findElement(By.xpath("//div//h1"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", practiceFormMiddleText);
		System.out.println("Scroll success");

		driver.findElement(By.id("firstName")).sendKeys(firstName);
		driver.findElement(By.id("lastName")).sendKeys(lastName);
		driver.findElement(By.id("userEmail")).sendKeys(email);

		// Gender
		List<String> allGender = new ArrayList<>();
		String selectedGenderName = " ";
		List<WebElement> listGenderButtons = driver.findElements(By.cssSelector(".custom-control.custom-radio.custom-control-inline"));
		for (WebElement genderButton : listGenderButtons) {
			WebElement tagNameGender = genderButton.findElement(By.tagName("label"));
			String genderName = tagNameGender.getText();
			allGender.add(genderName);

			if (genderName.equalsIgnoreCase(wantedGenderName)) {
				tagNameGender.click();
				selectedGenderName = genderName;
			}
		}
		System.out.println("All gender Names: " + allGender);
		System.out.println("Selected gender name: " + selectedGenderName);

		driver.findElement(By.id("userNumber")).sendKeys(phnNo);

		// DOB
		driver.findElement(By.id("dateOfBirthInput")).click();
		WebElement yearDropDown = driver.findElement(By.cssSelector(".react-datepicker__year-select"));
		Select yearDrop = new Select(yearDropDown);
		yearDrop.selectByVisibleText(yearDob);

		WebElement monthDropDown = driver.findElement(By.cssSelector(".react-datepicker__month-select"));
		Select monthDrop = new Select(monthDropDown);
		monthDrop.selectByVisibleText(monthDob);

		driver.findElement(By.xpath("//div[@class='react-datepicker__day react-datepicker__day--" + finalDay + "']")).click();
		System.out.println("DOB selected");

		Thread.sleep(2000);
		WebElement dynamicTextBox = driver.findElement(By.id("subjectsInput"));
		dynamicTextBox.click();
		dynamicTextBox.sendKeys(sub);
		System.out.println("subjects dropdown visible");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".subjects-auto-complete__option")));
		System.out.println("wait succeeded");

		List<WebElement> suggestions = driver.findElements(By.cssSelector(".subjects-auto-complete__option"));
		for (WebElement subject : suggestions) {
			String subjectName = subject.getText().trim();
			System.out.println("the subjects are: " + subjectName);
			if (subjectName.equalsIgnoreCase(wantedSubjectName)) {
				subject.click();
				System.out.println("Selected: " + subjectName);
				break;
			}
		}

		// Hobbies
		String selectedHobby = "";
		List<String> allHobbies = new ArrayList<>();
		List<WebElement> hobbiesRadioButtons = driver.findElements(By.cssSelector(".custom-control.custom-checkbox.custom-control-inline"));
		for (WebElement hobbiesRadioButton : hobbiesRadioButtons) {
			WebElement hobbyTagName = hobbiesRadioButton.findElement(By.tagName("Label"));
			String individualHobbyName = hobbyTagName.getText();
			allHobbies.add(individualHobbyName);

			if (individualHobbyName.equalsIgnoreCase(wantedHobbyName)) {
				hobbyTagName.click();
				selectedHobby = individualHobbyName;
			}
		}
		System.out.println("All Hobbies: " + allHobbies);
		System.out.println("Selected hobby: " + selectedHobby);

		// Upload Picture
		driver.findElement(By.id("uploadPicture")).sendKeys("C:\\Users\\bandh\\Downloads\\edgedriver_win64.zip");

		driver.findElement(By.id("currentAddress")).sendKeys(currentAddress);

		// Scroll to state dropdown
		js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath("//label[@id='stateCity-label']")));

		// Select State
		driver.findElement(By.xpath("//div[contains(text(),'Select State')]")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'menu')]")));

		List<String> allStates = new ArrayList<>();
		List<WebElement> stateList = driver.findElements(By.xpath("//div[contains(@class,'menu')]//div[@tabindex='-1']"));
		for (WebElement singleState : stateList) {
			String individualState = singleState.getText();
			allStates.add(individualState);
			if (individualState.equalsIgnoreCase(wantedState)) {
				singleState.click();
				System.out.println("Selected State: " + individualState);
				break;
			}
		}
		System.out.println("All states from list are: " + allStates);

		// Select City
		WebElement selectCity = driver.findElement(By.xpath("//div[contains(text(),'Select City')]"));
		wait.until(ExpectedConditions.elementToBeClickable(selectCity));
		selectCity.click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'menu')]")));

		List<WebElement> cityList = driver.findElements(By.xpath("//div[contains(@class,'menu')]//div[@tabindex='-1']"));
		List<String> allCities = new ArrayList<>();

		for (WebElement city : cityList) {
			if (city.isDisplayed()) {
				String individualCity = city.getText();
				allCities.add(individualCity);
				if (individualCity.equalsIgnoreCase(wantedCity)) {
					city.click();
					System.out.println("Clicked City: " + individualCity);
					break;
				}
			}
		}
		System.out.println("All Cities: " + allCities);
		
		driver.findElement(By.id("submit")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-header']//div[contains(text(),'Thanks')]")));
		System.out.println("Final form is visible");
		WebElement submissionForm = driver.findElement(By.cssSelector(".modal-content"));
		
//		entering expected values in HashMap
		Map<String, String> expectedData = new HashMap<>();
		expectedData.put("Student Name", firstName + " " + lastName);
		expectedData.put("Student Email", email);
		expectedData.put("Gender", selectedGenderName);
		expectedData.put("Mobile", phnNo);
		expectedData.put("Date of Birth", day + " " + monthDob + "," + yearDob);
		expectedData.put("Subjects", wantedSubjectName);
		expectedData.put("Hobbies", selectedHobby);
		expectedData.put("Picture", "edgedriver_win64.zip");
		expectedData.put("Address", currentAddress);
		expectedData.put("State and City", wantedState + " " + wantedCity);
		
		Map<String, String> actualdata = new HashMap<>();
		List<WebElement> tableRows = submissionForm.findElements(By.xpath("//tbody//tr"));
		
		for(WebElement singleRow : tableRows)
		{
			List<WebElement> columns = singleRow.findElements(By.tagName("td"));
			if(columns.size()==2)
			{
				String key = columns.get(0).getText().trim();
				String value = columns.get(1).getText().trim();
				actualdata.put(key, value);
			}
		}
		
		for(String key : expectedData.keySet())
		{
			String expected = expectedData.get(key);
			String actual = actualdata.get(key);
			
			if (expected.equals(actual)) 
			{
		        System.out.println("PASS: " + key + " matched: " + expected);
		    } 
			else 
			{
		        System.out.println("FAIL: " + key + " mismatch → Expected: [" + expected + "] | Actual: [" + actual + "]");
		    }
			
		}
		
		
		
		
	}
}
