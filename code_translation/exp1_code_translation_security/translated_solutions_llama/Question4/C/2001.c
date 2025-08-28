#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to add a student to the database
void add_student(MYSQL *connection, const char *name, int age) {
    // Prepare the query
    const char *query = "INSERT INTO students (name, age) VALUES (?, ?)";

    // Prepare parameters
    MYSQL_STMT *stmt;
    MYSQL_BIND param[2];

    // Initialize statement
    stmt = mysql_stmt_init(connection);
    if (!stmt) {
        fprintf(stderr, "mysql_stmt_init(), out of memory\n");
        return;
    }

    // Prepare statement
    if (mysql_stmt_prepare(stmt, query, strlen(query))) {
        fprintf(stderr, "mysql_stmt_prepare(), You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near '?' at line 1\n");
        mysql_stmt_free_result(stmt);
        mysql_stmt_close(stmt);
        return;
    }

    // Bind parameters
    memset(param, 0, sizeof(param));

    param[0].buffer_type = MYSQL_TYPE_STRING;
    param[0].buffer = (void *)name;
    param[0].buffer_length = strlen(name);
    param[0].is_null = 0;
    param[0].length = 0;

    param[1].buffer_type = MYSQL_TYPE_LONG;
    param[1].buffer = &age;
    param[1].buffer_length = 0;
    param[1].is_null = 0;
    param[1].length = 0;

    if (mysql_stmt_bind_param(stmt, param)) {
        fprintf(stderr, "mysql_stmt_bind_param(), You have an error in your SQL syntax\n");
        mysql_stmt_free_result(stmt);
        mysql_stmt_close(stmt);
        return;
    }

    // Execute statement
    if (mysql_stmt_execute(stmt)) {
        fprintf(stderr, "mysql_stmt_execute(), You have an error in your SQL syntax\n");
        mysql_stmt_free_result(stmt);
        mysql_stmt_close(stmt);
        return;
    }

    // Free resources
    mysql_stmt_free_result(stmt);
    mysql_stmt_close(stmt);

    printf("Added %s to students table\n", name);
}

int main() {
    MYSQL *connection;
    MYSQL_RES *result;
    MYSQL_ROW row;

    // Initialize MySQL library
    mysql_library_init(0, NULL, NULL);

    // Create a connection
    connection = mysql_init(NULL);

    if (!mysql_real_connect(connection, "localhost", "username", "password", "database", 0, NULL, 0)) {
        fprintf(stderr, "%s\n", mysql_error(connection));
        mysql_close(connection);
        mysql_library_end();
        return 1;
    }

    // Add a student
    add_student(connection, "John Doe", 25);

    // Close connection
    mysql_close(connection);

    // Clean up
    mysql_library_end();

    return 0;
}