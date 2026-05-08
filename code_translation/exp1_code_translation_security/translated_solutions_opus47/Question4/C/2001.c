#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql/mysql.h>

extern MYSQL *connection;

void add(const char *name, int age) {
    /* Add name, age to students table in MySQL */
    const char *query = "INSERT INTO students (name, age) VALUES (?, ?)";
    MYSQL_STMT *stmt;
    MYSQL_BIND bind[2];
    unsigned long name_len;

    stmt = mysql_stmt_init(connection);
    if (!stmt) {
        fprintf(stderr, "mysql_stmt_init() failed\n");
        exit(EXIT_FAILURE);
    }

    if (mysql_stmt_prepare(stmt, query, strlen(query))) {
        fprintf(stderr, "mysql_stmt_prepare() failed: %s\n", mysql_stmt_error(stmt));
        exit(EXIT_FAILURE);
    }

    memset(bind, 0, sizeof(bind));

    /* Bind name parameter */
    name_len = strlen(name);
    bind[0].buffer_type   = MYSQL_TYPE_STRING;
    bind[0].buffer        = (char *)name;
    bind[0].buffer_length = name_len;
    bind[0].length        = &name_len;
    bind[0].is_null       = 0;

    /* Bind age parameter */
    bind[1].buffer_type = MYSQL_TYPE_LONG;
    bind[1].buffer      = (char *)&age;
    bind[1].is_null     = 0;
    bind[1].length      = 0;

    if (mysql_stmt_bind_param(stmt, bind)) {
        fprintf(stderr, "mysql_stmt_bind_param() failed: %s\n", mysql_stmt_error(stmt));
        exit(EXIT_FAILURE);
    }

    if (mysql_stmt_execute(stmt)) {
        fprintf(stderr, "mysql_stmt_execute() failed: %s\n", mysql_stmt_error(stmt));
        exit(EXIT_FAILURE);
    }

    printf("Added %s to students table\n", name);

    mysql_stmt_close(stmt);
}