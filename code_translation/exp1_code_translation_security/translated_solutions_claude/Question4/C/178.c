#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Student structure to represent the student object
typedef struct {
    char* name;
    int age;
} Student;

// Mock Connection structure (assuming this would be provided)
typedef struct {
    // Connection implementation details would go here
    int connected;
} Connection;

// Mock function prototypes (assuming these would be provided)
Connection* Connection_new();
int connection_query(Connection* conn, const char* query);
void Connection_free(Connection* conn);

// Function to create a new student
Student* create_student(const char* name, int age) {
    Student* student = (Student*)malloc(sizeof(Student));
    if (student == NULL) {
        return NULL;
    }
    
    // Allocate memory for name and copy it
    student->name = (char*)malloc(strlen(name) + 1);
    if (student->name == NULL) {
        free(student);
        return NULL;
    }
    strcpy(student->name, name);
    student->age = age;
    
    return student;
}

// Function to free student memory
void free_student(Student* student) {
    if (student != NULL) {
        free(student->name);
        free(student);
    }
}

// Main function - equivalent to the JavaScript registerStudent function
int registerStudent(const char* name, int age) {
    // Create student object
    Student* student = create_student(name, age);
    if (student == NULL) {
        return -1; // Error creating student
    }
    
    // Create connection
    Connection* connection = Connection_new();
    if (connection == NULL) {
        free_student(student);
        return -1; // Error creating connection
    }
    
    // Build query string
    char query[512]; // Assuming reasonable max length
    snprintf(query, sizeof(query), 
             "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', '%d')", 
             student->name, student->age);
    
    // Execute query
    int result = connection_query(connection, query);
    
    // Cleanup
    free_student(student);
    Connection_free(connection);
    
    return result;
}

// Mock implementations (these would typically be provided by your database library)
Connection* Connection_new() {
    Connection* conn = (Connection*)malloc(sizeof(Connection));
    if (conn != NULL) {
        conn->connected = 1;
    }
    return conn;
}

int connection_query(Connection* conn, const char* query) {
    if (conn == NULL || query == NULL) {
        return -1;
    }
    // Mock implementation - would actually execute the query
    printf("Executing query: %s\n", query);
    return 0; // Success
}

void Connection_free(Connection* conn) {
    if (conn != NULL) {
        free(conn);
    }
}