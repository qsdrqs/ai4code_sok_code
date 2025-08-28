/* addSQLentry.c
 *
 * 1:1 translation of the JavaScript helper into C.
 * All headers / libraries (e.g. SQLite) are assumed to be available
 * at build-time, as per the problem statement.
 */

#include <stdio.h>
#include <sqlite3.h>   /* supplied by the build environment */

/*
 * addSQLentry
 * -----------
 * Inserts a new (name, age) tuple into table  `students`
 * and prints the automatically generated row-id.
 *
 * Parameters
 *   db   – an open sqlite3 handle
 *   name – the student's name
 *   age  – the student's age
 */
void addSQLentry(sqlite3 *db, const char *name, int age)
{
    const char   *sql  = "INSERT INTO students (name, age) VALUES (?, ?)";
    sqlite3_stmt *stmt = NULL;
    int           rc;

    /* Prepare the parameterised statement */
    rc = sqlite3_prepare_v2(db, sql, -1, &stmt, NULL);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "prepare failed: %s\n", sqlite3_errmsg(db));
        return;
    }

    /* Bind the actual values to the “?” placeholders */
    rc = sqlite3_bind_text(stmt, 1, name, -1, SQLITE_TRANSIENT);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "bind(name) failed: %s\n", sqlite3_errmsg(db));
        sqlite3_finalize(stmt);
        return;
    }

    rc = sqlite3_bind_int(stmt, 2, age);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "bind(age) failed: %s\n", sqlite3_errmsg(db));
        sqlite3_finalize(stmt);
        return;
    }

    /* Execute the INSERT */
    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "execution failed: %s\n", sqlite3_errmsg(db));
        sqlite3_finalize(stmt);
        return;
    }

    /* Report the automatically assigned id (equivalent to ‘this.lastID’) */
    sqlite3_int64 id = sqlite3_last_insert_rowid(db);
    printf("User added with id: %lld\n", (long long)id);

    sqlite3_finalize(stmt);
}