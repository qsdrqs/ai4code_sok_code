#include <mysql/mysql.h>  // MySQL C API header
#include <stdio.h>        // For printf and fprintf
#include <stdlib.h>       // For exit()

/**
 * Adds a new entry to the SQL table "STUDENTS".
 *
 * @param name  The name of the student (string).
 * @param age   The age of the student (integer).
 * @param conn  A pointer to a valid MYSQL connection object.
 */
void add(const char *name, int age, MYSQL *conn) {
    // Buffer to hold the SQL query string
    char sql[256];

    // Format the SQL INSERT statement
    snprintf(sql, sizeof(sql),
             "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)",
             name, age);

    // Log the SQL query to the console
    printf("%s\n", sql);

    // Execute the SQL query
    if (mysql_query(conn, sql)) {
        // If there's an error, print it and exit
        fprintf(stderr, "Error: %s\n", mysql_error(conn));
        exit(1);
    }

    // Log success message
    printf("1 record inserted\n");
}