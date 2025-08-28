#include <sqlite3.h>
#include <stdio.h>

/**
 * Inserts a new student into the STUDENTS table.
 *
 * @param db   A pointer to the SQLite database connection.
 * @param name A null-terminated string representing the student's name.
 * @param age  An integer representing the student's age.
 *
 * This function prepares an SQL INSERT statement, binds the provided name and age
 * to the placeholders, executes the statement, and logs any errors that occur
 * during the process. It does not perform any input validation.
 */
void addStudent(sqlite3* db, const char* name, int age) {
    const char* sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
    sqlite3_stmt* stmt;
    int rc;

    // Step 1: Prepare the SQL statement
    rc = sqlite3_prepare_v2(db, sql, -1, &stmt, NULL);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "inserted %s\n", sqlite3_errmsg(db));
        return;
    }

    // Step 2: Bind the name parameter (text)
    rc = sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "inserted %s\n", sqlite3_errmsg(db));
        sqlite3_finalize(stmt);
        return;
    }

    // Step 3: Bind the age parameter (integer)
    rc = sqlite3_bind_int(stmt, 2, age);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "inserted %s\n", sqlite3_errmsg(db));
        sqlite3_finalize(stmt);
        return;
    }

    // Step 4: Execute the statement
    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "inserted %s\n", sqlite3_errmsg(db));
    }

    // Step 5: Finalize the statement to release resources
    sqlite3_finalize(stmt);
}