#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

sqlite3 *db;

void addNewEntry(const char *name, int age) {
    char *errMsg = 0;
    int rc;
    sqlite3_stmt *stmt;

    // Create table if it doesn't exist
    const char *createSql = "CREATE TABLE IF NOT EXISTS STUDENTS (NAME text, AGE INT)";
    rc = sqlite3_exec(db, createSql, 0, 0, &errMsg);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errMsg);
        sqlite3_free(errMsg);
        return;
    }

    // Prepare the insert statement
    const char *insertSql = "INSERT INTO STUDENTS VALUES (?, ?)";
    rc = sqlite3_prepare_v2(db, insertSql, -1, &stmt, 0);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare statement: %s\n", sqlite3_errmsg(db));
        return;
    }

    // Bind parameters
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 2, age);

    // Execute the statement
    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "Failed to execute statement: %s\n", sqlite3_errmsg(db));
    }

    // Finalize the statement
    sqlite3_finalize(stmt);
}

// Callback function used by sqlite3_exec to process each row
int callback(void *NotUsed, int argc, char **argv, char **azColName) {
    printf("{ ");
    for (int i = 0; i < argc; i++) {
        printf("%s: %s", azColName[i], argv[i] ? argv[i] : "NULL");
        if (i < argc - 1) printf(", ");
    }
    printf(" }\n");
    return 0;
}

int main() {
    int rc;
    char *errMsg = 0;

    // Open an in-memory database
    rc = sqlite3_open(":memory:", &db);
    if (rc) {
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return 1;
    }

    addNewEntry("XXXXX", 0);
    addNewEntry("XXXXX", 0);

    // Query all rows and invoke the callback for each one
    const char *selectSql = "SELECT NAME, AGE FROM STUDENTS";
    rc = sqlite3_exec(db, selectSql, callback, 0, &errMsg);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errMsg);
        sqlite3_free(errMsg);
    }

    // Close the database
    sqlite3_close(db);

    return 0;
}