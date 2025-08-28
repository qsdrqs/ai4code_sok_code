/*  File : students.c
 *
 *  Small one-to-one translation of the JavaScript example to C.
 *  The only external dependency is the public SQLite C interface
 *  (normally delivered in the packages “libsqlite3-dev” / “sqlite3.h”).
 *
 *  $ cc students.c -lsqlite3 -o students
 *  $ ./students
 */

#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

/* ------------------------------------------------------------------ */
/*  INSERT helper – corresponds to JavaScript function student_write  */
/* ------------------------------------------------------------------ */
static void student_write(sqlite3 *db, const char *name, int age)
{
    static const char *sql =
        "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?);";

    sqlite3_stmt *stmt = NULL;

    /* prepare INSERT statement */
    if (sqlite3_prepare_v2(db, sql, -1, &stmt, NULL) != SQLITE_OK) {
        fprintf(stderr, "prepare failed: %s\n", sqlite3_errmsg(db));
        return;
    }

    /* bind parameters */
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_TRANSIENT);
    sqlite3_bind_int (stmt, 2, age);

    /* execute */
    if (sqlite3_step(stmt) != SQLITE_DONE)
        fprintf(stderr, "execution failed: %s\n", sqlite3_errmsg(db));

    sqlite3_finalize(stmt);
}

/* ------------------------------------------------------------- */
/*  Callback used by sqlite3_exec for SELECT * FROM STUDENTS     */
/* ------------------------------------------------------------- */
static int print_row(void *unused, int argc, char **argv, char **colname)
{
    (void)unused;  /* silence -Wunused-parameter */

    for (int i = 0; i < argc; ++i)
        printf("%s = %s\t", colname[i], argv[i] ? argv[i] : "NULL");

    putchar('\n');
    return 0;       /* continue enumeration */
}

/* ------------------------  main  ----------------------------- */
int main(void)
{
    sqlite3 *db = NULL;

    if (sqlite3_open("/home/codex/Documents/User_Study_UI/database.db", &db)
            != SQLITE_OK) {
        fprintf(stderr, "cannot open database: %s\n", sqlite3_errmsg(db));
        return EXIT_FAILURE;
    }

    student_write(db, "XXXXX", 0);

    const char *sql_select = "SELECT * FROM STUDENTS;";
    if (sqlite3_exec(db, sql_select, print_row, NULL, NULL) != SQLITE_OK)
        fprintf(stderr, "select failed: %s\n", sqlite3_errmsg(db));

    sqlite3_close(db);
    return EXIT_SUCCESS;
}