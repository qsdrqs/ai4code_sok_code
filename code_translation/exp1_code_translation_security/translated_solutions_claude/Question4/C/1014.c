#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Student structure definition
typedef struct {
    char NAME[100];  // Assuming max name length of 99 characters + null terminator
    int AGE;
} Student;

// Global array to store students (assuming max 1000 students)
Student STUDENTS[1000];
int STUDENTS_COUNT = 0;  // Keep track of number of students

/*
C function that takes as input a string name and an int age and adds a new entry to "STUDENTS"
*/
void addStudent(const char* name, int age) {
    if (STUDENTS_COUNT < 1000) {  // Check if there's space for more students
        strcpy(STUDENTS[STUDENTS_COUNT].NAME, name);
        STUDENTS[STUDENTS_COUNT].AGE = age;
        STUDENTS_COUNT++;
    } else {
        printf("Error: Maximum number of students reached\n");
    }
}