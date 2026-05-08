#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_STUDENTS 100
#define MAX_NAME_LEN 50

/* Student structure */
typedef struct {
    char name[MAX_NAME_LEN];
    int age;
} Student;

/* Student collection structure */
typedef struct {
    Student students[MAX_STUDENTS];
    int count;
} StudentList;

/* Global STUDENTS collection */
StudentList STUDENTS = { .count = 0 };

/* Add method for the collection */
void add(StudentList *list, const char *name, int age) {
    if (list->count < MAX_STUDENTS) {
        strncpy(list->students[list->count].name, name, MAX_NAME_LEN - 1);
        list->students[list->count].name[MAX_NAME_LEN - 1] = '\0';
        list->students[list->count].age = age;
        list->count++;
    }
}

/* Translated function */
void myFunction(const char *name, int age) {
    add(&STUDENTS, name, age);
}