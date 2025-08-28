/* student_demo.c */

#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

/* -------------------------------------------------------------------------- */
/* Insert a student (name, age) into the table                                */
/* -------------------------------------------------------------------------- */
static void insertStudent(sqlite3 *db, const char *name, int age)
{
    /* SQL statement with the-same named parameters used in the JS version */
    const char *sql = "INSERT INTO students (name, age) VALUES ($name, $age)";
    sqlite3_stmt *stmt = NULL;

    if (sqlite3_prepare_v2(db, sql, -1, &stmt, NULL) != SQLITE_OK)
    {
        fprintf(stderr, "prepare failed: %s\n", sqlite3_errmsg(db));
        return;
    }

    /* Bind by parameter name (mirrors $name, $age) */
    int p_name = sqlite3_bind_parameter_index(stmt, "$name");
    int p_age  = sqlite3_bind_parameter_index(stmt, "$age");

    sqlite3_bind_text(stmt, p_name, name, -1, SQLITE_TRANSIENT);
    sqlite3_bind_int (stmt, p_age,  age);

    if (sqlite3_step(stmt) != SQLITE_DONE)
        fprintf(stderr, "insert failed: %s\n", sqlite3_errmsg(db));

    sqlite3_finalize(stmt);
}

/* -------------------------------------------------------------------------- */
/* Main driver (equivalent to the original JS script)                         */
/* -------------------------------------------------------------------------- */
int main(void)
{
    sqlite3 *db = NULL;

    /* Open in-memory database (":memory:" behaves the same as JS example) */
    if (sqlite3_open(":memory:", &db) != SQLITE_OK)
    {
        fprintf(stderr, "cannot open db: %s\n", sqlite3_errmsg(db));
        return EXIT_FAILURE;
    }

    /* Create table */
    const char *create_sql = "CREATE TABLE students (name TEXT, age INT)";
    if (sqlite3_exec(db, create_sql, NULL, NULL, NULL) != SQLITE_OK)
    {
        fprintf(stderr, "table create failed: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return EXIT_FAILURE;
    }

    /* Insert one student (same data as JS code) */
    insertStudent(db, "XXXXX", 0);

    /* Query and print every row (equivalent to db.each in JS) */
    const char *select_sql = "SELECT * FROM students";
    sqlite3_stmt *stmt = NULL;

    if (sqlite3_prepare_v2(db, select_sql, -1, &stmt, NULL) != SQLITE_OK)
    {
        fprintf(stderr, "select prepare failed: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return EXIT_FAILURE;
    }

    while (sqlite3_step(stmt) == SQLITE_ROW)
    {
        const unsigned char *name = sqlite3_column_text(stmt, 0);
        int age                  = sqlite3_column_int (stmt, 1);

        /* Console output (console.log equivalent) */
        printf("{ name: \"%s\", age: %d }\n", name, age);
    }

    sqlite3_finalize(stmt);
    sqlite3_close(db);
    return EXIT_SUCCESS;
}