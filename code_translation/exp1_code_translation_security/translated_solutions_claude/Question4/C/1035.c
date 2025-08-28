#include <stdio.h>
#include <sqlite3.h>
#include <stdlib.h>

// Function to insert a student record
void insert(sqlite3* db, const char* name, int age) {
    sqlite3_stmt* stmt;
    const char* sql = "INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)";
    
    // Prepare the statement
    int rc = sqlite3_prepare_v2(db, sql, -1, &stmt, NULL);
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

int main() {
    sqlite3* db;
    int rc;
    
    // Open database
    rc = sqlite3_open("studentdb", &db);
    if (rc) {
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return 1;
    }
    
    // Create table if it doesn't exist
    const char* create_table_sql = "CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)";
    rc = sqlite3_exec(db, create_table_sql, NULL, NULL, NULL);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to create table: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return 1;
    }
    
    // Insert records
    insert(db, "Foo", 42);
    insert(db, "Bar", 55);
    
    /* Commented out section equivalent (for reference):
    const char* select_sql = "SELECT * FROM STUDENTS";
    sqlite3_stmt* select_stmt;
    
    rc = sqlite3_prepare_v2(db, select_sql, -1, &select_stmt, NULL);
    if (rc == SQLITE_OK) {
        while (sqlite3_step(select_stmt) == SQLITE_ROW) {
            const char* name = (const char*)sqlite3_column_text(select_stmt, 0);
            int age = sqlite3_column_int(select_stmt, 1);
            printf("Name: %s, Age: %d\n", name, age);
        }
        sqlite3_finalize(select_stmt);
    }
    */
    
    // Close database
    sqlite3_close(db);
    
    return 0;
}