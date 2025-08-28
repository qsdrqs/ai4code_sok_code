#include <sqlite3.h>
#include <stdio.h>

/**
 * Adds a new entry to the STUDENTS table.
 *
 * @param name  The name of the student (string).
 * @param age   The age of the student (integer).
 * @param db    A pointer to the SQLite database connection.
 */
void addEntry(char* name, int age, sqlite3* db) {
    // Construct the SQL query using sqlite3_mprintf for dynamic string allocation
    char* sql = sqlite3_mprintf("INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);

    // Execute the SQL query
    sqlite3_exec(db, sql, NULL, NULL, NULL);

    // Free the allocated SQL string
    sqlite3_free(sql);
}