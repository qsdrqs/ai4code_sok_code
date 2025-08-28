#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to insert a new row into the 'todos' table
void add(char *name, int age, char *table) {
    // Suppress unused parameter warning
    (void)table;

    MYSQL *conn;
    MYSQL_STMT *stmt;

    // Database configuration (hardcoded as per instruction)
    const char *host = "localhost";
    const char *user = "user";
    const char *password = "password";
    const char *database = "test";

    // SQL statement to insert a new row
    const char *stmt_str = "INSERT INTO todos (title, completed) VALUES (?, ?)";

    // Initialize MySQL connection
    conn = mysql_init(NULL);
    if (!conn) {
        fprintf(stderr, "Failed to initialize MySQL connection\n");
        return;
    }

    // Connect to the database
    if (!mysql_real_connect(conn, host, user, password, database, 0, NULL, 0)) {
        fprintf(stderr, "Connection Error: %s\n", mysql_error(conn));
        mysql_close(conn);
        return;
    }

    // Initialize prepared statement
    stmt = mysql_stmt_init(conn);
    if (!stmt) {
        fprintf(stderr, "Failed to initialize MySQL statement\n");
        mysql_close(conn);
        return;
    }

    // Prepare the SQL statement
    if (mysql_stmt_prepare(stmt, stmt_str, strlen(stmt_str))) {
        fprintf(stderr, "Prepare Error: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(conn);
        return;
    }

    // Bind parameters
    MYSQL_BIND param[2];
    memset(param, 0, sizeof(param));

    // First parameter: title (string)
    param[0].buffer_type = MYSQL_TYPE_STRING;
    param[0].buffer = name;
    param[0].buffer_length = strlen(name);
    param[0].is_null = 0;
    param[0].length = NULL;

    // Second parameter: completed (integer)
    param[1].buffer_type = MYSQL_TYPE_LONG;
    param[1].buffer = (void *)&age;
    param[1].is_null = 0;
    param[1].length = NULL;

    if (mysql_stmt_bind_param(stmt, param)) {
        fprintf(stderr, "Bind Error: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(conn);
        return;
    }

    // Execute the statement
    if (mysql_stmt_execute(stmt)) {
        fprintf(stderr, "Execute Error: %s\n", mysql_stmt_error(stmt));
    } else {
        long affected_rows = mysql_stmt_affected_rows(stmt);
        printf("Row inserted: %ld\n", affected_rows);
    }

    // Clean up
    mysql_stmt_close(stmt);
    mysql_close(conn);
}