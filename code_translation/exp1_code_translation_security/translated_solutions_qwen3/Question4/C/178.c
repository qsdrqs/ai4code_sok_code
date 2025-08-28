#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Define the Student struct to mirror the JavaScript object
typedef struct {
    const char* name;
    int age;
} Student;

// Define the Connection struct (mocked for this example)
typedef struct {
    // In a real implementation, this would contain connection details
    // like socket, database handle, etc.
} Connection;

// Function to create a new connection (mocked)
Connection* create_connection() {
    Connection* conn = (Connection*)malloc(sizeof(Connection));
    if (!conn) {
        // Handle allocation failure if needed
        return NULL;
    }
    return conn;
}

// Function to execute a query on the connection (mocked)
int execute_query(Connection* conn, const char* query) {
    // In a real implementation, this would send the query to the database
    // and return a status code (e.g., 0 for success, non-zero for error).
    return 0; // Mock success
}

// Translated registerStudent function
int registerStudent(const char* name, int age) {
    // Create a Student struct to mirror the JavaScript object
    Student student = { name, age };

    // Create a new connection
    Connection* connection = create_connection();
    if (!connection) {
        // Handle connection creation failure
        return -1;
    }

    // Build the SQL query string
    char query[512]; // Sufficient size for most cases
    snprintf(query, sizeof(query),
             "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)",
             student.name, student.age);

    // Execute the query and return the result
    int result = execute_query(connection, query);

    // Clean up the connection (optional, depending on real implementation)
    free(connection);

    return result;
}