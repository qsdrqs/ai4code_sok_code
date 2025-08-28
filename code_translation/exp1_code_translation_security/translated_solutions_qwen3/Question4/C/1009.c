#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void createEntry(char *name, int age) {
    MYSQL *con = mysql_init(NULL);
    if (con == NULL) {
        fprintf(stderr, "MySQL initialization failed\n");
        exit(EXIT_FAILURE);
    }

    // Replace the following with your actual database connection details
    if (mysql_real_connect(con, "localhost", "username", "password", "database_name", 0, NULL, 0) == NULL) {
        fprintf(stderr, "Connection failed: %s\n", mysql_error(con));
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    MYSQL_STMT *stmt = mysql_stmt_init(con);
    if (!stmt) {
        fprintf(stderr, "Statement initialization failed: %s\n", mysql_error(con));
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    const char *query = "INSERT INTO STUDENTS (name, age) VALUES (?, ?)";
    if (mysql_stmt_prepare(stmt, query, strlen(query)) != 0) {
        fprintf(stderr, "Statement preparation failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    MYSQL_BIND bind[2];
    memset(bind, 0, sizeof(bind));

    // Bind the name parameter
    bind[0].buffer_type = MYSQL_TYPE_STRING;
    bind[0].buffer = name;
    bind[0].buffer_length = strlen(name);
    bind[0].is_null = 0;

    // Bind the age parameter
    bind[1].buffer_type = MYSQL_TYPE_LONG;
    bind[1].buffer = &age;
    bind[1].is_null = 0;

    if (mysql_stmt_bind_param(stmt, bind) != 0) {
        fprintf(stderr, "Binding parameters failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    if (mysql_stmt_execute(stmt) != 0) {
        fprintf(stderr, "Statement execution failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(con);
        exit(EXIT_FAILURE);
    }

    // Clean up
    mysql_stmt_close(stmt);
    mysql_close(con);
}