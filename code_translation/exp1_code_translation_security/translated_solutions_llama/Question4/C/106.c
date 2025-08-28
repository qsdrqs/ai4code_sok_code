#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "sqlite3.h"

/**
 * Adds a new entry to the "STUDENTS" table in the given database.
 *
 * @param name The name of the student.
 * @param age The age of the student.
 * @param db A pointer to the SQLite database connection.
 *
 * @return 0 on success, non-zero on failure.
 */
int addEntry(const char* name, int age, sqlite3* db) {
    // Prepare the SQL query
    char sql[256];
    snprintf(sql, sizeof(sql), "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);

    // Execute the SQL query
    char* errorMessage;
    int rc = sqlite3_exec(db, sql, NULL, NULL, &errorMessage);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errorMessage);
        sqlite3_free(errorMessage);
    }

    return rc;
}

int main() {
    // Example usage
    sqlite3* db;
    int rc = sqlite3_open("example.db", &db);
    if (rc) {
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        return 1;
    }

    // Create table if it doesn't exist
    char* errorMessage;
    rc = sqlite3_exec(db, "CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INTEGER)", NULL, NULL, &errorMessage);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errorMessage);
        sqlite3_free(errorMessage);
    }

    // Add a new entry
    rc = addEntry("John Doe", 20, db);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to add entry\n");
        return 1;
    }

    sqlite3_close(db);
    return 0;
}