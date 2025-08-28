#include <sqlite3.h>
#include <stdio.h>
#include <string.h>

/**
 * Creates the 'students' table if it does not already exist.
 * This function mimics the behavior of the JavaScript `createTable()` function,
 * including success and error alerts via `printf`.
 */
void createTable() {
    sqlite3 *db;
    int rc = sqlite3_open("mydb.db", &db);
    if (rc != SQLITE_OK) {
        printf("Error occurred while creating the table.\n");
        sqlite3_close(db);
        return;
    }

    const char *sql = "CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY, name TEXT, age INTEGER)";
    char *errmsg = 0;

    rc = sqlite3_exec(db, sql, 0, 0, &errmsg);
    if (rc != SQLITE_OK) {
        printf("Error occurred while creating the table: %s\n", errmsg);
        sqlite3_free(errmsg);
    } else {
        printf("Table created successfully\n");
    }

    sqlite3_close(db);
}

/**
 * Inserts a new student into the 'students' table.
 * This function mimics the behavior of the JavaScript `insertStudent(name, age)` function,
 * including opening the database and using a transaction.
 *
 * @param name The name of the student (C string)
 * @param age The age of the student (integer)
 */
void insertStudent(const char *name, int age) {
    sqlite3 *db;
    int rc = sqlite3_open("mydb.db", &db);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Cannot open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return;
    }

    // Begin transaction
    rc = sqlite3_exec(db, "BEGIN IMMEDIATE TRANSACTION", 0, 0, 0);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to start transaction: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return;
    }

    sqlite3_stmt *stmt;
    const char *sql = "INSERT INTO students (name, age) VALUES (?, ?)";
    rc = sqlite3_prepare_v2(db, sql, -1, &stmt, NULL);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare statement: %s\n", sqlite3_errmsg(db));
        sqlite3_exec(db, "ROLLBACK TRANSACTION", 0, 0, 0);
        sqlite3_close(db);
        return;
    }

    // Bind parameters
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 2, age);

    // Execute the statement
    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "Failed to insert student: %s\n", sqlite3_errmsg(db));
        sqlite3_exec(db, "ROLLBACK TRANSACTION", 0, 0, 0);
    } else {
        sqlite3_exec(db, "COMMIT TRANSACTION", 0, 0, 0);
    }

    // Clean up
    sqlite3_finalize(stmt);
    sqlite3_close(db);
}