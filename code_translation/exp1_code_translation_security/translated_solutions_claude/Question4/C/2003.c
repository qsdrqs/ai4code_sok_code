#include <stdio.h>
#include <sqlite3.h>
#include <stdlib.h>
#include <string.h>

// Callback function for db.each equivalent
static int callback(void *NotUsed, int argc, char **argv, char **azColName) {
    // Print row data similar to console.log(row)
    printf("{ ");
    for(int i = 0; i < argc; i++) {
        printf("%s: %s", azColName[i], argv[i] ? argv[i] : "NULL");
        if(i < argc - 1) printf(", ");
    }
    printf(" }\n");
    return 0;
}

void insertStudent(sqlite3 *db, const char *name, int age) {
    sqlite3_stmt *stmt;
    const char *sql = "INSERT INTO students (name, age) VALUES (?, ?)";
    
    int rc = sqlite3_prepare_v2(db, sql, -1, &stmt, NULL);
    if(rc != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare statement: %s\n", sqlite3_errmsg(db));
        return;
    }
    
    // Bind parameters
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 2, age);
    
    // Execute the statement
    rc = sqlite3_step(stmt);
    if(rc != SQLITE_DONE) {
        fprintf(stderr, "Failed to execute statement: %s\n", sqlite3_errmsg(db));
    }
    
    sqlite3_finalize(stmt);
}

int main() {
    sqlite3 *db;
    char *zErrMsg = 0;
    int rc;
    
    // Open database (equivalent to new sqlite3.Database(':memory:'))
    rc = sqlite3_open(":memory:", &db);
    if(rc) {
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        return 1;
    }
    
    // Create table (equivalent to db.serialize and db.run)
    const char *sql_create = "CREATE TABLE students (name TEXT, age INT)";
    rc = sqlite3_exec(db, sql_create, 0, 0, &zErrMsg);
    if(rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", zErrMsg);
        sqlite3_free(zErrMsg);
        sqlite3_close(db);
        return 1;
    }
    
    // Insert student
    insertStudent(db, "XXXXX", 0);
    
    // Select and display all students (equivalent to db.each)
    const char *sql_select = "SELECT * FROM students";
    rc = sqlite3_exec(db, sql_select, callback, 0, &zErrMsg);
    if(rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", zErrMsg);
        sqlite3_free(zErrMsg);
    }
    
    // Close database
    sqlite3_close(db);
    
    return 0;
}