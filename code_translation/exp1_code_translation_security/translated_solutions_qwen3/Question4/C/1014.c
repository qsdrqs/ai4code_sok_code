#include <stdlib.h>   // For malloc, realloc, free, exit
#include <string.h>   // For strlen, strcpy

// Define the Student structure
typedef struct {
    char* NAME;  // Pointer to a dynamically allocated string
    int AGE;     // Integer to store the student's age
} Student;

// Global variables to simulate the JavaScript STUDENTS array
Student* STUDENTS = NULL;     // Pointer to the dynamic array of students
size_t student_count = 0;     // Current number of students in the array
size_t student_capacity = 0;  // Current capacity of the array

// Function to add a new student to the STUDENTS array
void addStudent(const char* name, int age) {
    // Step 1: Check if we need to expand the array
    if (student_count >= student_capacity) {
        // Double the capacity if not zero, otherwise start with 1
        student_capacity = (student_capacity == 0) ? 1 : student_capacity * 2;

        // Reallocate memory for the array
        Student* temp = realloc(STUDENTS, student_capacity * sizeof(Student));
        if (!temp) {
            // Memory allocation failed
            exit(EXIT_FAILURE);
        }
        STUDENTS = temp;
    }

    // Step 2: Allocate and copy the name string
    STUDENTS[student_count].NAME = malloc(strlen(name) + 1);
    if (!STUDENTS[student_count].NAME) {
        // Memory allocation failed
        exit(EXIT_FAILURE);
    }
    strcpy(STUDENTS[student_count].NAME, name);

    // Step 3: Assign the age
    STUDENTS[student_count].AGE = age;

    // Step 4: Increment the student count
    student_count++;
}