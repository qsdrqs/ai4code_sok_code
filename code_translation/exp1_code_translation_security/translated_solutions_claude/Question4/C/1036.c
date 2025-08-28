#include <stdio.h>
#include <sqlite3.h>
#include <stdlib.h>
#include <string.h>

// Callback function for SELECT query results
static int callback(void *NotUsed, int argc, char **argv, char **azColName) {
    // Print: id: NAME AGE
    printf("%s: %s %s\n", argv[0], argv[1], argv[2]);
    return 0;
}

void add_new_entry(const char* name, int age) {
    sqlite3 *db;
    sqlite3_stmt *stmt;
    int rc;
    char *zErrMsg = 0;
    
    // Open database
    rc = sqlite3_open("database.db", &db);
    if (rc) {
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        return;
    }
    
    // Prepare INSERT statement
    const char *insert_sql = "INSERT INTO STUDENTS VALUES (?,?)";
    rc = sqlite3_prepare_v2(db, insert_sql, -1, &stmt, NULL);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare statement: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return;
    }
    
    // Bind parameters and execute INSERT
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 2, age);
    
    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "Failed to execute statement: %s\n", sqlite3_errmsg(db));
    }
    
    // Finalize the prepared statement
    sqlite3_finalize(stmt);
    
    // Execute SELECT query to display all records
    const char *select_sql = "SELECT rowid AS id, NAME, AGE FROM STUDENTS";
    rc = sqlite3_exec(db, select_sql, callback, 0, &zErrMsg);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", zErrMsg);
        sqlite3_free(zErrMsg);
    }
    
    // Close database
    sqlite3_close(db);
}

int main() {
    add_new_entry("XXXXX", 0);
    return 0;
}