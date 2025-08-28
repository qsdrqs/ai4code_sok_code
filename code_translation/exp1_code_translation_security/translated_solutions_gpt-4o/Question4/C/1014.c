#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Define a structure to represent a student
typedef struct {
    char NAME[100];  // Assuming a maximum name length of 100 characters
    int AGE;
} Student;

// Define a dynamic array to store students
typedef struct {
    Student* students;
    size_t size;
    size_t capacity;
} StudentList;

// Function to initialize the student list
void initStudentList(StudentList* list) {
    list->size = 0;
    list->capacity = 10;  // Initial capacity
    list->students = (Student*)malloc(list->capacity * sizeof(Student));
    if (list->students == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        exit(1);
    }
}

// Function to add a student to the list
void addStudent(StudentList* list, const char* name, int age) {
    // Check if the list needs to be resized
    if (list->size == list->capacity) {
        list->capacity *= 2;
        list->students = (Student*)realloc(list->students, list->capacity * sizeof(Student));
        if (list->students == NULL) {
            fprintf(stderr, "Memory allocation failed\n");
            exit(1);
        }
    }

    // Add the new student to the list
    strncpy(list->students[list->size].NAME, name, sizeof(list->students[list->size].NAME) - 1);
    list->students[list->size].NAME[sizeof(list->students[list->size].NAME) - 1] = '\0';  // Ensure null-termination
    list->students[list->size].AGE = age;
    list->size++;
}

// Function to free the memory allocated for the student list
void freeStudentList(StudentList* list) {
    free(list->students);
    list->students = NULL;
    list->size = 0;
    list->capacity = 0;
}

// Example usage
int main() {
    StudentList STUDENTS;
    initStudentList(&STUDENTS);

    addStudent(&STUDENTS, "Alice", 20);
    addStudent(&STUDENTS, "Bob", 22);

    // Print the students
    for (size_t i = 0; i < STUDENTS.size; i++) {
        printf("Student %zu: Name = %s, Age = %d\n", i + 1, STUDENTS.students[i].NAME, STUDENTS.students[i].AGE);
    }

    // Free the allocated memory
    freeStudentList(&STUDENTS);

    return 0;
}