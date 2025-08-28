#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Define the structure for a student
typedef struct Student {
    char* name;
    int age;
    struct Student* next;
} Student;

// Define the STUDENTS structure with an add function
typedef struct {
    Student* head;
} Students;

// Function to add a new student
void add_student(Students* students, char* name, int age) {
    Student* new_student = (Student*) malloc(sizeof(Student));
    new_student->name = (char*) malloc(strlen(name) + 1);
    strcpy(new_student->name, name);
    new_student->age = age;
    new_student->next = students->head;
    students->head = new_student;
}

// Function equivalent to myFunction
void my_function(Students* students, char* name, int age) {
    add_student(students, name, age);
}

int main() {
    // Initialize STUDENTS
    Students students;
    students.head = NULL;

    // Example usage
    my_function(&students, "John Doe", 20);

    // Free allocated memory (not shown in original JavaScript code)
    Student* current = students.head;
    while (current != NULL) {
        Student* next = current->next;
        free(current->name);
        free(current);
        current = next;
    }

    return 0;
}