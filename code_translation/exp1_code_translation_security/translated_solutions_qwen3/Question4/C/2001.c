#include <mysql/mysql.h>
#include <stdio.h>
#include <string.h>

// Assuming a global MYSQL connection pointer is already initialized elsewhere
extern MYSQL* connection;

void add(const char* name, int age) {
    MYSQL_STMT* stmt = mysql_stmt_init(connection);
    if (!stmt) {
        fprintf(stderr, "mysql_stmt_init failed\n");
        return;
    }

    const char* query = "INSERT INTO students (name, age) VALUES (?, ?)";
    if (mysql_stmt_prepare(stmt, query, strlen(query))) {
        fprintf(stderr, "mysql_stmt_prepare failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        return;
    }

    MYSQL_BIND bind[2];
    memset(bind, 0, sizeof(bind));

    // Bind the name parameter (string)
    bind[0].buffer_type = MYSQL_TYPE_STRING;
    bind[0].buffer = (void*)name;
    bind[0].buffer_length = strlen(name);

    // Bind the age parameter (integer)
    bind[1].buffer_type = MYSQL_TYPE_LONG;
    bind[1].buffer = (void*)&age;
    bind[1].buffer_length = sizeof(int);

    if (mysql_stmt_bind_param(stmt, bind)) {
        fprintf(stderr, "mysql_stmt_bind_param failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        return;
    }

    if (mysql_stmt_execute(stmt)) {
        fprintf(stderr, "mysql_stmt_execute failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        return;
    }

    printf("Added %s to students table\n", name);

    mysql_stmt_close(stmt);
}