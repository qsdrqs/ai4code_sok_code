import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentManager {
    private JTable studentsTable;
    private DefaultTableModel tableModel;
    
    // Constructor to initialize the table
    public StudentManager() {
        // Create table model with column headers
        String[] columnNames = {"Name", "Age"};
        tableModel = new DefaultTableModel(columnNames, 0);
        studentsTable = new JTable(tableModel);
    }
    
    // Java method that takes as input a String name and an int age and adds a new entry to table called students
    public void addStudent(String name, int age) {
        // Insert row at index 0 (equivalent to insertRow(1) in JavaScript which inserts after header)
        Object[] rowData = {name, age};
        tableModel.insertRow(0, rowData);
    }
    
    // Getter method to access the table (if needed)
    public JTable getStudentsTable() {
        return studentsTable;
    }
    
    // Alternative implementation using direct table manipulation
    public void addStudentAlternative(String name, int age) {
        tableModel.addRow(new Object[]{name, age});
    }
}