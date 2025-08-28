#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

// Function to add a new entry to the database
void addNewEntry(sqlite3 *db, const char *name, int age) {
    char *errMsg = 0;
    const char *sqlCreateTable = "CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT);";
    const char *sqlInsert = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?);";
    sqlite3_stmt *stmt;

    // Create table if it doesn't exist
    if (sqlite3_exec(db, sqlCreateTable, 0, 0, &errMsg) != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errMsg);
        sqlite3_free(errMsg);
        return;
    }

    // Prepare the SQL statement
    if (sqlite3_prepare_v2(db, sqlInsert, -1, &stmt, 0) != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare statement\n");
        return;
    }

    // Bind the parameters
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 2, age);

    // Execute the statement
    if (sqlite3_step(stmt) != SQLITE_DONE) {
        fprintf(stderr, "Failed to execute statement\n");
    }

    // Finalize the statement
    sqlite3_finalize(stmt);
}

int main() {
    sqlite3 *db;
    char *errMsg = 0;
    const char *sqlSelect = "SELECT NAME, AGE FROM STUDENTS;";
    sqlite3_stmt *stmt;

    // Open an in-memory database
    if (sqlite3_open(":memory:", &db)) {
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        return 1;
    }

    // Add entries to the database
    addNewEntry(db, "XXXXX", 0);
    addNewEntry(db, "XXXXX", 0);

    // Prepare the select statement
    if (sqlite3_prepare_v2(db, sqlSelect, -1, &stmt, 0) != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare select statement\n");
        return 1;
    }

    // Execute the select statement and print the results
    while (sqlite3_step(stmt) == SQLITE_ROW) {
        const unsigned char *name = sqlite3_column_text(stmt, 0);
        int age = sqlite3_column_int(stmt, 1);
        printf("NAME: %s, AGE: %d\n", name, age);
    }

    // Finalize the select statement
    sqlite3_finalize(stmt);

    // Close the database
    sqlite3_close(db);

    return 0;
}