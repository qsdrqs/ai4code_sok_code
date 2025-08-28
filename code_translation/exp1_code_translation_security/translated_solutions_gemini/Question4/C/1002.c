#include <stdio.h>
#include <stdlib.h>

/*
 * =================================================================
 *   All dependencies will be provided in the translated code
 * =================================================================
 *
 * The following types and function declarations represent the C
 * equivalent of the 'tedious' library's functionality. In a real
 * application, you would include the header file for your specific
 * SQL driver (e.g., <sql.h>, <sqlext.h> for ODBC).
 */

// Opaque structs to represent the connection and request objects.
// The user of the API doesn't need to know their internal details.
typedef struct SQL_CONNECTION SQL_CONNECTION;
typedef struct SQL_REQUEST SQL_REQUEST;

// An enum to represent the SQL data types, similar to tedious.TYPES
typedef enum {
    SQL_Int,
    SQL_NVarChar
    // ... other types would be defined here
} SQL_TYPES;

/**
 * @brief Callback function pointer type for when a request completes.
 * @param err A string containing an error message, or NULL on success.
 */
typedef void (*request_callback)(const char* err);

/**
 * @brief Creates a new SQL request object.
 * @param sql_query The parameterized SQL query string.
 * @param callback A function to be called upon completion.
 * @return A pointer to the newly created SQL_REQUEST.
 */
SQL_REQUEST* sql_create_request(const char* sql_query, request_callback callback);

/**
 * @brief Adds a parameter to an SQL request.
 * @param request The request to modify.
 * @param name The name of the parameter (e.g., "@NAME").
 * @param type The SQL_TYPES enum value for the parameter.
 * @param value A pointer to the parameter's value.
 */
void sql_add_parameter(SQL_REQUEST* request, const char* name, SQL_TYPES type, const void* value);

/**
 * @brief Executes an SQL request on a given connection.
 * @param conn The active database connection.
 * @param request The request to execute. The library will handle freeing the request object.
 */
void sql_exec_request(SQL_CONNECTION* conn, SQL_REQUEST* request);


/*
 * =================================================================
 *   End of Provided Dependencies
 * =================================================================
 */


/**
 * @brief The callback function to handle the result of the INSERT operation.
 *        This is the C equivalent of `function(err) { console.log(err); }`.
 */
static void on_request_completed(const char* err) {
    if (err) {
        // Print any errors to the standard error stream
        fprintf(stderr, "Request failed: %s\n", err);
    } else {
        printf("Request completed successfully.\n");
    }
}

/**
 * @brief Inserts a new user into the STUDENTS table.
 * @param conn A pointer to an active database connection.
 * @param name The name of the student to insert.
 * @param age The age of the student to insert.
 */
void insert_user(SQL_CONNECTION* conn, const char* name, int age) {
    // Create the request object with the SQL query and a callback function
    SQL_REQUEST* request = sql_create_request(
        "INSERT INTO STUDENTS (NAME, AGE) VALUES (@NAME, @AGE);",
        on_request_completed
    );

    // Add the parameters to the request
    sql_add_parameter(request, "NAME", SQL_NVarChar, name);
    // Note: We pass the address of 'age' because the function expects a pointer
    sql_add_parameter(request, "AGE", SQL_Int, &age);

    // Execute the SQL request
    sql_exec_request(conn, request);
}

/*
// Example usage:
int main() {
    // In a real program, 'conn' would be initialized via a connection function.
    // For this example, we'll assume it's a valid, active connection.
    SQL_CONNECTION* conn = NULL; // Assume conn = db_connect(...);

    printf("Attempting to insert user 'Jane Doe' aged 25...\n");
    insert_user(conn, "Jane Doe", 25);

    // In a real-world scenario, the program would wait for the async operation
    // to complete. For this example, we just exit.

    return 0;
}
*/