#include <stdio.h>
#include <string.h>

// Define the maximum number of rows and columns for the table
#define MAX_ROWS 100
#define MAX_COLS 2

// Define a structure to represent a table row
typedef struct {
    char name[50];
    int age;
} Student;

// Global table to store students
Student students[MAX_ROWS];
int currentRow = 0; // Keeps track of the current number of rows in the table

// Function to add a new student to the table
void addStudent(const char* name, int age) {
    if (currentRow >= MAX_ROWS) {
        printf("Error: Table is full.\n");
        return;
    }

    // Add the new student to the table
    strncpy(students[currentRow].name, name, sizeof(students[currentRow].name) - 1);
    students[currentRow].name[sizeof(students[currentRow].name) - 1] = '\0'; // Ensure null-termination
    students[currentRow].age = age;

    currentRow++; // Increment the row count
}

// Function to display the table (for testing purposes)
void displayTable() {
    printf("Students Table:\n");
    printf("Name\tAge\n");
    for (int i = 0; i < currentRow; i++) {
        printf("%s\t%d\n", students[i].name, students[i].age);
    }
}

int main() {
    // Example usage
    addStudent("Alice", 20);
    addStudent("Bob", 22);

    // Display the table
    displayTable();

    return 0;
}