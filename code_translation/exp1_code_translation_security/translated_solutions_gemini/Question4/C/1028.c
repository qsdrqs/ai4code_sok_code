#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// =============================================================================
// MOCK DATABASE DEPENDENCIES (As if provided by a library like libmysql.h)
// =============================================================================

// An opaque structure representing the database connection.
typedef struct DB_Connection DB_Connection;

// A structure to hold the result of a query.
typedef struct {
    int affected_rows;
    // Other fields like insert_id could be here.
} DB_Result;

// Define the function pointer type for a callback.
// It takes an error code, an error message, and a result struct.
typedef void (*db_callback_t)(int err_code, const char* err_message, DB_Result* result);

// A mock function to simulate executing a query asynchronously.
// In a real library, this would send the query to the database server.
void connection_query(DB_Connection* conn, const char* sql, db_callback_t callback);

// A global connection object, similar to the JS example.
// In a real application, this would be initialized by a connect function.
DB_Connection* connection = NULL; 

// =============================================================================
// TRANSLATED CODE
// =============================================================================

/**
 * @brief The callback function to handle the result of the addStudent query.
 * This is the C equivalent of `function (err, result) { ... }`.
 */
void add_student_callback(int err_code, const char* err_message, DB_Result* result) {
    if (err_code != 0) {
        // Equivalent to "if (err) throw err;"
        fprintf(stderr, "Database query failed: %s\n", err_message);
        exit(EXIT_FAILURE); // 'throw' halts execution, so we exit.
    }
    
    // Equivalent to "console.log("1 record inserted");"
    printf("1 record inserted\n");
    
    // In a real scenario, you might free the result object here.
    // free(result); 
}

/**
 * @brief Adds a new student to the database.
 * @param name The name of the student.
 * @param age The age of the student.
 */
void addStudent(const char* name, int age) {
    // C requires a pre-allocated buffer for strings.
    // We use snprintf for safe formatting to prevent buffer overflows.
    char sql[256];
    int chars_written = snprintf(sql, sizeof(sql), 
                                 "INSERT INTO STUDENTS (name, age) VALUES ('%s', %d)", 
                                 name, age);

    // Optional: Check for truncation.
    if (chars_written >= sizeof(sql)) {
        fprintf(stderr, "Error: SQL query was too long and got truncated.\n");
        return;
    }

    printf("Executing SQL: %s\n", sql);
    connection_query(connection, sql, add_student_callback);
}


// =============================================================================
// EXAMPLE USAGE AND MOCK IMPLEMENTATION
// =============================================================================

// Mock implementation of the database query function.
void connection_query(DB_Connection* conn, const char* sql, db_callback_t callback) {
    // This mock simulates a successful database operation.
    // To test the error case, you could set err_code to 1.
    int mock_err_code = 0; 
    const char* mock_err_message = "No error";
    DB_Result mock_result = { .affected_rows = 1 };

    if (strstr(sql, "DROP TABLE")) { // Simple check to simulate an error
        mock_err_code = 123;
        mock_err_message = "Execution denied for command DROP TABLE";
    }

    // In a real async library, this callback would be invoked later
    // when the database responds. Here, we call it immediately.
    callback(mock_err_code, mock_err_message, &mock_result);
}

int main() {
    // In a real app, you would connect to the database here.
    // connection = db_connect("host", "user", "pass", "db");
    printf("--- Running Direct (Unsafe) Translation ---\n");
    addStudent("Alice", 30);
    
    // This demonstrates the SQL injection vulnerability.
    // The name contains code that maliciously alters the SQL query.
    printf("\n--- Demonstrating SQL Injection Vulnerability ---\n");
    addStudent("Robert'); DROP TABLE STUDENTS; --", 25);

    // In a real app, you would disconnect here.
    // db_disconnect(connection);
    return 0;
}