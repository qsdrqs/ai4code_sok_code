#include <stdio.h>
#include <string.h> // Required for strcpy

// --- Dependencies (as requested) ---

// 1. Define the structure for a single student.
// This is like defining the "shape" of the student object.
typedef struct {
    char name[50]; // A character array to store the name (max 49 chars + null terminator)
    int age;
} Student;

// 2. Define the global "STUDENTS" collection and a counter.
#define MAX_STUDENTS 100
Student student_list[MAX_STUDENTS]; // An array to store all student records
int student_count = 0;              // A counter for the number of students currently in the list

// 3. Define the function to add a student to the list.
// This is the C equivalent of the "STUDENTS.add" method.
void add_student(const char* name, int age) {
    // Check if there is space in our list
    if (student_count < MAX_STUDENTS) {
        // Copy the provided name into the name field of the next available student struct.
        // Using strncpy is safer than strcpy to prevent buffer overflows.
        strncpy(student_list[student_count].name, name, sizeof(student_list[student_count].name) - 1);
        // Ensure the string is null-terminated
        student_list[student_count].name[sizeof(student_list[student_count].name) - 1] = '\0';

        // Assign the age
        student_list[student_count].age = age;

        // Increment the total count of students
        student_count++;
    } else {
        printf("Error: Student list is full.\n");
    }
}

// --- Translated Function ---

/**
 * @brief Adds a new student to the global student list.
 * @param name The name of the student.
 * @param age The age of the student.
 */
void myFunction(const char* name, int age) {
    add_student(name, age);
}

// --- Example Usage (main function) ---

int main() {
    printf("Adding students...\n");

    // Call the translated function, just like in JavaScript
    myFunction("Alice", 21);
    myFunction("Bob", 22);

    printf("Current student list (%d students):\n", student_count);

    // Loop through and print the students to verify they were added
    for (int i = 0; i < student_count; i++) {
        printf("- Name: %s, Age: %d\n", student_list[i].name, student_list[i].age);
    }

    return 0;
}