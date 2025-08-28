import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
// Assume other necessary Selenium imports like ChromeDriver are handled elsewhere.

/**
 * A utility class for manipulating the students table on a web page.
 */
public class StudentManager {

    /**
     * Takes as input a string name and an int age and adds a new entry to a table called "students".
     * This method is the Java equivalent of the provided JavaScript function, using Selenium WebDriver.
     *
     * @param driver The WebDriver instance controlling the browser.
     * @param name The name of the student to add.
     * @param age The age of the student to add.
     */
    public void addStudent(WebDriver driver, String name, int age) {
        // 1. Find the table element by its ID, equivalent to document.getElementById("students")
        WebElement table = driver.findElement(By.id("students"));

        // 2. To directly replicate the JavaScript's DOM manipulation (insertRow, insertCell),
        //    it's easiest and most direct to use a JavascriptExecutor.
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // 3. The script to execute is almost identical to the original JavaScript function.
        //    We pass the table, name, and age as arguments to the script for safety and clarity.
        String script = "var table = arguments[0];" +
                        "var name = arguments[1];" +
                        "var age = arguments[2];" +
                        "var row = table.insertRow(1);" + // Inserts a new row at the second position
                        "var cell1 = row.insertCell(0);" +
                        "var cell2 = row.insertCell(1);" +
                        "cell1.innerHTML = name;" +
                        "cell2.innerHTML = age;";

        // 4. Execute the script with the required arguments.
        js.executeScript(script, table, name, age);
    }
}