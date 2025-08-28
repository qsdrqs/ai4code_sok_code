#include <stdio.h>
#include <sqlite3.h>
#include <string.h>

sqlite3 *db;

void student_write(const char* name, int age) {
    sqlite3_stmt *stmt;
    const char *sql = "INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)";
    
    int rc = sqlite3_prepare_v2(db, sql, -1, &stmt, NULL);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare statement: %s\n", sqlite3_errmsg(db));
        return;
    }
    
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 2, age);
    
    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "Failed to execute statement: %s\n", sqlite3_errmsg(db));
    }
    
    sqlite3_finalize(stmt);
}

int main() {
    int rc = sqlite3_open("/home/codex/Documents/User_Study_UI/database.db", &db);
    
    if (rc) {
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        return 1;
    }
    
    student_write("XXXXX", 0);
    
    // SELECT query equivalent
    sqlite3_stmt *stmt;
    const char *sql = "SELECT * FROM STUDENTS";
    
    rc = sqlite3_prepare_v2(db, sql, -1, &stmt, NULL);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare statement: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return 1;
    }
    
    while ((rc = sqlite3_step(stmt)) == SQLITE_ROW) {
        const char* name = (const char*)sqlite3_column_text(stmt, 0);
        int age = sqlite3_column_int(stmt, 1);
        printf("Name: %s, Age: %d\n", name, age);
    }
    
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "Failed to execute statement: %s\n", sqlite3_errmsg(db));
    }
    
    sqlite3_finalize(stmt);
    sqlite3_close(db);
    
    return 0;
}