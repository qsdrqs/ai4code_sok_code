#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Define the maximum number of students
#define MAX_STUDENTS 100
#define MAX_NAME_LENGTH 100

// Define the structure for a student
typedef struct {
    char NAME[MAX_NAME_LENGTH];
    int AGE;
} Student;

// Global array to store students
Student STUDENTS[MAX_STUDENTS];
int studentCount = 0; // Keep track of the current number of students

// Function to add a new student
void addStudent(const char* name, int age) {
    if (studentCount < MAX_STUDENTS) {
        // Copy the name to the student struct
        strcpy(STUDENTS[studentCount].NAME, name);
        STUDENTS[studentCount].AGE = age;
        studentCount++; // Increment the student count
    } else {
        printf("Maximum number of students reached.\n");
    }
}

int main() {
    // Example usage
    addStudent("John Doe", 20);
    addStudent("Jane Doe", 22);

    // Print the added students
    for (int i = 0; i < studentCount; i++) {
        printf("Name: %s, Age: %d\n", STUDENTS[i].NAME, STUDENTS[i].AGE);
    }

    return 0;
}