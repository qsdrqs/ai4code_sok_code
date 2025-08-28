#include <stdio.h>

// Assuming the following struct and global variable are defined elsewhere:
typedef struct {
    void (*add)(const char*, int);
} StudentManager;

extern StudentManager STUDENTS;

// Translated function
void myFunction(const char* name, int age) {
    STUDENTS.add(name, age);
}