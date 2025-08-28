#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// =============================================================================
// Dependencies (Mock Implementation)
// As per the request, these are provided to make the code self-contained.
// In a real-world scenario, you would use a library like libpq or ODBC.
// =============================================================================

// Represents a database connection object.
typedef struct {
    int is_connected; // A simple flag to simulate connection state.
} Connection;

// Simulates creating a new database connection.
// In C, we often use create/destroy functions instead of constructors.
Connection Connection_create() {
    printf("--> Simulating: Opening database connection...\n");
    Connection conn;
    conn.is_connected = 1; // Mark as connected.
    return conn;
}

// Simulates executing a query.
// Returns 1 for success, 0 for failure.
int Connection_query(Connection* conn, const char* query) {
    if (!conn || !conn->is_connected) {
        fprintf(stderr, "--> Simulating ERROR: No active connection.\n");
        return 0; // Failure
    }
    printf("--> Simulating: Executing query: \"%s\"\n", query);
    // In a real application, this would return the number of affected rows or a result set.
    return 1; // Success
}

// Simulates closing the connection.
void Connection_close(Connection* conn) {
    if (conn) {
        printf("--> Simulating: Closing database connection.\n");
        conn->is_connected = 0;
    }
}

// =============================================================================
// Translated Code
// =============================================================================

// In C, it's good practice to group related data into a struct,
// similar to the JavaScript object.
typedef struct {
    const char* name;
    int age;
} Student;

/**
 * @brief Registers a new student in the database.
 *
 * @param name The name of the student.
 * @param age The age of the student.
 * @return int Returns 1 on success, 0 on failure.
 */
int registerStudent(const char* name, int age) {
    // The JavaScript object is analogous to a C struct.
    Student student = { name, age };

    // Create the connection object on the stack.
    Connection connection = Connection_create();

    // Construct the query string.
    // We must allocate a buffer for the query. Using snprintf is safer
    // than sprintf to prevent buffer overflows.
    char query[256];
    snprintf(query, sizeof(query), "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)",
             student.name, student.age);

    // Execute the query and get the result.
    int result = Connection_query(&connection, query);

    // It's crucial in C to manage resources manually.
    // We close the connection when we're done with it.
    Connection_close(&connection);

    return result;
}

// =============================================================================
// Example Usage
// =============================================================================

int main() {
    printf("Attempting to register 'Alice'...\n");
    int status = registerStudent("Alice", 21);

    if (status == 1) {
        printf("Registration successful!\n");
    } else {
        printf("Registration failed.\n");
    }

    printf("\n");

    printf("Attempting to register 'Bob'...\n");
    status = registerStudent("Bob", 25);

    if (status == 1) {
        printf("Registration successful!\n");
    } else {
        printf("Registration failed.\n");
    }

    return 0;
}