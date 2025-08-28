#include <sqlite3.h>
#include <stdio.h>
#include <stdlib.h>

// Callback function for SQLite3
static int callback(void *data, int argc, char **argv, char **azColName) {
    return 0;
}

// Function to add a new entry to the students table
int addSQLentry(sqlite3 *db, const char *name, int age) {
    const char *sql = "INSERT INTO students (name, age) VALUES (?, ?)";
    sqlite3_stmt *stmt;
    int rc;

    // Prepare the SQL statement
    rc = sqlite3_prepare_v2(db, sql, -1, &stmt, 0);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", sqlite3_errmsg(db));
        return rc;
    }

    // Bind parameters
    rc = sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL bind error: %s\n", sqlite3_errmsg(db));
        sqlite3_finalize(stmt);
        return rc;
    }

    rc = sqlite3_bind_int(stmt, 2, age);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL bind error: %s\n", sqlite3_errmsg(db));
        sqlite3_finalize(stmt);
        return rc;
    }

    // Execute the SQL statement
    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "SQL error: %s\n", sqlite3_errmsg(db));
        sqlite3_finalize(stmt);
        return rc;
    }

    // Get the last inserted rowid
    sqlite3_int64 lastID = sqlite3_last_insert_rowid(db);
    printf("User added with id: %" PRId64 "\n", lastID);

    // Finalize the statement
    sqlite3_finalize(stmt);

    return SQLITE_OK;
}

int main() {
    sqlite3 *db;
    int rc;

    // Open the database
    rc = sqlite3_open("example.db", &db);
    if (rc) {
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        return 1;
    }

    // Add a new entry to the students table
    rc = addSQLentry(db, "John Doe", 25);

    // Close the database
    sqlite3_close(db);

    return 0;
}