#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

// Forward declarations of the functions to be used.
// This is similar to how functions are available in the global scope in the JS example.
void createDatabase();
void createTables2(sqlite3 *db);
void insert(sqlite3 *db, const char *name, int age);

/*
 * This is the C equivalent of the main IIFE (Immediately Invoked Function Expression).
 * It serves as the entry point for the program.
 */
int main() {
    createDatabase();
    return 0;
}

/**
 * @brief Opens or creates a new SQLite database file.
 *
 * This function corresponds to the JavaScript `createDatabase` function.
 * In C, sqlite3_open is a synchronous call. It blocks until the database is
 * opened or an error occurs. If successful, it proceeds to call createTables2,
 * mimicking the JS callback chain.
 */
void createDatabase() {
    printf("created database\n");
    sqlite3 *db;
    int rc; // To store return codes from sqlite3 functions

    // Attempt to open the database file "temp.db"
    rc = sqlite3_open("temp.db", &db);

    if (rc) {
        // If sqlite3_open returns a non-zero value, an error occurred.
        fprintf(stderr, "Getting error: Can't open database: %s\n", sqlite3_errmsg(db));
        exit(1);
    }

    // If the database is opened successfully, proceed to create the tables.
    // This is the C equivalent of the successful callback path.
    createTables2(db);

    // Close the database connection to free resources.
    sqlite3_close(db);
}

/**
 * @brief Creates the 'students' table in the database.
 *
 * This function corresponds to the JavaScript `createTables2` function.
 * It uses sqlite3_exec, a convenience function for running simple SQL statements.
 * After creating the table, it calls the insert function, continuing the chain.
 */
void createTables2(sqlite3 *db) {
    printf("pre-created tables\n");
    char *sql = "CREATE TABLE IF NOT EXISTS students(name TEXT, age INT);";
    char *zErrMsg = 0;
    int rc;

    // Execute the SQL statement.
    // The callback arguments (3rd and 4th) are NULL because we don't need to process results.
    rc = sqlite3_exec(db, sql, 0, 0, &zErrMsg);

    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", zErrMsg);
        sqlite3_free(zErrMsg); // Free the error message memory
    } else {
        // On success, call the next function in the logical chain.
        insert(db, "John Doe", 30);
    }
}

/**
 * @brief Inserts a new row into the 'students' table.
 *
 * This function corresponds to the JavaScript `insert` function.
 * It uses prepared statements (sqlite3_prepare_v2, sqlite3_bind_*, sqlite3_step)
 * which is the recommended way to execute SQL with parameters, as it prevents
 * SQL injection vulnerabilities.
 */
void insert(sqlite3 *db, const char *name, int age) {
    sqlite3_stmt *stmt;
    const char *sql = "INSERT INTO students(name, age) VALUES (?, ?);";
    int rc;

    // Prepare the SQL statement
    rc = sqlite3_prepare_v2(db, sql, -1, &stmt, NULL);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare statement: %s\n", sqlite3_errmsg(db));
        return;
    }

    // Bind the parameters to the statement.
    // The index is 1-based.
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 2, age);

    // Execute the statement
    rc = sqlite3_step(stmt);

    // The callback logic from JS is now a simple if/else block.
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "Execution failed: %s\n", sqlite3_errmsg(db));
    } else {
        printf("row added successfully\n");
    }

    // Finalize the statement to release resources.
    sqlite3_finalize(stmt);
}