#include <stdio.h>
#include <string.h>
#include <mysql.h> // Assumed to be provided, as per the request.

/*
 * @brief Adds a new student record to the database.
 * @param connection A pointer to an active MySQL connection object.
 * @param name The name of the student.
 * @param age The age of the student.
 */
void add(MYSQL *connection, const char *name, int age) {
    MYSQL_STMT *stmt;
    MYSQL_BIND params[2];
    const char *query = "INSERT INTO students (name, age) VALUES (?, ?)";

    // Prepare the SQL statement
    stmt = mysql_stmt_init(connection);
    if (!stmt) {
        fprintf(stderr, "Error: mysql_stmt_init() failed\n");
        return;
    }

    if (mysql_stmt_prepare(stmt, query, strlen(query))) {
        fprintf(stderr, "Error: mysql_stmt_prepare() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        return;
    }

    // Zero out the parameter-binding structure
    memset(params, 0, sizeof(params));

    // Bind the 'name' parameter (string)
    params[0].buffer_type = MYSQL_TYPE_STRING;
    params[0].buffer = (char *)name;
    params[0].buffer_length = strlen(name);

    // Bind the 'age' parameter (integer)
    params[1].buffer_type = MYSQL_TYPE_LONG; // MYSQL_TYPE_LONG is used for standard integers
    params[1].buffer = &age;
    params[1].is_unsigned = 0;

    // Bind the parameters to the statement
    if (mysql_stmt_bind_param(stmt, params)) {
        fprintf(stderr, "Error: mysql_stmt_bind_param() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        return;
    }

    // Execute the statement
    // This is the point where the query is actually run on the database.
    if (mysql_stmt_execute(stmt)) {
        // This block is equivalent to the `if (err) throw err;` in JavaScript.
        fprintf(stderr, "Error: mysql_stmt_execute() failed: %s\n", mysql_stmt_error(stmt));
        mysql_stmt_close(stmt);
        return;
    }

    // Corresponds to the success callback in the JavaScript version.
    printf("Added %s to students table\n", name);

    // Clean up and close the statement
    mysql_stmt_close(stmt);
}