#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>

void add(const char *name, int age) {
    MYSQL *conn;
    MYSQL_STMT *stmt;
    MYSQL_BIND bind[2];
    const char *query = "INSERT INTO students (name, age) VALUES (?, ?)";
    
    // Initialize MySQL connection
    conn = mysql_init(NULL);
    if (conn == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        exit(EXIT_FAILURE);
    }

    // Connect to the database
    if (mysql_real_connect(conn, "host", "user", "password", "database", 0, NULL, 0) == NULL) {
        fprintf(stderr, "mysql_real_connect() failed\n");
        mysql_close(conn);
        exit(EXIT_FAILURE);
    }

    // Prepare the statement
    stmt = mysql_stmt_init(conn);
    if (stmt == NULL) {
        fprintf(stderr, "mysql_stmt_init() failed\n");
        mysql_close(conn);
        exit(EXIT_FAILURE);
    }

    if (mysql_stmt_prepare(stmt, query, strlen(query)) != 0) {
        fprintf(stderr, "mysql_stmt_prepare() failed\n");
        mysql_stmt_close(stmt);
        mysql_close(conn);
        exit(EXIT_FAILURE);
    }

    // Bind the parameters
    memset(bind, 0, sizeof(bind));

    // Bind the name parameter
    bind[0].buffer_type = MYSQL_TYPE_STRING;
    bind[0].buffer = (char *)name;
    bind[0].buffer_length = strlen(name);

    // Bind the age parameter
    bind[1].buffer_type = MYSQL_TYPE_LONG;
    bind[1].buffer = (char *)&age;
    bind[1].is_unsigned = 0;

    if (mysql_stmt_bind_param(stmt, bind) != 0) {
        fprintf(stderr, "mysql_stmt_bind_param() failed\n");
        mysql_stmt_close(stmt);
        mysql_close(conn);
        exit(EXIT_FAILURE);
    }

    // Execute the statement
    if (mysql_stmt_execute(stmt) != 0) {
        fprintf(stderr, "mysql_stmt_execute() failed\n");
        mysql_stmt_close(stmt);
        mysql_close(conn);
        exit(EXIT_FAILURE);
    }

    printf("Added %s to students table\n", name);

    // Clean up
    mysql_stmt_close(stmt);
    mysql_close(conn);
}

int main() {
    add("John Doe", 20);
    return 0;
}