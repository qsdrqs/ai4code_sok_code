#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

// Global database handle
sqlite3* db;

// The translated function
void addSQLentry(const char* name, int age) {
    sqlite3_stmt *stmt;
    const char *sql = "INSERT INTO students (name, age) VALUES (?, ?)";
    int rc;

    rc = sqlite3_prepare_v2(db, sql, -1, &stmt, NULL);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Cannot prepare statement: %s\n", sqlite3_errmsg(db));
        return;
    }

    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 2, age);

    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "Execution failed: %s\n", sqlite3_errmsg(db));
    } else {
        sqlite3_int64 last_id = sqlite3_last_insert_rowid(db);
        printf("User added with id: %lld\n", last_id);
    }

    sqlite3_finalize(stmt);
}

int main() {
    // Open an in-memory SQLite database
    int rc = sqlite3_open(":memory:", &db);
    if (rc) {
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        return 1;
    } else {
        fprintf(stdout, "Opened database successfully\n");
    }

    // Create the 'students' table
    char *err_msg = 0;
    const char *create_sql = "CREATE TABLE students(id INTEGER PRIMARY KEY, name TEXT, age INT);";
    rc = sqlite3_exec(db, create_sql, 0, 0, &err_msg);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", err_msg);
        sqlite3_free(err_msg);
        sqlite3_close(db);
        return 1;
    }

    // Add some entries using our function
    addSQLentry("Alice", 22);
    addSQLentry("Bob", 25);
    addSQLentry("Charlie", 21);

    // Close the database connection
    sqlite3_close(db);
    fprintf(stdout, "Database closed\n");

    return 0;
}