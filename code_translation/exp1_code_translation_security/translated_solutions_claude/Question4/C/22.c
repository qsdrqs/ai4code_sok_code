#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Student structure
typedef struct Student {
    char* name;
    int age;
    struct Student* next;
} Student;

// Students collection structure
typedef struct {
    Student* head;
} Students;

// Global STUDENTS instance
Students STUDENTS = {NULL};

// Function to add a student to the collection
void add(Students* students, const char* name, int age) {
    Student* newStudent = (Student*)malloc(sizeof(Student));
    
    // Allocate memory for name and copy it
    newStudent->name = (char*)malloc(strlen(name) + 1);
    strcpy(newStudent->name, name);
    
    newStudent->age = age;
    newStudent->next = students->head;
    students->head = newStudent;
}

// Translated function
void myFunction(const char* name, int age) {
    add(&STUDENTS, name, age);
}

// Helper function to free memory (cleanup)
void freeStudents(Students* students) {
    Student* current = students->head;
    while (current != NULL) {
        Student* temp = current;
        current = current->next;
        free(temp->name);
        free(temp);
    }
    students->head = NULL;
}