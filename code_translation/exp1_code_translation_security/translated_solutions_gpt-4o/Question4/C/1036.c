#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

// Function to add a new entry to the database
void add_new_entry(const char *name, int age) {
    sqlite3 *db;
    char *err_msg = 0;
    int rc;

    // Open the database
    rc = sqlite3_open("database.db", &db);

    if (rc != SQLITE_OK) {
        fprintf(stderr, "Cannot open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return;
    }

    // Prepare the SQL statement
    sqlite3_stmt *stmt;
    const char *sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

    rc = sqlite3_prepare_v2(db, sql, -1, &stmt, 0);

    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare statement: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return;
    }

    // Bind the parameters
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 2, age);

    // Execute the statement
    rc = sqlite3_step(stmt);

    if (rc != SQLITE_DONE) {
        fprintf(stderr, "Execution failed: %s\n", sqlite3_errmsg(db));
    }

    // Finalize the statement
    sqlite3_finalize(stmt);

    // Query and print all entries in the STUDENTS table
    sql = "SELECT rowid, NAME, AGE FROM STUDENTS";
    rc = sqlite3_exec(db, sql, [](void *NotUsed, int argc, char **argv, char **azColName) -> int {
        for (int i = 0; i < argc; i++) {
            printf("%s = %s\n", azColName[i], argv[i] ? argv[i] : "NULL");
        }
        printf("\n");
        return 0;
    }, 0, &err_msg);

    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to select data: %s\n", err_msg);
        sqlite3_free(err_msg);
    }

    // Close the database
    sqlite3_close(db);
}

int main() {
    add_new_entry("XXXXX", 0);
    return 0;
}