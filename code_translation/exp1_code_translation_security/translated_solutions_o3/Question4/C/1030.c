/*  temp.c – C version of the original JS program
 *
 *  gcc temp.c -lsqlite3 -o temp
 */

#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

/* forward declarations */
static void  create_tables(sqlite3 *db);
static void  insert_row(sqlite3 *db, const char *name, int age);
static void  show_rows(sqlite3 *db);
static int   print_row(void *unused, int argc, char **argv, char **colname);

/* ------------------------------------------------------------------------- */
int main(void)
{
    printf("created database\n");

    sqlite3 *db = NULL;
    int rc = sqlite3_open("temp.db", &db);

    if (rc != SQLITE_OK) {
        fprintf(stderr, "Getting error: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return EXIT_FAILURE;
    }

    create_tables(db);      /* creates table, inserts a row, prints table */

    sqlite3_close(db);
    return EXIT_SUCCESS;
}

/* ------------------------------------------------------------------------- */
static void create_tables(sqlite3 *db)
{
    printf("pre-created tables\n");

    const char *sql =
        "CREATE TABLE IF NOT EXISTS students("
        "  name TEXT,"
        "  age  INT"
        ");";

    char *errmsg = NULL;
    int rc = sqlite3_exec(db, sql, NULL, NULL, &errmsg);

    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errmsg);
        sqlite3_free(errmsg);
        return;
    }

    insert_row(db, "John Doe", 30);
    show_rows(db);
}

/* ------------------------------------------------------------------------- */
static void insert_row(sqlite3 *db, const char *name, int age)
{
    const char *sql = "INSERT INTO students(name, age) VALUES(?, ?);";

    sqlite3_stmt *stmt = NULL;
    int rc = sqlite3_prepare_v2(db, sql, -1, &stmt, NULL);

    if (rc != SQLITE_OK) {
        fprintf(stderr, "Prepare failed: %s\n", sqlite3_errmsg(db));
        return;
    }

    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_TRANSIENT);
    sqlite3_bind_int (stmt, 2, age);

    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE)
        fprintf(stderr, "Execution failed: %s\n", sqlite3_errmsg(db));
    else
        printf("row added successfully\n");

    sqlite3_finalize(stmt);
}

/* ------------------------------------------------------------------------- */
static void show_rows(sqlite3 *db)
{
    const char *sql = "SELECT name, age FROM students;";
    char *errmsg    = NULL;

    int rc = sqlite3_exec(db, sql, print_row, NULL, &errmsg);

    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errmsg);
        sqlite3_free(errmsg);
    }
}

/* SQLite callback that prints a single row */
static int print_row(void *unused, int argc, char **argv, char **colname)
{
    (void)unused; /* parameter not used */

    for (int i = 0; i < argc; ++i)
        printf("%s%s", argv[i] ? argv[i] : "NULL", (i == argc - 1) ? "\n" : " ");

    return 0;   /* continue scanning */
}