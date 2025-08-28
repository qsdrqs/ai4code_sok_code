#include <stdio.h>
#include <sqlite3.h>
#include <stdlib.h>

sqlite3 *db;

void addNewEntry(const char* name, int age) {
    char *err_msg = 0;
    int rc;
    
    // Create table if not exists
    const char *create_table_sql = "CREATE TABLE IF NOT EXISTS STUDENTS (NAME text, AGE INT)";
    rc = sqlite3_exec(db, create_table_sql, 0, 0, &err_msg);
    
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", err_msg);
        sqlite3_free(err_msg);
        return;
    }
    
    // Prepare INSERT statement
    sqlite3_stmt *stmt;
    const char *insert_sql = "INSERT INTO STUDENTS VALUES (?, ?)";
    
    rc = sqlite3_prepare_v2(db, insert_sql, -1, &stmt, NULL);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare statement: %s\n", sqlite3_errmsg(db));
        return;
    }
    
    // Bind parameters
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 2, age);
    
    // Execute statement
    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "Failed to execute statement: %s\n", sqlite3_errmsg(db));
    }
    
    // Finalize statement
    sqlite3_finalize(stmt);
}

void selectAndPrintStudents() {
    sqlite3_stmt *stmt;
    const char *select_sql = "SELECT NAME, AGE FROM STUDENTS";
    int rc;
    
    rc = sqlite3_prepare_v2(db, select_sql, -1, &stmt, NULL);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare statement: %s\n", sqlite3_errmsg(db));
        return;
    }
    
    // Execute and iterate through results
    while ((rc = sqlite3_step(stmt)) == SQLITE_ROW) {
        const char *name = (const char*)sqlite3_column_text(stmt, 0);
        int age = sqlite3_column_int(stmt, 1);
        printf("{ NAME: %s, AGE: %d }\n", name, age);
    }
    
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "Failed to execute statement: %s\n", sqlite3_errmsg(db));
    }
    
    sqlite3_finalize(stmt);
}

int main() {
    int rc;
    
    // Open in-memory database
    rc = sqlite3_open(":memory:", &db);
    if (rc) {
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        return 1;
    }
    
    // Add entries
    addNewEntry("XXXXX", 0);
    addNewEntry("XXXXX", 0);
    
    // Select and print results
    selectAndPrintStudents();
    
    // Close database
    sqlite3_close(db);
    
    return 0;
}