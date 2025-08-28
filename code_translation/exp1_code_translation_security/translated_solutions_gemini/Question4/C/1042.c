#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// =============================================================================
// Mock Database Library (Provided Dependencies)
// In a real application, you would include the header for a library like
// libmysqlclient, libpq (for PostgreSQL), or ODBC.
// =============================================================================

// A structure to represent the result of a query.
// In a real library, this would contain rows, affected counts, etc.
typedef struct {
    int records_affected;
} SQL_RESULT;

// A structure to represent the database connection.
// In a real library, this would hold the socket, credentials, etc.
typedef struct {
    const char* host;
    int port;
} SQL_CONNECTION;

// A function pointer type for the callback.
// It takes an error message (or NULL if no error) and a result pointer.
typedef void (*query_callback)(const char* err, SQL_RESULT* result);

/**
 * @brief Simulates executing an SQL query asynchronously.
 *
 * In a real library, this function would send the query to the database
 * and invoke the callback when the response is received.
 * Here, we just invoke the callback immediately to simulate the flow.
 *
 * @param conn The database connection.
 * @param sql The SQL query string to execute.
 * @param callback The function to call upon completion.
 */
void connection_query(SQL_CONNECTION* conn, const char* sql, query_callback callback) {
    printf("Executing SQL: %s\n", sql);

    // Simulate a successful query result.
    SQL_RESULT result;
    result.records_affected = 1;

    // To simulate an error, you could uncomment the following line:
    // callback("Error: Duplicate entry for key 'PRIMARY'", NULL);

    // If no error, call the callback with NULL for the error argument.
    callback(NULL, &result);
}

// =============================================================================
// End of Mock Database Library
// =============================================================================


/**
 * @brief The callback function that handles the result of the INSERT query.
 * This is the C equivalent of the `function(err, result){...}` in JavaScript.
 *
 * @param err A string describing the error, or NULL if the query was successful.
 * @param result A pointer to the result structure.
 */
void add_student_callback(const char* err, SQL_RESULT* result) {
    // In JS: if (err) throw err;
    if (err) {
        fprintf(stderr, "Database query failed: %s\n", err);
        exit(EXIT_FAILURE); // Exits the program, similar to an uncaught exception.
    }

    // In JS: console.log("1 record inserted");
    // We can use the result struct for a more dynamic message.
    if (result) {
        printf("%d record inserted.\n", result->records_affected);
    }
}

/**
 * @brief Constructs an SQL INSERT statement and executes it.
 *
 * @param connection A pointer to the active database connection.
 * @param name The name of the student to add.
 * @param age The age of the student to add.
 */
void addStudent(SQL_CONNECTION* connection, const char* name, int age) {
    // Allocate a buffer for the SQL query string.
    // Using snprintf is the safe C equivalent of string concatenation for this task.
    // 256 bytes is a generous size for this specific query.
    char sql[256];

    // In JS: var sql = "INSERT INTO ...";
    int chars_written = snprintf(sql, sizeof(sql),
                                 "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)",
                                 name, age);

    // Check for truncation, which would be a security and logic error.
    if (chars_written >= sizeof(sql)) {
        fprintf(stderr, "Error: SQL query buffer was too small.\n");
        return;
    }

    // In JS: connection.query(sql, function (err, result) { ... });
    // In C, we call the query function and pass a pointer to our callback function.
    connection_query(connection, sql, add_student_callback);
}

// Main function to demonstrate the usage of addStudent.
int main() {
    // "Connect" to the database (create and initialize the connection object).
    SQL_CONNECTION* my_connection = (SQL_CONNECTION*)malloc(sizeof(SQL_CONNECTION));
    if (my_connection == NULL) {
        return EXIT_FAILURE;
    }
    my_connection->host = "localhost";
    my_connection->port = 3306;

    printf("Adding a student...\n");
    addStudent(my_connection, "Alice", 22);

    printf("\nAdding another student...\n");
    addStudent(my_connection, "Bob", 25);

    // Clean up the connection object.
    free(my_connection);

    return 0;
}