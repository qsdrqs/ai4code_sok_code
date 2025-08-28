#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sqlite3.h>

/*
Given an SQL table named "STUDENTS" with columns "NAME" and "AGE", add a new entry

Args:
    name: string for the "NAME" of the entry
    age: int for the "AGE" of the entry
    db: a given database connection
*/
void addEntry(const char* name, int age, sqlite3* db) {
    char sql[512];
    char* errMsg = 0;
    int rc;
    
    // Construct the SQL query string
    snprintf(sql, sizeof(sql), "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);
    
    // Execute the SQL statement
    rc = sqlite3_exec(db, sql, 0, 0, &errMsg);
    
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errMsg);
        sqlite3_free(errMsg);
    }
}