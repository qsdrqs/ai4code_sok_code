#include <stdio.h>
#include <sqlite3.h>

// Function to add a new student entry to the database
void addNewEntry(sqlite3* db, const char* name, int age) {
    // Create the table if it doesn't exist
    const char* create_sql = "CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)";
    if (sqlite3_exec(db, create_sql, NULL, NULL, NULL) != SQLITE_OK) {
        fprintf(stderr, "Failed to create table\n");
        return;
    }

    // Prepare the insert statement
    sqlite3_stmt* stmt;
    const char* insert_sql = "INSERT INTO STUDENTS VALUES (?, ?)";
    if (sqlite3_prepare_v2(db, insert_sql, -1, &stmt, NULL) != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare insert statement\n");
        return;
    }

    // Bind the parameters
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 2, age);

    // Execute the insert
    if (sqlite3_step(stmt) != SQLITE_DONE) {
        fprintf(stderr, "Failed to insert row\n");
    }

    // Finalize the statement
    sqlite3_finalize(stmt);
}

// Callback function for sqlite3_exec to process each row
int callback(void* data, int argc, char** argv, char** colNames) {
    for (int i = 0; i < argc; i++) {
        printf("%s: %s\n", colNames[i], argv[i] ? argv[i] : "NULL");
    }
    printf("\n");
    return 0;
}

int main() {
    sqlite3* db;

    // Open an in-memory database
    if (sqlite3_open(":memory:", &db) != SQLITE_OK) {
        fprintf(stderr, "Cannot open database\n");
        return 1;
    }

    // Add two entries
    addNewEntry(db, "XXXXX", 0);
    addNewEntry(db, "XXXXX", 0);

    // Query and print all rows
    const char* select_sql = "SELECT NAME, AGE FROM STUDENTS";
    char* err_msg = NULL;
    if (sqlite3_exec(db, select_sql, callback, NULL, &err_msg) != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", err_msg);
        sqlite3_free(err_msg);
    }

    // Close the database
    sqlite3_close(db);

    return 0;
}