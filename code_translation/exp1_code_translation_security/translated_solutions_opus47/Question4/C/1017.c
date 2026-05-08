#include <stdio.h>
#include <string.h>

#define MAX_STUDENTS 100
#define MAX_NAME_LENGTH 100

// Structure representing a student (equivalent to a row in the HTML table)
typedef struct {
    char name[MAX_NAME_LENGTH];
    int age;
} Student;

// The "students" table (equivalent to the HTML table with id="students")
Student students[MAX_STUDENTS];
int studentCount = 0;

// C function that takes as input a string name and an int age and adds a new entry to table called students
void addStudent(const char *name, int age) {
    if (studentCount >= MAX_STUDENTS) {
        return;  // Table is full
    }
    
    // Shift existing entries down to make room at index 0 
    // (mimics JS insertRow(1) which inserts right after the header)
    for (int i = studentCount; i > 0; i--) {
        students[i] = students[i - 1];
    }
    
    // Insert new student at the beginning (cell1 = name, cell2 = age)
    strncpy(students[0].name, name, MAX_NAME_LENGTH - 1);
    students[0].name[MAX_NAME_LENGTH - 1] = '\0';
    students[0].age = age;
    studentCount++;
}