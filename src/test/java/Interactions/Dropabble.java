package Interactions;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

public class Dropabble {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new EdgeDriver();
        driver.get("https://demoqa.com/droppable");
        driver.manage().window().maximize();

        scroll(driver, driver.findElement(By.cssSelector(".text-center")));
        driver.findElement(By.id("droppableExample-tab-revertable")).click();

        // Step 1: Trigger revert animation
        WebElement revertable = driver.findElement(By.id("revertable"));
        WebElement nonRevertable = driver.findElement(By.id("notRevertable"));
        performOffsetDrag(driver, revertable, nonRevertable);
        Thread.sleep(1500); // allow revert animation

        // Step 2: Locate reverted element again
        WebElement reverted = driver.findElement(By.id("revertable"));
        WebElement dropTarget = driver.findElement(By.id("droppable"));
        scroll(driver, reverted);
        scroll(driver, dropTarget);

        // Step 3: Final drop to target
        performOffsetDrag(driver, reverted, dropTarget);
        Thread.sleep(1000);

        // Step 4: Validate
        String dropText = dropTarget.getText();
        String dropClass = dropTarget.getAttribute("class");

        System.out.println("🟢 Class after drop: " + dropClass);
        System.out.println("✅ Drop zone text: " + dropText);

        if (dropClass.contains("ui-state-highlight") && dropText.equalsIgnoreCase("Dropped!")) {
            System.out.println("🎯 PASS: Drop successful!");
        } else {
            System.out.println("❌ FAIL: Drop feedback not as expected.");
        }

        driver.quit();
    }

    private static void scroll(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    private static void performOffsetDrag(WebDriver driver, WebElement source, WebElement target) {
        Point sourceLocation = source.getLocation();
        Point targetLocation = target.getLocation();

        int xOffset = targetLocation.getX() - sourceLocation.getX();
        int yOffset = targetLocation.getY() - sourceLocation.getY();

        new Actions(driver)
                .moveToElement(source)
                .clickAndHold()
                .moveByOffset(xOffset + 10, yOffset + 10)
                .pause(300)
                .release()
                .perform();
    }
}
