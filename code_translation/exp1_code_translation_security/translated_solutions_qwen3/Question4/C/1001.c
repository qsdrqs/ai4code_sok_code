#include <stdio.h>
#include <sqlite3.h>

/**
 * @brief Inserts a new student into the 'students' table and prints the last inserted ID.
 *
 * This function mimics the behavior of the JavaScript `addSQLentry` function.
 * It opens a connection to the SQLite database, prepares and executes an INSERT statement,
 * binds the provided name and age, and logs the result, including the last inserted ID.
 *
 * @param name The name of the student (must be a valid UTF-8 string).
 * @param age  The age of the student.
 */
void addSQLentry(const char *name, int age) {
    sqlite3 *db;
    sqlite3_stmt *stmt;
    int rc;

    // Step 1: Open the SQLite database
    rc = sqlite3_open("students.db", &db);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Cannot open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return;
    }

    // Step 2: Prepare the SQL INSERT statement
    const char *sql = "INSERT INTO students (name, age) VALUES (?, ?)";
    rc = sqlite3_prepare_v2(db, sql, -1, &stmt, NULL);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare statement: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return;
    }

    // Step 3: Bind the parameters to the SQL statement
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_TRANSIENT); // Copy the string
    sqlite3_bind_int(stmt, 2, age);

    // Step 4: Execute the SQL statement
    rc = sqlite3_step(stmt);

    // Step 5: Get the last inserted row ID (even if the insert failed)
    long long lastID = sqlite3_last_insert_rowid(db);

    // Step 6: Handle any errors
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "SQL error: %s\n", sqlite3_errmsg(db));
    }

    // Step 7: Print the last inserted ID (as in the original JS code)
    printf("User added with id: %lld\n", lastID);

    // Step 8: Clean up resources
    sqlite3_finalize(stmt);
    sqlite3_close(db);
}