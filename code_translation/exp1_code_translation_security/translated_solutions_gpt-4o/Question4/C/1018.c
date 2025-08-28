#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to add a record to the database
void add(const char *name, int age) {
    MYSQL *conn;
    MYSQL_STMT *stmt;
    MYSQL_BIND bind[2];
    my_bool is_null[2] = {0, 0};
    my_bool error[2] = {0, 0};
    unsigned long length[2];

    // Initialize MySQL connection
    conn = mysql_init(NULL);
    if (conn == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        return;
    }

    // Connect to the database
    if (mysql_real_connect(conn, "host", "user", "password", "database", 0, NULL, 0) == NULL) {
        fprintf(stderr, "mysql_real_connect() failed\n");
        mysql_close(conn);
        return;
    }

    // Prepare the SQL statement
    const char *stmt_str = "INSERT INTO todos(title, completed) VALUES(?, ?)";
    stmt = mysql_stmt_init(conn);
    if (!stmt) {
        fprintf(stderr, "mysql_stmt_init() failed\n");
        mysql_close(conn);
        return;
    }

    if (mysql_stmt_prepare(stmt, stmt_str, strlen(stmt_str))) {
        fprintf(stderr, "mysql_stmt_prepare() failed\n");
        fprintf(stderr, "%s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(conn);
        return;
    }

    // Bind the data for the parameters
    memset(bind, 0, sizeof(bind));

    // Bind the name parameter
    bind[0].buffer_type = MYSQL_TYPE_STRING;
    bind[0].buffer = (char *)name;
    bind[0].buffer_length = strlen(name);
    bind[0].is_null = &is_null[0];
    bind[0].length = &length[0];
    length[0] = strlen(name);

    // Bind the age parameter
    bind[1].buffer_type = MYSQL_TYPE_LONG;
    bind[1].buffer = (char *)&age;
    bind[1].is_null = &is_null[1];
    bind[1].length = &length[1];

    if (mysql_stmt_bind_param(stmt, bind)) {
        fprintf(stderr, "mysql_stmt_bind_param() failed\n");
        fprintf(stderr, "%s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        mysql_close(conn);
        return;
    }

    // Execute the statement
    if (mysql_stmt_execute(stmt)) {
        fprintf(stderr, "mysql_stmt_execute() failed\n");
        fprintf(stderr, "%s\n", mysql_stmt_error(stmt));
    } else {
        printf("Row inserted: %lu\n", (unsigned long)mysql_stmt_affected_rows(stmt));
    }

    // Clean up
    mysql_stmt_close(stmt);
    mysql_close(conn);
}

int main() {
    add("John Doe", 30);
    return 0;
}