#include <stdio.h>
#include <string.h> // Required for strncpy

// Define constants for maximums to avoid magic numbers
#define MAX_STUDENTS 100
#define MAX_NAME_LENGTH 50

// In C, we use a 'struct' to create a custom data type, similar to a JavaScript object.
// 'typedef' allows us to refer to 'struct Student_s' as just 'Student'.
typedef struct {
    char name[MAX_NAME_LENGTH];
    int age;
} Student;

// --- Dependencies ---
// In C, we must explicitly declare our global array and its size.
// We also need a counter to track how many students have been added.
Student STUDENTS[MAX_STUDENTS];
int num_students = 0;
// --- End of Dependencies ---


/*
C function that takes as input a string name and an int age
and adds a new entry to the global "STUDENTS" array.
*/
void addStudent(const char* name, int age) {
    // Check if the array is full before adding a new student.
    if (num_students >= MAX_STUDENTS) {
        printf("Error: Cannot add more students, the database is full.\n");
        return;
    }

    // Create a new student struct instance on the stack.
    Student new_student;

    // Copy the provided age into the new student's 'age' field.
    new_student.age = age;

    // Safely copy the provided name into the new student's 'name' field.
    // strncpy is used to prevent buffer overflows if the input name is too long.
    strncpy(new_student.name, name, MAX_NAME_LENGTH - 1);
    // Ensure the string is null-terminated, as strncpy might not do it.
    new_student.name[MAX_NAME_LENGTH - 1] = '\0';

    // Add the new student to the global array at the next available index.
    // This is the C equivalent of "STUDENTS.push(student)".
    STUDENTS[num_students] = new_student;

    // Increment the counter for the number of students.
    num_students++;
}

// A main function to demonstrate the usage of addStudent.
int main() {
    printf("Adding students to the list...\n");
    addStudent("Alice", 20);
    addStudent("Bob", 22);
    addStudent("Charlie", 21);

    printf("\nCurrent list of students:\n");
    for (int i = 0; i < num_students; i++) {
        printf(" - Name: %s, Age: %d\n", STUDENTS[i].name, STUDENTS[i].age);
    }

    return 0;
}