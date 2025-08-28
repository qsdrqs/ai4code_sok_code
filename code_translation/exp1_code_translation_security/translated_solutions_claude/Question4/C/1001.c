#include <stdio.h>
#include <sqlite3.h>
#include <string.h>

void addSQLentry(const char* name, int age) {
    sqlite3* db;
    sqlite3_stmt* stmt;
    const char* sql = "INSERT INTO students (name, age) VALUES (?, ?)";
    int rc;
    
    // Open database connection (assuming database file is "database.db")
    rc = sqlite3_open("database.db", &db);
    if (rc != SQLITE_OK) {
        printf("Cannot open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return;
    }
    
    // Prepare the SQL statement
    rc = sqlite3_prepare_v2(db, sql, -1, &stmt, NULL);
    if (rc != SQLITE_OK) {
        printf("Failed to prepare statement: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return;
    }
    
    // Bind parameters
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 2, age);
    
    // Execute the statement
    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        printf("Error executing statement: %s\n", sqlite3_errmsg(db));
    } else {
        printf("User added with id: %lld\n", sqlite3_last_insert_rowid(db));
    }
    
    // Clean up
    sqlite3_finalize(stmt);
    sqlite3_close(db);
}