#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>
#include <string.h>

// Forward declarations
void createDatabase();
void createTables(sqlite3* db);
void createTables2(sqlite3* db);
void insert(sqlite3* db, const char* name, int age);

// Callback function for database operations
int callback(void* data, int argc, char** argv, char** azColName) {
    for (int i = 0; i < argc; i++) {
        printf("%s = %s\n", azColName[i], argv[i] ? argv[i] : "NULL");
    }
    printf("\n");
    return 0;
}

int main() {
    // Equivalent to the IIFE (Immediately Invoked Function Expression)
    printf("Starting program...\n");
    
    createDatabase();
    
    return 0;
}

void createDatabase() {
    printf("created database\n");
    
    sqlite3* newdb;
    int rc = sqlite3_open("temp.db", &newdb);
    
    if (rc) {
        printf("Getting error %s\n", sqlite3_errmsg(newdb));
        sqlite3_close(newdb);
        exit(1);
    }
    
    createTables2(newdb);
}

void createTables(sqlite3* db) {
    printf("pre-created tables\n");
    
    char* errMsg = 0;
    int rc = sqlite3_exec(db, "CREATE TABLE students(name TEXT,age INT)", 0, 0, &errMsg);
    
    if (rc != SQLITE_OK) {
        printf("SQL error: %s\n", errMsg);
        sqlite3_free(errMsg);
    }
}

void createTables2(sqlite3* db) {
    printf("pre-created tables\n");
    
    char* errMsg = 0;
    int rc = sqlite3_exec(db, "CREATE TABLE students(name TEXT,age INT)", 0, 0, &errMsg);
    
    if (rc != SQLITE_OK) {
        printf("SQL error: %s\n", errMsg);
        sqlite3_free(errMsg);
        return;
    }
    
    // Call insert after table creation (simulating the callback behavior)
    insert(db, "John Doe", 30);
}

void insert(sqlite3* db, const char* name, int age) {
    const char* sql = "INSERT INTO students(name, age) VALUES (?, ?)";
    sqlite3_stmt* stmt;
    
    int rc = sqlite3_prepare_v2(db, sql, -1, &stmt, NULL);
    
    if (rc != SQLITE_OK) {
        printf("Error preparing statement: %s\n", sqlite3_errmsg(db));
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
        printf("row added successfully\n");
    }
    
    // Clean up
    sqlite3_finalize(stmt);
    sqlite3_close(db);
}