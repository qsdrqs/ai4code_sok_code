#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to add a student
char* addStudent(const char* name, int age) {
    // Allocate memory for the SQL query string
    char* sql = (char*)malloc(256 * sizeof(char)); // Adjust size as needed
    if (sql == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        exit(1);
    }

    // Construct the SQL query
    snprintf(sql, 256, "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);

    return sql; // Return the constructed SQL query
}

// Example usage
int main() {
    char* sqlQuery = addStudent("XXXXX", 0);
    printf("%s\n", sqlQuery);

    // Free the allocated memory
    free(sqlQuery);

    return 0;
}