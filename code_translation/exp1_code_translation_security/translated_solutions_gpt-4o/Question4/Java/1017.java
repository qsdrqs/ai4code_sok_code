import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentTableManager {

    private JTable studentsTable;
    private DefaultTableModel tableModel;

    public StudentTableManager(JTable table) {
        this.studentsTable = table;
        this.tableModel = (DefaultTableModel) table.getModel();
    }

    // Method to add a new student to the table
    public void addStudent(String name, int age) {
        // Add a new row to the table with the provided name and age
        tableModel.insertRow(0, new Object[]{name, age});
    }

    public static void main(String[] args) {
        // Create a JFrame to hold the table
        JFrame frame = new JFrame("Students Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a table with two columns: Name and Age
        String[] columnNames = {"Name", "Age"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);

        // Add the table to a scroll pane and then to the frame
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);

        // Create an instance of StudentTableManager
        StudentTableManager manager = new StudentTableManager(table);

        // Add some sample students
        manager.addStudent("Alice", 20);
        manager.addStudent("Bob", 22);

        // Set up the frame
        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}