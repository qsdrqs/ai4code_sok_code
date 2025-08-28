/*
 *  insert_student.c
 *
 *  A tiny C program that adds a new row to the SQL table
 *  STUDENTS(NAME, AGE).
 *
 *  The example uses SQLite-3 to keep the code completely
 *  self-contained, but you can swap the calls for any other
 *  client library (PostgreSQL, MySQL, ODBC, …) if you prefer.
 *
 *  All third-party headers and libraries mentioned below are
 *  assumed to be available when you build the program.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sqlite3.h>

/*
 * insertStudent
 * ------------
 * Adds one (name, age) pair to the STUDENTS table.
 *
 *  db   : an open sqlite3 handle
 *  name : zero-terminated UTF-8 string
 *  age  : non-negative integer
 *
 * Returns 0 on success, non-zero on error.
 */
int insertStudent(sqlite3 *db, const char *name, int age)
{
    const char *sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?);";
    sqlite3_stmt *stmt = NULL;
    int rc;

    /* Prepare the parameterized statement */
    rc = sqlite3_prepare_v2(db, sql, -1, &stmt, NULL);
    if (rc != SQLITE_OK)
    {
        fprintf(stderr, "prepare failed: %s\n", sqlite3_errmsg(db));
        return rc;
    }

    /* Bind parameters */
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    sqlite3_bind_int (stmt, 2, age);

    /* Execute */
    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE)
    {
        fprintf(stderr, "insert failed: %s\n", sqlite3_errmsg(db));
    }

    /* Clean up */
    sqlite3_finalize(stmt);
    return (rc == SQLITE_DONE) ? 0 : rc;
}

/* Demo / test harness */
int main(void)
{
    sqlite3 *db = NULL;
    int rc;

    /* Open (or create) a local database file */
    rc = sqlite3_open("students.db", &db);
    if (rc != SQLITE_OK)
    {
        fprintf(stderr, "cannot open DB: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return EXIT_FAILURE;
    }

    /* Ensure the STUDENTS table exists */
    const char *ddl =
        "CREATE TABLE IF NOT EXISTS STUDENTS ("
        "  NAME TEXT NOT NULL,"
        "  AGE  INTEGER NOT NULL"
        ");";
    if (sqlite3_exec(db, ddl, NULL, NULL, NULL) != SQLITE_OK)
    {
        fprintf(stderr, "failed to create table: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return EXIT_FAILURE;
    }

    /* Insert sample row */
    if (insertStudent(db, "XXXXX", 0) != 0)
    {
        /* insertStudent already printed the error message */
        sqlite3_close(db);
        return EXIT_FAILURE;
    }

    puts("Row successfully inserted.");

    sqlite3_close(db);
    return EXIT_SUCCESS;
}