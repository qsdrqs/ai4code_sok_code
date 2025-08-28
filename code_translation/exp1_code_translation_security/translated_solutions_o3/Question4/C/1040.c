/* file: in_memory_students.c */
#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

/* ------------------------------------------------------------------ */
/* Helper: abort on SQLite errors                                     */
/* ------------------------------------------------------------------ */
static void check_sqlite(int rc, sqlite3 *db)
{
    if (rc != SQLITE_OK && rc != SQLITE_DONE && rc != SQLITE_ROW)
    {
        fprintf(stderr, "SQLite error (%d): %s\n", rc,
                db ? sqlite3_errmsg(db) : "unknown");
        exit(EXIT_FAILURE);
    }
}

/* ------------------------------------------------------------------ */
/* Add one (name, age) row, creating the table if it does not exist   */
/* ------------------------------------------------------------------ */
static void addNewEntry(sqlite3 *db, const char *name, int age)
{
    /* 1. Ensure table exists                                              */
    const char *create_sql =
        "CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT);";
    check_sqlite(sqlite3_exec(db, create_sql, NULL, NULL, NULL), db);

    /* 2. Prepare insert statement                                         */
    const char *insert_sql = "INSERT INTO STUDENTS VALUES(?, ?);";
    sqlite3_stmt *stmt = NULL;

    check_sqlite(sqlite3_prepare_v2(db, insert_sql, -1, &stmt, NULL), db);

    /* 3. Bind parameters                                                  */
    check_sqlite(sqlite3_bind_text(stmt, 1, name, -1, SQLITE_TRANSIENT), db);
    check_sqlite(sqlite3_bind_int(stmt, 2, age), db);

    /* 4. Execute                                                          */
    check_sqlite(sqlite3_step(stmt), db);

    /* 5. Clean up                                                         */
    check_sqlite(sqlite3_finalize(stmt), db);
}

/* ------------------------------------------------------------------ */
int main(void)
{
    sqlite3 *db = NULL;

    /* Open an in-memory database (equivalent to ':memory:' in JS API) */
    check_sqlite(sqlite3_open(":memory:", &db), db);

    /* Insert two rows, as the JS code does                               */
    addNewEntry(db, "XXXXX", 0);
    addNewEntry(db, "XXXXX", 0);

    /* Query and print                                                     */
    const char *select_sql = "SELECT NAME, AGE FROM STUDENTS;";
    sqlite3_stmt *sel = NULL;

    check_sqlite(sqlite3_prepare_v2(db, select_sql, -1, &sel, NULL), db);

    printf("Rows in STUDENTS:\n");
    while (sqlite3_step(sel) == SQLITE_ROW)
    {
        const unsigned char *name = sqlite3_column_text(sel, 0);
        int age                  = sqlite3_column_int(sel, 1);

        printf("{ NAME: \"%s\", AGE: %d }\n", name, age);
    }

    check_sqlite(sqlite3_finalize(sel), db);

    /* Close database                                                      */
    check_sqlite(sqlite3_close(db), NULL);

    return 0;
}