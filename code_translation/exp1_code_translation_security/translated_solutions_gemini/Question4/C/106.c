#include <stdio.h>
#include <stdlib.h>

/*
================================================================================
 Dependencies (Imagined for a more robust, secure API)
================================================================================
*/

// Opaque struct for the database connection.
typedef struct DatabaseConnection DatabaseConnection;

// Opaque struct for a prepared statement.
typedef struct PreparedStatement PreparedStatement;

// Placeholder functions for a secure database API.
// These mimic real-world libraries like SQLite3.

// Prepares an SQL statement for execution. '?' are placeholders.
PreparedStatement* db_prepare(DatabaseConnection* db, const char* sql_template) {
    printf("Preparing statement: %s\n", sql_template);
    // In a real implementation, this would return a valid statement handle.
    // We'll just allocate a dummy pointer for the example.
    return (PreparedStatement*)malloc(sizeof(PreparedStatement));
}

// Binds a string value to a placeholder in the statement.
void db_bind_text(PreparedStatement* stmt, int index, const char* text) {
    printf("Binding text '%s' to parameter %d\n", text, index);
    // (void)stmt; // Unused parameter
}

// Binds an integer value to a placeholder in the statement.
void db_bind_int(PreparedStatement* stmt, int index, int value) {
    printf("Binding int '%d' to parameter %d\n", value, index);
    // (void)stmt; // Unused parameter
}

// Executes the prepared statement.
void db_step(PreparedStatement* stmt) {
    printf("Executing prepared statement.\n");
    // (void)stmt; // Unused parameter
}

// Cleans up and frees the prepared statement.
void db_finalize(PreparedStatement* stmt) {
    printf("Finalizing statement.\n");
    free(stmt);
}


/*
================================================================================
 Translated and Improved Code
================================================================================
*/

/**
 * @brief Securely adds a new entry to the "STUDENTS" table using parameterized queries.
 *
 * @param name The string for the "NAME" of the entry.
 * @param age The int for the "AGE" of the entry.
 * @param db A given database connection.
 */
void addEntry_safe(const char* name, int age, DatabaseConnection* db) {
    // The SQL template with '?' as placeholders for data.
    // This prevents the data from being interpreted as SQL commands.
    const char* sql_template = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
    
    // 1. Prepare the statement.
    PreparedStatement* stmt = db_prepare(db, sql_template);
    if (stmt == NULL) {
        fprintf(stderr, "Error: Failed to prepare statement.\n");
        return;
    }

    // 2. Bind the variables to the placeholders.
    // The database driver handles escaping the string, preventing injection.
    db_bind_text(stmt, 1, name); // Parameter indices are usually 1-based.
    db_bind_int(stmt, 2, age);

    // 3. Execute the final command.
    db_step(stmt);

    // 4. Clean up the statement object.
    db_finalize(stmt);
}

// Example usage:
int main() {
    DatabaseConnection* my_db = NULL; // In a real app, this would be initialized.
    
    printf("Adding 'Alice' safely...\n");
    addEntry_safe("Alice", 22, my_db);
    printf("--------------------------------\n");

    // The malicious string is now treated as simple text and inserted safely.
    // The database will contain a student with a very strange name,
    // but the DROP TABLE command will NOT be executed.
    printf("Attempting injection safely...\n");
    addEntry_safe("Robert'); DROP TABLE STUDENTS;--", 30, my_db);
    printf("--------------------------------\n");

    return 0;
}