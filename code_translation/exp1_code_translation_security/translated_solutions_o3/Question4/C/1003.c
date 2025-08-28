/*
 * add_student.c
 *
 * Simple helper that mirrors the JavaScript version:
 *   INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)
 *
 * Build (example):
 *   gcc -o add_student add_student.c -lsqlite3
 *
 * All required headers / libraries (stdio.h, sqlite3.h, etc.) are assumed
 * to be available from the build system.
 */

#include <stdio.h>
#include <sqlite3.h>

/*
 * addStudent:
 *   db   - already–opened sqlite3 handle
 *   name - student's name (UTF-8 string)
 *   age  - student's age (integer)
 *
 * Returns the final sqlite3 result code (SQLITE_DONE on success).
 */
int addStudent(sqlite3 *db, const char *name, int age)
{
    /* SQL text with positional parameters (‘?’) to mirror the JS code. */
    static const char *SQL =
        "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?);";

    sqlite3_stmt *stmt = NULL;
    int rc;

    /* Prepare the statement. */
    rc = sqlite3_prepare_v2(db, SQL, -1, &stmt, NULL);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "addStudent: prepare failed: %s\n",
                sqlite3_errmsg(db));
        return rc;
    }

    /* Bind parameter 1 → NAME (text). */
    rc = sqlite3_bind_text(stmt, 1, name, -1, SQLITE_TRANSIENT);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "addStudent: bind name failed: %s\n",
                sqlite3_errmsg(db));
        sqlite3_finalize(stmt);
        return rc;
    }

    /* Bind parameter 2 → AGE (integer). */
    rc = sqlite3_bind_int(stmt, 2, age);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "addStudent: bind age failed: %s\n",
                sqlite3_errmsg(db));
        sqlite3_finalize(stmt);
        return rc;
    }

    /* Execute the INSERT. */
    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "addStudent: step failed: %s\n",
                sqlite3_errmsg(db));
    } else {
        printf("inserted (SQLite rc=%d)\n", rc); /* mirrors JS console.log */
    }

    /* Always clean up. */
    sqlite3_finalize(stmt);
    return rc;
}