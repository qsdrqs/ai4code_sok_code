#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void insert(char *name, int age) {
    MYSQL *con;
    MYSQL_STMT *stmt;
    const char *query = "INSERT INTO students (name, age) VALUES (?, ?)";

    // Initialize MySQL connection
    con = mysql_init(NULL);
    if (con == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(EXIT_FAILURE);
    }

    // Connect to the database
    if (!mysql_real_connect(con, "localhost", "yourusername", "yourpassword", "STUDENTS", 0, NULL, 0)) {
        fprintf(stderr, "Connection error: %s\n", mysql_error(con));
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    printf("Connected!\n");

    // Initialize prepared statement
    stmt = mysql_stmt_init(con);
    if (!stmt) {
        fprintf(stderr, "mysql_stmt_init() failed\n");
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    // Prepare the INSERT statement
    if (mysql_stmt_prepare(stmt, query, strlen(query)) != 0) {
        fprintf(stderr, "mysql_stmt_prepare() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    // Bind parameters
    MYSQL_BIND bind[2];
    memset(bind, 0, sizeof(bind));

    // First parameter: name (string)
    bind[0].buffer_type = MYSQL_TYPE_STRING;
    bind[0].buffer = name;
    bind[0].buffer_length = strlen(name);
    bind[0].is_null = 0;

    // Second parameter: age (long)
    bind[1].buffer_type = MYSQL_TYPE_LONG;
    bind[1].buffer = &age;
    bind[1].is_null = 0;

    if (mysql_stmt_bind_param(stmt, bind) != 0) {
        fprintf(stderr, "mysql_stmt_bind_param() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    // Execute the prepared statement
    if (mysql_stmt_execute(stmt) != 0) {
        fprintf(stderr, "mysql_stmt_execute() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    printf("1 record inserted\n");

    // Cleanup
    mysql_stmt_close(stmt);
    mysql_close(con);
}