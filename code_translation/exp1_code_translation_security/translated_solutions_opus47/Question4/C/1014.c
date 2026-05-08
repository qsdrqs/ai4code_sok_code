#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAX_STUDENTS 100
#define MAX_NAME_LENGTH 100

typedef struct {
    char NAME[MAX_NAME_LENGTH];
    int AGE;
} Student;

Student STUDENTS[MAX_STUDENTS];
int student_count = 0;

/*
C function that takes as input a string name and an int age and adds a new entry to "STUDENTS"
*/
void addStudent(const char *name, int age) {
    Student student;
    strncpy(student.NAME, name, MAX_NAME_LENGTH - 1);
    student.NAME[MAX_NAME_LENGTH - 1] = '\0';
    student.AGE = age;
    STUDENTS[student_count++] = student;
}