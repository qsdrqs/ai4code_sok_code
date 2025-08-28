/*  students.c
 *  gcc students.c -lsqlite3 -o students
 */

#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

/* ------------------------------------------------------------
 * addStudent
 *   Inserts one row into STUDENTS and returns #rows affected.
 *   On any error the function prints a message and returns 0.
 * ------------------------------------------------------------ */
int addStudent(sqlite3 *db, const char *name, int age)
{
    const char      *sql =
        "INSERT INTO STUDENTS (NAME, AGE) VALUES (?1, ?2);";
    sqlite3_stmt    *stmt  = NULL;
    int              rc;

    /* Prepare parametrised statement (avoids SQL-injection). */
    rc = sqlite3_prepare_v2(db, sql, -1, &stmt, NULL);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "prepare failed: %s\n", sqlite3_errmsg(db));
        return 0;
    }

    /* Bind parameters. */
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_TRANSIENT);
    sqlite3_bind_int (stmt, 2, age);

    /* Execute statement. */
    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "step failed: %s\n", sqlite3_errmsg(db));
        sqlite3_finalize(stmt);
        return 0;
    }

    /* #rows that changed during the most recent operation. */
    int rows = sqlite3_changes(db);
    printf("Rows affected: %d\n", rows);

    sqlite3_finalize(stmt);
    return rows;
}

/* ----------------------------------------------------------------- */
/* Small test driver mirroring the JavaScript example.               */
/* ----------------------------------------------------------------- */
int main(void)
{
    sqlite3 *db = NULL;
    int      rc;

    /* Open / create the DB file. */
    rc = sqlite3_open("./database.db", &db);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "cannot open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return EXIT_FAILURE;
    }

    /* Create the table the first time this program is run. */
    const char *create_sql =
        "CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT);";
    rc = sqlite3_exec(db, create_sql, NULL, NULL, NULL);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "create table failed: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return EXIT_FAILURE;
    }

    /* Insert a test row (equivalent to addStudent("XXXXX", 0)). */
    addStudent(db, "XXXXX", 0);

    /* Print the contents of the table. */
    const char *select_sql = "SELECT NAME, AGE FROM STUDENTS;";
    sqlite3_stmt *stmt     = NULL;

    rc = sqlite3_prepare_v2(db, select_sql, -1, &stmt, NULL);
    if (rc == SQLITE_OK) {
        while ((rc = sqlite3_step(stmt)) == SQLITE_ROW) {
            const unsigned char *name = sqlite3_column_text(stmt, 0);
            int                  age  = sqlite3_column_int (stmt, 1);
            printf("%s %d\n", name, age);
        }
        sqlite3_finalize(stmt);
    } else {
        fprintf(stderr, "select failed: %s\n", sqlite3_errmsg(db));
    }

    sqlite3_close(db);
    return EXIT_SUCCESS;
}