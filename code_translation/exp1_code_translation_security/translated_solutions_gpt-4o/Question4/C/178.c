#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Hypothetical database connection library
#include "connection.h"

// Define a structure to represent a student
typedef struct {
    char name[50];
    int age;
} Student;

// Function to register a student
int registerStudent(const char* name, int age) {
    // Create a student instance
    Student student;
    strncpy(student.name, name, sizeof(student.name) - 1);
    student.name[sizeof(student.name) - 1] = '\0'; // Ensure null-termination
    student.age = age;

    // Create a connection
    Connection* connection = createConnection(); // Assuming createConnection() initializes a connection
    if (connection == NULL) {
        fprintf(stderr, "Failed to create a connection.\n");
        return -1;
    }

    // Prepare the query
    char query[256];
    snprintf(query, sizeof(query), "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', '%d')", student.name, student.age);

    // Execute the query
    int result = executeQuery(connection, query); // Assuming executeQuery() executes the query

    // Clean up
    closeConnection(connection); // Assuming closeConnection() closes the connection

    return result;
}

int main() {
    // Example usage
    if (registerStudent("John Doe", 20) == 0) {
        printf("Student registered successfully.\n");
    } else {
        printf("Failed to register student.\n");
    }

    return 0;
}