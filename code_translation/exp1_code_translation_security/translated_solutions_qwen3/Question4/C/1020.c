#include <mysql.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/**
 * Inserts a student's name and age into the STUDENTS table.
 *
 * @param name - The name of the student (C string).
 * @param age  - The age of the student (integer).
 */
void inserter(char *name, int age) {
    MYSQL *con = mysql_init(NULL);
    if (!con) {
        fprintf(stderr, "MySQL initialization failed\n");
        exit(EXIT_FAILURE);
    }

    // Connect to the MySQL database
    if (!mysql_real_connect(con, "localhost", "yourusername", "yourpassword", "mydb", 0, NULL, 0)) {
        fprintf(stderr, "MySQL connection error: %s\n", mysql_error(con));
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    printf("Connected!\n");

    // SQL statement with placeholders for parameterized query
    const char *sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
    MYSQL_STMT *stmt = mysql_stmt_init(con);
    if (!stmt) {
        fprintf(stderr, "Statement initialization failed\n");
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    // Prepare the SQL statement
    if (mysql_stmt_prepare(stmt, sql, strlen(sql)) != 0) {
        fprintf(stderr, "Statement preparation failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    // Bind parameters to the prepared statement
    MYSQL_BIND bind[2];
    memset(bind, 0, sizeof(bind));

    // First parameter: name (string)
    bind[0].buffer_type = MYSQL_TYPE_STRING;
    bind[0].buffer = name;
    bind[0].buffer_length = strlen(name);
    bind[0].is_null = 0;

    // Second parameter: age (long)
    bind[1].buffer_type = MYSQL_TYPE_LONG;
    bind[1].buffer = (void *)&age;
    bind[1].is_null = 0;

    if (mysql_stmt_bind_param(stmt, bind) != 0) {
        fprintf(stderr, "Binding parameters failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    // Execute the prepared statement
    if (mysql_stmt_execute(stmt) != 0) {
        fprintf(stderr, "Execution failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    printf("1 record inserted\n");

    // Clean up
    mysql_stmt_close(stmt);
    mysql_close(con);
}