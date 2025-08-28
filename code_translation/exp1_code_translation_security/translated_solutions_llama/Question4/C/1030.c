#include <sqlite3.h>
#include <stdio.h>
#include <stdlib.h>

// Function to create a database
void createDatabase() {
    sqlite3 *db;
    int rc = sqlite3_open("temp.db", &db);
    if (rc) {
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        exit(1);
    }
    printf("Database created\n");
    createTables(db);
    sqlite3_close(db);
}

// Function to create tables in the database
void createTables(sqlite3 *db) {
    char *errMsg;
    const char *sql = "CREATE TABLE IF NOT EXISTS students(name TEXT,age INT)";
    int rc = sqlite3_exec(db, sql, NULL, NULL, &errMsg);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errMsg);
        sqlite3_free(errMsg);
    } else {
        printf("Tables created\n");
        insert(db, "John Doe", 30);
        // Call select function after some delay (not directly possible in C)
        // Use sleep function for simplicity
        sleep(1);
        selectAll(db);
    }
}

// Function to insert data into the students table
void insert(sqlite3 *db, const char *name, int age) {
    sqlite3_stmt *stmt;
    const char *sql = "INSERT INTO students(name, age) VALUES (?,?)";
    int rc = sqlite3_prepare_v2(db, sql, -1, &stmt, 0);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL prepare error: %s\n", sqlite3_errmsg(db));
        return;
    }
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 2, age);
    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "SQL execution error: %s\n", sqlite3_errmsg(db));
    } else {
        printf("Row added successfully\n");
    }
    sqlite3_finalize(stmt);
}

// Function to select all data from the students table
void selectAll(sqlite3 *db) {
    sqlite3_stmt *stmt;
    const char *sql = "SELECT * FROM students";
    int rc = sqlite3_prepare_v2(db, sql, -1, &stmt, 0);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL prepare error: %s\n", sqlite3_errmsg(db));
        return;
    }
    while ((rc = sqlite3_step(stmt)) == SQLITE_ROW) {
        printf("%s %d\n", sqlite3_column_text(stmt, 0), sqlite3_column_int(stmt, 1));
    }
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "SQL execution error: %s\n", sqlite3_errmsg(db));
    }
    sqlite3_finalize(stmt);
}

int main() {
    createDatabase();
    return 0;
}