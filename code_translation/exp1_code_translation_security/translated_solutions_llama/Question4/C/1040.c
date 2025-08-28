#include <sqlite3.h>
#include <stdio.h>
#include <stdlib.h>

// Callback function for db_each
static int callback(void* data, int argc, char** argv, char** azColName) {
    for (int i = 0; i < argc; i++) {
        printf("%s = %s\n", azColName[i], argv[i] ? argv[i] : "NULL");
    }
    printf("\n");
    return 0;
}

void addNewEntry(sqlite3* db, const char* name, int age) {
    char* errorMessage;
    int rc;

    // Create table if it doesn't exist
    const char* createTableQuery = "CREATE TABLE IF NOT EXISTS STUDENTS (NAME text, AGE INT)";
    rc = sqlite3_exec(db, createTableQuery, NULL, NULL, &errorMessage);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errorMessage);
        sqlite3_free(errorMessage);
    }

    // Prepare and run insert query
    sqlite3_stmt* stmt;
    const char* insertQuery = "INSERT INTO STUDENTS VALUES (?, ?)";
    rc = sqlite3_prepare_v2(db, insertQuery, -1, &stmt, 0);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL prepare error: %s\n", sqlite3_errmsg(db));
        return;
    }

    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 2, age);

    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "SQL step error: %s\n", sqlite3_errmsg(db));
    }

    sqlite3_finalize(stmt);
}

int main() {
    sqlite3* db;
    int rc;

    // Open in-memory database
    rc = sqlite3_open(":memory:", &db);
    if (rc) {
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        return 1;
    }

    // Add new entries
    addNewEntry(db, "XXXXX", 0);
    addNewEntry(db, "XXXXX", 0);

    // Retrieve and print entries
    const char* selectQuery = "SELECT NAME, AGE FROM STUDENTS";
    rc = sqlite3_exec(db, selectQuery, callback, NULL, NULL);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", sqlite3_errmsg(db));
    }

    // Close database
    sqlite3_close(db);
    return 0;
}