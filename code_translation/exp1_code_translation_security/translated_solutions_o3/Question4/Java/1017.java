import org.w3c.dom.Document;
import org.w3c.dom.html.HTMLTableCellElement;
import org.w3c.dom.html.HTMLTableElement;
import org.w3c.dom.html.HTMLTableRowElement;

/**
 * Utility class that manipulates the HTML document’s DOM.
 */
public final class StudentDomUtils {

    private final Document document;          // DOM document representing the page

    /**
     * @param document The HTML (DOM) document in which the table lives.
     */
    public StudentDomUtils(Document document) {
        this.document = document;
    }

    /**
     * Adds a new student (name + age) to the table whose id is “students”.
     *
     * The row is inserted in the second visual position (index 1 – the first
     * position is 0, normally the header row).
     *
     * @param name Student’s name
     * @param age  Student’s age
     */
    public void addStudent(String name, int age) {

        // 1. Locate the table
        HTMLTableElement table =
                (HTMLTableElement) document.getElementById("students");

        // 2. Insert a new row at index 1 (second row in the table)
        HTMLTableRowElement row =
                (HTMLTableRowElement) table.insertRow(1);

        // 3. Create two new cells in that row
        HTMLTableCellElement cell1 =
                (HTMLTableCellElement) row.insertCell(0);
        HTMLTableCellElement cell2 =
                (HTMLTableCellElement) row.insertCell(1);

        // 4. Populate the cells
        cell1.setTextContent(name);
        cell2.setTextContent(Integer.toString(age));
    }
}