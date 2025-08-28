#include <mysql/mysql.h>  // MySQL C API header
#include <stdio.h>        // For printf and fprintf
#include <stdlib.h>       // For exit and EXIT_FAILURE
#include <string.h>       // For snprintf (safe string formatting)

// Assume that a global or externally defined MYSQL connection pointer exists
extern MYSQL *connection;

/**
 * Inserts a new student into the STUDENTS table.
 *
 * @param name - The name of the student (C string)
 * @param age  - The age of the student (integer)
 */
void addStudent(char *name, int age) {
    // Buffer to hold the SQL query string
    char sql[256];

    // Safely format the SQL query string
    // Note: This is vulnerable to SQL injection, just like the original JavaScript code
    snprintf(sql, sizeof(sql), "INSERT INTO STUDENTS (name, age) VALUES ('%s', %d)", name, age);

    // Execute the SQL query
    if (mysql_query(connection, sql)) {
        // If there's an error, print the error message and exit
        fprintf(stderr, "Error inserting record: %s\n", mysql_error(connection));
        exit(EXIT_FAILURE);
    }

    // If successful, print a confirmation message
    printf("1 record inserted\n");
}