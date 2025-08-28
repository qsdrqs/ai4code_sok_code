#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

// Function to insert a student record into the database
void insert(sqlite3 *db, const char *name, int age) {
    sqlite3_stmt *stmt;
    const char *sql = "INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)";
    
    // Prepare the SQL statement
    if (sqlite3_prepare_v2(db, sql, -1, &stmt, NULL) != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare statement: %s\n", sqlite3_errmsg(db));
        return;
    }
    
    // Bind the parameters
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 2, age);
    
    // Execute the statement
    if (sqlite3_step(stmt) != SQLITE_DONE) {
        fprintf(stderr, "Failed to execute statement: %s\n", sqlite3_errmsg(db));
    }
    
    // Finalize the statement
    sqlite3_finalize(stmt);
}

int main() {
    sqlite3 *db;
    char *errMsg = 0;
    
    // Open the database
    if (sqlite3_open("studentdb", &db)) {
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        return 1;
    }
    
    // Create the STUDENTS table if it doesn't exist
    const char *createTableSQL = "CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)";
    if (sqlite3_exec(db, createTableSQL, 0, 0, &errMsg) != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errMsg);
        sqlite3_free(errMsg);
    }
    
    // Insert records
    insert(db, "Foo", 42);
    insert(db, "Bar", 55);
    
    // Close the database
    sqlite3_close(db);
    
    return 0;
}