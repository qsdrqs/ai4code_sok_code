#include <mysql/mysql.h>
#include <stdio.h>
#include <stdlib.h>

void addStudent(MYSQL *connection, const char *name, int age) {
    // Prepare the SQL query
    const char *query = "INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)";
    
    // Initialize a statement
    MYSQL_STMT *stmt = mysql_stmt_init(connection);
    if (!stmt) {
        fprintf(stderr, "mysql_stmt_init() failed\n");
        return;
    }

    // Prepare the statement
    if (mysql_stmt_prepare(stmt, query, strlen(query))) {
        fprintf(stderr, "mysql_stmt_prepare() failed\n");
        fprintf(stderr, "%s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        return;
    }

    // Bind the parameters
    MYSQL_BIND bind[2];
    memset(bind, 0, sizeof(bind));

    // Bind the name parameter
    bind[0].buffer_type = MYSQL_TYPE_STRING;
    bind[0].buffer = (char *)name;
    bind[0].buffer_length = strlen(name);

    // Bind the age parameter
    bind[1].buffer_type = MYSQL_TYPE_LONG;
    bind[1].buffer = (char *)&age;
    bind[1].is_unsigned = 0;

    if (mysql_stmt_bind_param(stmt, bind)) {
        fprintf(stderr, "mysql_stmt_bind_param() failed\n");
        fprintf(stderr, "%s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        return;
    }

    // Execute the statement
    if (mysql_stmt_execute(stmt)) {
        fprintf(stderr, "mysql_stmt_execute() failed\n");
        fprintf(stderr, "%s\n", mysql_stmt_error(stmt));
    }

    // Close the statement
    mysql_stmt_close(stmt);
}

int main() {
    MYSQL *connection = mysql_init(NULL);
    if (connection == NULL) {
        fprintf(stderr, "mysql_init() failed\n");
        return EXIT_FAILURE;
    }

    // Connect to the database
    if (mysql_real_connect(connection, "host", "user", "password", "database", 0, NULL, 0) == NULL) {
        fprintf(stderr, "mysql_real_connect() failed\n");
        mysql_close(connection);
        return EXIT_FAILURE;
    }

    // Add a student
    addStudent(connection, "John Doe", 20);

    // Close the connection
    mysql_close(connection);
    return EXIT_SUCCESS;
}