/*
 *  students.c   – C version of the JavaScript helpers shown in the question
 *
 *  $ cc students.c -lsqlite3 -o students
 *  $ ./students
 */

#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

/* --------------------------------------------------------------------------
   Utility: open (or create) a database file called “mydb.sqlite”
   -------------------------------------------------------------------------- */
static sqlite3 *open_database(void)
{
    sqlite3 *db = NULL;
    int rc = sqlite3_open("mydb.sqlite", &db);

    if (rc != SQLITE_OK)
    {
        fprintf(stderr, "Cannot open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);            /* db may be NULL, sqlite3 handles that */
        return NULL;
    }

    /* Optional but nice: enforce foreign keys, etc. */
    sqlite3_exec(db, "PRAGMA foreign_keys = ON;", 0, 0, 0);

    return db;
}

/* --------------------------------------------------------------------------
   Equivalent of JavaScript createTable()
   -------------------------------------------------------------------------- */
static int create_table(sqlite3 *db)
{
    const char *sql =
        "CREATE TABLE IF NOT EXISTS students ("
        " id   INTEGER PRIMARY KEY,"
        " name TEXT,"
        " age  INTEGER"
        ");";

    char *errmsg = 0;
    int rc = sqlite3_exec(db, sql, 0, 0, &errmsg);

    if (rc != SQLITE_OK)
    {
        fprintf(stderr, "Error occurred while creating the table: %s\n", errmsg);
        sqlite3_free(errmsg);
        return rc;
    }

    puts("Table created successfully.");
    return SQLITE_OK;
}

/* --------------------------------------------------------------------------
   Equivalent of JavaScript insertStudent(name, age)
   -------------------------------------------------------------------------- */
static int insert_student(sqlite3 *db, const char *name, int age)
{
    /* Using a prepared statement for parameters (avoids SQL injection, etc.) */
    const char *sql = "INSERT INTO students (name, age) VALUES (?, ?);";
    sqlite3_stmt *stmt = NULL;
    int rc;

    rc = sqlite3_prepare_v2(db, sql, -1, &stmt, NULL);
    if (rc != SQLITE_OK)
    {
        fprintf(stderr, "sqlite3_prepare_v2 failed: %s\n", sqlite3_errmsg(db));
        return rc;
    }

    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_TRANSIENT);  /* “?” #1 */
    sqlite3_bind_int (stmt, 2, age);                         /* “?” #2 */

    rc = sqlite3_step(stmt);          /* actually execute the INSERT */
    if (rc != SQLITE_DONE)
    {
        fprintf(stderr, "sqlite3_step failed: %s\n", sqlite3_errmsg(db));
    }
    else
    {
        puts("Record inserted.");
        rc = SQLITE_OK;
    }

    sqlite3_finalize(stmt);
    return rc;
}

/* --------------------------------------------------------------------------
   Example main() so you can compile & test immediately
   -------------------------------------------------------------------------- */
int main(void)
{
    sqlite3 *db = open_database();
    if (!db) return EXIT_FAILURE;

    if (create_table(db) != SQLITE_OK)
    {
        sqlite3_close(db);
        return EXIT_FAILURE;
    }

    /* Insert some demo data */
    insert_student(db, "Alice", 20);
    insert_student(db, "Bob",   22);

    sqlite3_close(db);
    return EXIT_SUCCESS;
}