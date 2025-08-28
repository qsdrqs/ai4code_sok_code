#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

// Function declarations
void createDatabase();
int createTables2(sqlite3 *db);
int insert(sqlite3 *db, const char *name, int age);

int main() {
    createDatabase();
    return 0;
}

/**
 * Creates the database and initializes it.
 * Logs "created database" and opens the SQLite database file.
 */
void createDatabase() {
    printf("created database\n");
    sqlite3 *db;
    int rc = sqlite3_open("temp.db", &db);

    if (rc != SQLITE_OK) {
        fprintf(stderr, "Getting error: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        exit(1);
    }

    rc = createTables2(db);
    if (rc != SQLITE_OK) {
        sqlite3_close(db);
        exit(1);
    }

    sqlite3_close(db);
}

/**
 * Creates the 'students' table and inserts a row.
 * Logs "pre-created tables" before creating the table.
 */
int createTables2(sqlite3 *db) {
    printf("pre-created tables\n");
    const char *sql = "CREATE TABLE IF NOT EXISTS students(name TEXT, age INT)";
    char *err_msg = NULL;

    int rc = sqlite3_exec(db, sql, NULL, NULL, &err_msg);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", err_msg);
        sqlite3_free(err_msg);
        return rc;
    }

    return insert(db, "John Doe", 30);
}

/**
 * Inserts a new row into the 'students' table.
 * Logs "row added successfully" on success.
 */
int insert(sqlite3 *db, const char *name, int age) {
    const char *sql = "INSERT INTO students(name, age) VALUES (?, ?)";
    sqlite3_stmt *stmt;
    int rc = sqlite3_prepare_v2(db, sql, -1, &stmt, NULL);

    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare statement: %s\n", sqlite3_errmsg(db));
        return rc;
    }

    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 2, age);

    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "Execution failed: %s\n", sqlite3_errmsg(db));
    } else {
        printf("row added successfully\n");
    }

    sqlite3_finalize(stmt);
    return rc;
}