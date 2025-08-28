import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentTable extends Application {

    private ObservableList<Student> students = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        // Create table
        TableView<Student> table = new TableView<>();
        table.setPrefHeight(200);

        // Create columns
        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Student, Integer> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        // Add columns to table
        table.getColumns().addAll(nameColumn, ageColumn);
        table.setItems(students);

        // Create button to add student
        javafx.scene.control.Button addButton = new javafx.scene.control.Button("Add Student");
        addButton.setOnAction(event -> {
            // Create a new stage for input
            Stage inputStage = new Stage();
            inputStage.setTitle("Add Student");

            // Create input fields
            javafx.scene.control.TextField nameField = new javafx.scene.control.TextField();
            nameField.setPromptText("Name");

            javafx.scene.control.TextField ageField = new javafx.scene.control.TextField();
            ageField.setPromptText("Age");

            // Create button to submit input
            javafx.scene.control.Button submitButton = new javafx.scene.control.Button("Submit");
            submitButton.setOnAction(e -> {
                try {
                    String name = nameField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    students.add(new Student(name, age));
                    inputStage.close();
                } catch (NumberFormatException ex) {
                    // Handle invalid age input
                }
            });

            // Layout input fields and button
            VBox inputBox = new VBox(10);
            inputBox.getChildren().addAll(nameField, ageField, submitButton);
            inputBox.setAlignment(Pos.CENTER);
            inputBox.setPadding(new Insets(10));

            // Show input stage
            Scene inputScene = new Scene(inputBox);
            inputStage.setScene(inputScene);
            inputStage.show();
        });

        // Layout table and button
        VBox root = new VBox(10);
        root.getChildren().addAll(table, addButton);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));

        // Show primary stage
        Scene scene = new Scene(root);
        primaryStage.setTitle("Student Table");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static class Student {
        private String name;
        private int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}