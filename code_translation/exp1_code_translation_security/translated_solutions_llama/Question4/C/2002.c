#include <mysql/mysql.h>
#include <stdio.h>
#include <string.h>

// Function to add a student to the database
int addStudent(MYSQL *connection, const char *name, int age) {
    // SQL query to insert a new student
    const char *query = "INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)";

    // Prepare the query
    MYSQL_STMT *stmt;
    MYSQL_BIND param[2];

    stmt = mysql_stmt_init(connection);
    if (!stmt) {
        fprintf(stderr, "mysql_stmt_init() failed\n");
        return 1;
    }

    if (mysql_stmt_prepare(stmt, query, strlen(query))) {
        fprintf(stderr, "mysql_stmt_prepare() failed\n");
        mysql_stmt_free_result(stmt);
        return 1;
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
        fprintf(stderr, "mysql_stmt_bind_param() failed\n");
        mysql_stmt_free_result(stmt);
        return 1;
    }

    // Execute the query
    if (mysql_stmt_execute(stmt)) {
        fprintf(stderr, "mysql_stmt_execute() failed\n");
        mysql_stmt_free_result(stmt);
        return 1;
    }

    // Free resources
    mysql_stmt_free_result(stmt);

    return 0;
}

int main() {
    // Initialize MySQL library
    mysql_library_init(0, NULL, NULL);

    // Create a MySQL connection
    MYSQL *connection;
    connection = mysql_init(NULL);

    if (!mysql_real_connect(connection, "your_host", "your_user", "your_password", "your_database", 0, NULL, 0)) {
        fprintf(stderr, "mysql_real_connect() failed\n");
        mysql_close(connection);
        mysql_library_end();
        return 1;
    }

    // Add a student
    const char *name = "John Doe";
    int age = 20;
    if (addStudent(connection, name, age)) {
        fprintf(stderr, "Failed to add student\n");
    } else {
        printf("Student added successfully\n");
    }

    // Close the connection and clean up
    mysql_close(connection);
    mysql_library_end();

    return 0;
}