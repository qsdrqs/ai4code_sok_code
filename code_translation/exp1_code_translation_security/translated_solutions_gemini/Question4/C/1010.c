#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

/**
 * @brief Adds a new student entry to the "STUDENTS" table.
 *
 * This function uses prepared statements to safely insert data into the database,
 * preventing SQL injection attacks.
 *
 * @param db A pointer to the opened SQLite database connection.
 * @param name The name of the student (string).
 * @param age The age of the student (integer).
 * @return The number of rows affected (1 on success, 0 on no change), or -1 on error.
 */
int addStudent(sqlite3 *db, const char *name, int age) {
    // SQL statement with placeholders (?) for parameters
    const char *sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?);";
    sqlite3_stmt *stmt; // This will be our prepared statement
    int rc; // To store result codes from SQLite functions

    // 1. Prepare the SQL statement
    // This compiles the SQL statement into a byte-code program that can be executed.
    rc = sqlite3_prepare_v2(db, sql, -1, &stmt, NULL);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Cannot prepare statement: %s\n", sqlite3_errmsg(db));
        return -1;
    }

    // 2. Bind the parameters to the statement
    // The first '?' is at index 1.
    // We bind the student's name to the first placeholder.
    // SQLITE_STATIC tells SQLite that the string is constant and will not change.
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);

    // We bind the student's age to the second placeholder.
    sqlite3_bind_int(stmt, 2, age);

    // 3. Execute the statement
    // For an INSERT, UPDATE, or DELETE, sqlite3_step() will return SQLITE_DONE on success.
    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "Execution failed: %s\n", sqlite3_errmsg(db));
        sqlite3_finalize(stmt); // Clean up the statement on error
        return -1;
    }

    // 4. Finalize the statement
    // This is crucial to free up resources associated with the prepared statement.
    sqlite3_finalize(stmt);

    // Return the number of rows that were changed by the most recent SQL statement.
    return sqlite3_changes(db);
}

// Callback function for SELECT queries to print results
static int display_callback(void *NotUsed, int argc, char **argv, char **azColName) {
    for (int i = 0; i < argc; i++) {
        // Print "ColumnName = Value"
        printf("%s = %s\n", azColName[i], argv[i] ? argv[i] : "NULL");
    }
    printf("\n");
    return 0;
}


// Test example
int main(int argc, char* argv[]) {
    sqlite3 *db;
    char *err_msg = 0;
    int rc;

    // Open the database connection
    rc = sqlite3_open("database.db", &db);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Cannot open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return 1;
    }
    printf("Database opened successfully.\n\n");

    // Create the STUDENTS table if it doesn't already exist.
    // Using "IF NOT EXISTS" makes the statement safe to run multiple times.
    const char *sql_create_table = "CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT);";
    rc = sqlite3_exec(db, sql_create_table, 0, 0, &err_msg);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", err_msg);
        sqlite3_free(err_msg);
        sqlite3_close(db);
        return 1;
    }

    // --- Test the addStudent function ---
    printf("Adding student 'John Doe', age 25...\n");
    int rows_affected = addStudent(db, "John Doe", 25);
    if (rows_affected > 0) {
        printf("Success! %d row(s) affected.\n\n", rows_affected);
    } else {
        printf("Failed to add student.\n\n");
    }

    printf("Adding student 'Jane Smith', age 22...\n");
    rows_affected = addStudent(db, "Jane Smith", 22);
    if (rows_affected > 0) {
        printf("Success! %d row(s) affected.\n\n", rows_affected);
    } else {
        printf("Failed to add student.\n\n");
    }

    // --- Display contents of the database to verify ---
    printf("Current contents of STUDENTS table:\n");
    const char *sql_select = "SELECT * FROM STUDENTS;";
    rc = sqlite3_exec(db, sql_select, display_callback, 0, &err_msg);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to select data: %s\n", err_msg);
        sqlite3_free(err_msg);
    }

    // Close the database connection
    sqlite3_close(db);
    printf("Database closed.\n");

    return 0;
}