#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to insert a student into the database
void insertInto(const char *name, int age) {
    MYSQL *conn;
    char sql[1024];

    // Initialize MySQL connection
    conn = mysql_init(NULL);
    if (conn == NULL) {
        fprintf(stderr, "mysql_init failed\n");
        return;
    }

    // Connect to the database
    if (mysql_real_connect(
        conn,
        "localhost",     // Host
        "root",          // User
        "password",      // Password
        "school",        // Database name
        0,               // Port (0 = default)
        NULL,            // Unix socket (NULL = default)
        0                // Client flags
    ) == NULL) {
        fprintf(stderr, "Connection failed: %s\n", mysql_error(conn));
        mysql_close(conn);
        return;
    }

    // Format the SQL query
    // WARNING: This is vulnerable to SQL injection
    snprintf(sql, sizeof(sql), "INSERT INTO STUDENTS (NAME, AGE) VALUES (%s, %d)", name, age);

    // Execute the query
    if (mysql_query(conn, sql)) {
        fprintf(stderr, "Query failed: %s\n", mysql_error(conn));
    }

    // Close the connection
    mysql_close(conn);
}