#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

sqlite3 *db;

void insert(const char *name, int age) {
    sqlite3_stmt *stmt;
    const char *sql = "INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)";
    
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
    
    /*
    sqlite3_stmt *select_stmt;
    const char *select_sql = "SELECT * FROM STUDENTS";
    rc = sqlite3_prepare_v2(db, select_sql, -1, &select_stmt, NULL);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare select: %s\n", sqlite3_errmsg(db));
        return;
    }
    while (sqlite3_step(select_stmt) == SQLITE_ROW) {
        const unsigned char *row_name = sqlite3_column_text(select_stmt, 0);
        int row_age = sqlite3_column_int(select_stmt, 1);
        printf("{ NAME: '%s', AGE: %d }\n", row_name, row_age);
    }
    sqlite3_finalize(select_stmt);
    */
}

int main(void) {
    int rc = sqlite3_open("studentdb", &db);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Cannot open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return 1;
    }
    
    char *err_msg = NULL;
    const char *create_sql = "CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)";
    rc = sqlite3_exec(db, create_sql, NULL, NULL, &err_msg);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", err_msg);
        sqlite3_free(err_msg);
        sqlite3_close(db);
        return 1;
    }
    
    insert("Foo", 42);
    insert("Bar", 55);
    
    sqlite3_close(db);
    return 0;
}