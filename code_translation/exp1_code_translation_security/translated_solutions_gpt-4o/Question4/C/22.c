#include <stdio.h>
#include <string.h>

// Assuming STUDENTS is a structure with an add function
typedef struct {
    void (*add)(const char* name, int age);
} Students;

// Example implementation of the add function
void addStudent(const char* name, int age) {
    printf("Added student: Name = %s, Age = %d\n", name, age);
}

// Global STUDENTS object
Students STUDENTS = { addStudent };

// Translated function
void myFunction(const char* name, int age) {
    STUDENTS.add(name, age);
}

// Example usage
int main() {
    myFunction("John Doe", 20);
    return 0;
}