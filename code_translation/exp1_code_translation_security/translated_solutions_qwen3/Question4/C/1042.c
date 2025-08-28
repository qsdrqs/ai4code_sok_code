#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql.h>

// Assume that the connection is a global MYSQL pointer initialized elsewhere
extern MYSQL* connection;

void addStudent(char* name, int age) {
    // Estimate buffer size for SQL query
    size_t buffer_size = strlen(name) + 100; // 100 is a safe estimate for the rest of the query
    char* sql = (char*)malloc(buffer_size);

    if (!sql) {
        fprintf(stderr, "Memory allocation failed\n");
        exit(EXIT_FAILURE);
    }

    // Construct the SQL query
    snprintf(sql, buffer_size, "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);

    // Execute the query
    if (mysql_query(connection, sql)) {
        fprintf(stderr, "Error: %s\n", mysql_error(connection));
        free(sql);
        exit(EXIT_FAILURE);
    }

    // Log success
    printf("1 record inserted\n");

    // Clean up
    free(sql);
}