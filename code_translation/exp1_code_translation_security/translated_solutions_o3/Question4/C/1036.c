/*
 * Translation of the original JavaScript snippet to C.
 * Requires SQLite’s C interface (sqlite3.h / sqlite3.c or a pre-built library).
 */

#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

/* -------------------------------------------------------------------------- */
/*  Callback used by sqlite3_exec() to print every row returned by the query  */
/* -------------------------------------------------------------------------- */
static int print_row(void *unused, int argc, char **argv, char **azColName)
{
    /* Expected order: id, NAME, AGE (see SELECT statement below) */
    (void)unused;       /* silence “unused parameter” warnings */

    /* Very small safety check; real-world code would be more defensive */
    if (argc >= 3)
        printf("%s: %s %s\n", argv[0] ? argv[0] : "NULL",
                               argv[1] ? argv[1] : "NULL",
                               argv[2] ? argv[2] : "NULL");
    return 0;           /* returning non-zero would abort sqlite3_exec() */
}

/* -------------------------------------------------------------------------- */
/*  Equivalent of `add_new_entry()` from the original JavaScript             */
/* -------------------------------------------------------------------------- */
void add_new_entry(const char *name, int age)
{
    sqlite3     *db        = NULL;
    sqlite3_stmt *stmt     = NULL;
    const char  *sql_insert =
        "INSERT INTO STUDENTS VALUES (?,?)";
    const char  *sql_select =
        "SELECT rowid AS id, NAME, AGE FROM STUDENTS";
    int          rc;

    /* ------------------------------------------------------ */
    /* 1. Open / create the database                          */
    /* ------------------------------------------------------ */
    rc = sqlite3_open("database.db", &db);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Cannot open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return;
    }

    /* ------------------------------------------------------ */
    /* 2. Prepare INSERT statement                            */
    /* ------------------------------------------------------ */
    rc = sqlite3_prepare_v2(db, sql_insert, -1, &stmt, NULL);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare INSERT: %s\n",
                sqlite3_errmsg(db));
        sqlite3_close(db);
        return;
    }

    /* Bind parameters (1-based indexes) */
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_TRANSIENT);
    sqlite3_bind_int(stmt, 2, age);

    /* Execute the INSERT                                      */
    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "Failed to execute INSERT: %s\n",
                sqlite3_errmsg(db));
        sqlite3_finalize(stmt);
        sqlite3_close(db);
        return;
    }
    sqlite3_finalize(stmt);   /* free compiled statement */

    /* ------------------------------------------------------ */
    /* 3. Query everything back and print each row            */
    /* ------------------------------------------------------ */
    rc = sqlite3_exec(db, sql_select, print_row, NULL, NULL);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SELECT failed: %s\n", sqlite3_errmsg(db));
        /* sqlite3_exec already cleaned up internal resources */
    }

    /* ------------------------------------------------------ */
    /* 4. Close the database                                  */
    /* ------------------------------------------------------ */
    sqlite3_close(db);
}

/* -------------------------------------------------------------------------- */
int main(void)
{
    add_new_entry("XXXXX", 0);
    return 0;
}