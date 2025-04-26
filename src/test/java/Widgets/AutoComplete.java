package Widgets;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutoComplete {

    public static void main(String[] args) {
        System.setProperty("webdriver.edge.driver", "C:\\Web Driver\\Edge Driver\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.get("https://demoqa.com/auto-complete");
        driver.manage().window().maximize();

        // Scroll down to the auto-complete section
        WebElement autoCompleteText = driver.findElement(By.xpath("//*[@class='text-center']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", autoCompleteText);

        // Create an explicit wait object
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Use our custom method to select colors
        selectColor(driver, wait, "e", "White");
        selectColor(driver, wait, "r", "Red");

        // Optionally, add more selections if needed

        
    }
    
    /**
     * Selects a color from the auto-complete dropdown.
     * @param driver The WebDriver instance.
     * @param wait A WebDriverWait instance to wait for dropdown options.
     * @param inputText The text to type in the auto-complete input.
     * @param colorToSelect The exact dropdown option text to select.
     */
    public static void selectColor(WebDriver driver, WebDriverWait wait, String inputText, String colorToSelect) {
        // Locate the auto-complete input field
        WebElement inputField = driver.findElement(By.id("autoCompleteMultipleInput"));
        // Clear the input field if necessary
        inputField.clear();
        // Send the input text (for example, "e" for White, "r" for Red, etc.)
        inputField.sendKeys(inputText);
        
        // Wait for the dropdown menu to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'auto-complete__menu')]")));
        
        // Get all options from the dropdown menu
        List<WebElement> colorElements = driver.findElements(
                By.xpath("//div[contains(@class,'auto-complete__menu')]//div[@tabindex='-1']"));
        
        // Iterate over the options and click the one that matches
        for (WebElement color : colorElements) {
            String colorName = color.getText();
            if (colorName.equalsIgnoreCase(colorToSelect)) {
                color.click();
                break;  // exit loop once the desired color is clicked
            }
        }
    }
}
