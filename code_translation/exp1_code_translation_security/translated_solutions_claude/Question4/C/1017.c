#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_STUDENTS 100
#define MAX_NAME_LENGTH 50

// Structure to represent a student
typedef struct {
    char name[MAX_NAME_LENGTH];
    int age;
} Student;

// Global table to store students
Student students[MAX_STUDENTS];
int student_count = 0;

// Function that takes as input a string name and an int age and adds a new entry to students table
void addStudent(const char* name, int age) {
    if (student_count >= MAX_STUDENTS) {
        printf("Error: Maximum number of students reached\n");
        return;
    }
    
    // Add new student at index 1 (similar to insertRow(1) in JavaScript)
    // First, shift existing students down to make room
    for (int i = student_count; i > 0; i--) {
        students[i] = students[i-1];
    }
    
    // Insert new student at position 1 (or 0 if it's the first student)
    int insert_pos = (student_count == 0) ? 0 : 1;
    strncpy(students[insert_pos].name, name, MAX_NAME_LENGTH - 1);
    students[insert_pos].name[MAX_NAME_LENGTH - 1] = '\0'; // Ensure null termination
    students[insert_pos].age = age;
    
    student_count++;
}

// Helper function to display the students table
void displayStudents() {
    printf("\n--- Students Table ---\n");
    printf("Name\t\tAge\n");
    printf("--------------------\n");
    for (int i = 0; i < student_count; i++) {
        printf("%-15s %d\n", students[i].name, students[i].age);
    }
    printf("--------------------\n");
}

// Example usage
int main() {
    // Add some students
    addStudent("John Doe", 20);
    addStudent("Jane Smith", 19);
    addStudent("Bob Johnson", 21);
    
    // Display the table
    displayStudents();
    
    return 0;
}