#include <stdio.h>
#include <stdlib.h> // For exit()
#include <string.h>

/*
================================================================================
 Mock Database Dependencies
 In a real-world C application, you would use a library like libmysqlclient,
 libpq (for PostgreSQL), or ODBC. These functions simulate the behavior
 of the JavaScript 'connection' object.
================================================================================
*/

// A mock structure to represent the database connection.
// In a real library, this would hold connection details (host, user, socket, etc.).
typedef struct {
    // Connection-specific data would go here.
    int is_connected;
} SQL_Connection;

// Define a function pointer type for our callback.
// It matches the JS callback signature: function(err, result)
// We use const char* for the error message and void* for a generic result.
typedef void (*sql_callback)(const char* err, void* result);

/**
 * @brief Simulates executing a SQL query asynchronously.
 *
 * In a real implementation, this would send the query to the database server
 * and invoke the callback when a response is received. Here, we just call
 * it immediately to simulate a successful operation.
 *
 * @param conn The database connection object.
 * @param sql The SQL query string to execute.
 * @param callback The function to call upon completion.
 */
void connection_query(SQL_Connection* conn, const char* sql, sql_callback callback) {
    // Simulate a successful query execution by calling the callback
    // with NULL as the error argument. The result is also NULL as it's not used.
    if (conn && conn->is_connected) {
        // In a real scenario, the database interaction would happen here.
        callback(NULL, NULL);
    } else {
        // Simulate a connection error.
        callback("Database not connected.", NULL);
    }
}

/*
================================================================================
 Translated Code
================================================================================
*/

// Forward declaration of the global connection object, similar to the JS environment.
SQL_Connection* connection;

/**
 * @brief The callback function to handle the result of the add query.
 * This is the C equivalent of the anonymous function: function (err, result) { ... }
 */
void on_add_complete(const char* err, void* result) {
    // The C equivalent of "if (err) throw err;" is to check for a non-NULL error,
    // print to standard error, and exit the program.
    if (err) {
        fprintf(stderr, "FATAL QUERY ERROR: %s\n", err);
        exit(EXIT_FAILURE);
    }
    printf("1 record inserted\n");
}

/**
 * @brief Adds a new entry to the SQL table "STUDENTS".
 *
 * @param name The name of the student.
 * @param age The age of the student.
 */
void add(const char* name, int age) {
    // We need a buffer to construct the SQL string.
    // Using snprintf is the safe C equivalent of string concatenation for this purpose,
    // as it prevents buffer overflows.
    char sql[256];

    // WARNING: Like the original JavaScript, this method of building a query
    // is vulnerable to SQL Injection. A real application should use prepared
    // statements to safely handle user input.
    snprintf(sql, sizeof(sql), "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);

    // The C equivalent of console.log(sql);
    printf("%s\n", sql);

    // Call the query function with the SQL string and our callback function.
    connection_query(connection, sql, on_add_complete);
}

// Main function to demonstrate the usage.
int main() {
    // Initialize the mock database connection.
    SQL_Connection db_conn;
    db_conn.is_connected = 1;
    connection = &db_conn;

    printf("Adding a new student...\n");
    add("John Doe", 30);

    printf("\nAdding another student...\n");
    add("Jane Smith", 25);

    return 0;
}