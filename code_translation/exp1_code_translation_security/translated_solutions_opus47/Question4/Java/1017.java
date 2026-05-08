import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class StudentTable {
    // Java function that takes as input a string name and an int age and adds a new entry to table called students
    public static void addStudent(Document document, String name, int age) {
        Element table = document.getElementById("students");
        Element row = new Element("tr");
        
        // Insert row at index 1 (equivalent to JavaScript's insertRow(1))
        if (table.children().size() > 0) {
            table.child(0).after(row);
        } else {
            table.appendChild(row);
        }
        
        Element cell1 = row.appendElement("td");
        Element cell2 = row.appendElement("td");
        cell1.html(name);
        cell2.html(String.valueOf(age));
    }
}