#include <sqlite3.h>
#include <stdio.h>
#include <stdlib.h>

// Function to create a table in the database
int createTable(sqlite3* db) {
    const char* sql = "CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY, name TEXT, age INTEGER)";
    char* errorMessage;

    int rc = sqlite3_exec(db, sql, NULL, NULL, &errorMessage);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errorMessage);
        sqlite3_free(errorMessage);
        return 0; // Return 0 to indicate failure
    } else {
        printf("Table created successfully\n");
        return 1; // Return 1 to indicate success
    }
}

// Function to insert a student into the database
int insertStudent(sqlite3* db, const char* name, int age) {
    const char* sql = "INSERT INTO students (name, age) VALUES (?, ?)";
    sqlite3_stmt* stmt;

    int rc = sqlite3_prepare_v2(db, sql, -1, &stmt, 0);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL prepare error: %s\n", sqlite3_errmsg(db));
        return 0; // Return 0 to indicate failure
    }

    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 2, age);

    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "SQL execution error: %s\n", sqlite3_errmsg(db));
        sqlite3_finalize(stmt);
        return 0; // Return 0 to indicate failure
    }

    sqlite3_finalize(stmt);
    printf("Student inserted successfully\n");
    return 1; // Return 1 to indicate success
}

int main() {
    sqlite3* db;
    int rc;

    // Open the database
    rc = sqlite3_open("mydb.db", &db);
    if (rc) {
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        return 1;
    }

    // Create the table
    createTable(db);

    // Insert students
    insertStudent(db, "John Doe", 20);
    insertStudent(db, "Jane Doe", 22);

    // Close the database
    sqlite3_close(db);
    return 0;
}