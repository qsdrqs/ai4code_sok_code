#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>

// Global MySQL connection handle
MYSQL *con = mysql_init(NULL);

// Function to add a student to the STUDENTS table
void add_student(const char *name, int age) {
    // Establish a new connection each time the function is called
    if (mysql_real_connect(con, "localhost", "user", "password", "database", 3306, NULL, 0) == NULL) {
        fprintf(stderr, "Connection error: %s\n", mysql_error(con));
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    // Construct the SQL query string
    char query[1024];
    snprintf(query, sizeof(query), "INSERT INTO STUDENTS VALUES (%s, %d)", name, age);

    // Execute the query (ignoring any errors, as in the original JS code)
    mysql_query(con, query);
}