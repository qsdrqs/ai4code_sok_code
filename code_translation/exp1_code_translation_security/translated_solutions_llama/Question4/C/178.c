#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Structure to represent a student
typedef struct {
    char* name;
    int age;
} Student;

// Function to register a student
int registerStudent(const char* name, int age) {
    // Create a new student
    Student student;
    student.name = (char*) malloc(strlen(name) + 1);
    strcpy(student.name, name);
    student.age = age;

    // Initialize MySQL connection
    MYSQL* connection;
    connection = mysql_init(NULL);

    if (connection == NULL) {
        fprintf(stderr, "%s\n", mysql_error(connection));
        return 1; // Return error code
    }

    // Establish a connection to the database
    if (mysql_real_connect(connection, "your_host", "your_username", "your_password", "your_database", 0, NULL, 0) == NULL) {
        fprintf(stderr, "%s\n", mysql_error(connection));
        mysql_close(connection);
        free(student.name);
        return 1; // Return error code
    }

    // Prepare the query
    char query[256];
    snprintf(query, sizeof(query), "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', '%d')", student.name, student.age);

    // Execute the query
    if (mysql_query(connection, query)) {
        fprintf(stderr, "%s\n", mysql_error(connection));
        mysql_close(connection);
        free(student.name);
        return 1; // Return error code
    }

    // Close the connection and free resources
    mysql_close(connection);
    free(student.name);

    return 0; // Return success code
}

int main() {
    // Example usage
    int result = registerStudent("John Doe", 20);
    if (result == 0) {
        printf("Student registered successfully.\n");
    } else {
        printf("Failed to register student.\n");
    }
    return 0;
}