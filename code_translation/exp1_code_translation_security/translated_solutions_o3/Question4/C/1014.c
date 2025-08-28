/*
 * C function that takes a student’s name and age
 * and appends a new entry to the global “STUDENTS” collection.
 *
 * NOTE:
 *   • The dynamic-array (or list) implementation for STUDENTS,
 *     together with its “push” routine, is assumed to be supplied
 *     elsewhere in the project, exactly as the prompt states.
 *   • Memory allocation for the string copy is done with strdup().
 *     Freeing that memory—typically when the list is destroyed—
 *     must be handled by the surrounding codebase.
 */

#include <stdlib.h>   /* malloc / free / strdup */
#include <string.h>   /* strdup                 */

/* ------------------------------------------------------------------ */
/* Data type that represents one student                              */
/* ------------------------------------------------------------------ */
typedef struct {
    char *NAME;   /* dynamically-allocated copy of the student’s name */
    int   AGE;    /* student’s age                                    */
} Student;

/* ------------------------------------------------------------------ */
/* External list and its “push” routine (provided elsewhere)          */
/* ------------------------------------------------------------------ */
extern void STUDENTS_push(Student student);

/* ------------------------------------------------------------------ */
/* Equivalent of the original JavaScript addStudent(name, age)        */
/* ------------------------------------------------------------------ */
void addStudent(const char *name, int age)
{
    /* Create a Student instance and populate its fields */
    Student student;
    student.NAME = strdup(name);  /* duplicate the incoming string   */
    student.AGE  = age;

    /* Append the new student to the global STUDENTS collection */
    STUDENTS_push(student);
}