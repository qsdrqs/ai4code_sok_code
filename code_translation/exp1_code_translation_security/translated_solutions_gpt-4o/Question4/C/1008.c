#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

/* Function to create SQL table "students" with 'name' and 'age' */
void createTable(sqlite3 *db) {
    char *errMsg = 0;
    const char *sql = "CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY, name TEXT, age INTEGER);";
    
    int rc = sqlite3_exec(db, sql, 0, 0, &errMsg);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errMsg);
        sqlite3_free(errMsg);
    } else {
        printf("Table created successfully\n");
    }
}

/* Function to insert string name and an int age to 'students' */
void insertStudent(sqlite3 *db, const char *name, int age) {
    sqlite3_stmt *stmt;
    const char *sql = "INSERT INTO students (name, age) VALUES (?, ?);";
    
    int rc = sqlite3_prepare_v2(db, sql, -1, &stmt, 0);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare statement\n");
        return;
    }
    
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 2, age);
    
    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "Execution failed: %s\n", sqlite3_errmsg(db));
    }
    
    sqlite3_finalize(stmt);
}

int main() {
    sqlite3 *db;
    int rc = sqlite3_open("mydb.db", &db);
    
    if (rc) {
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        return 0;
    } else {
        printf("Opened database successfully\n");
    }
    
    createTable(db);
    insertStudent(db, "John Doe", 25);
    
    sqlite3_close(db);
    return 0;
}