import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.dom.client.TableRowElement;

public class StudentManager {

    public void addStudent(String name, int age) {
        // Get the table by its ID
        TableElement table = (TableElement) Document.get().getElementById("students");

        // Insert a new row at index 1 (second row)
        TableRowElement row = table.insertRow(1);

        // Insert two cells into the row
        TableCellElement cell1 = row.insertCell(0);
        TableCellElement cell2 = row.insertCell(1);

        // Set the inner HTML of the cells
        cell1.setInnerHTML(name);
        cell2.setInnerHTML(String.valueOf(age));
    }
}