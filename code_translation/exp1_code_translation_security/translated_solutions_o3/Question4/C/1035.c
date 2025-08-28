#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

static sqlite3 *db = NULL;

/* ------------------------------------------------------------------ */
/* INSERT ROUTINE – equivalent to the JS arrow-function “insert”       */
void insert(const char *name, int age)
{
    const char *sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?);";
    sqlite3_stmt *stmt = NULL;

    /* Prepare the parametrised statement */
    if (sqlite3_prepare_v2(db, sql, -1, &stmt, NULL) != SQLITE_OK) {
        fprintf(stderr, "prepare error: %s\n", sqlite3_errmsg(db));
        return;
    }

    /* Bind parameters: index starts at 1 */
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_TRANSIENT);
    sqlite3_bind_int (stmt, 2, age);

    /* Execute (step once) */
    if (sqlite3_step(stmt) != SQLITE_DONE) {
        fprintf(stderr, "step error: %s\n", sqlite3_errmsg(db));
    }

    /* Finalise (= JS stmt.finalize()) */
    sqlite3_finalize(stmt);
}
/* ------------------------------------------------------------------ */

int main(void)
{
    /* Open / create the database file “studentdb” */
    if (sqlite3_open("studentdb", &db) != SQLITE_OK) {
        fprintf(stderr, "cannot open DB: %s\n", sqlite3_errmsg(db));
        return EXIT_FAILURE;
    }

    /* Create table if it does not yet exist */
    const char *create_sql =
        "CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT);";

    if (sqlite3_exec(db, create_sql, NULL, NULL, NULL) != SQLITE_OK) {
        fprintf(stderr, "create table error: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return EXIT_FAILURE;
    }

    /* Same calls as in the JS sample */
    insert("Foo", 42);
    insert("Bar", 55);

    /* ------------------------------------------------------------------ */
    /* Example SELECT (commented out, mirroring the original JS block)     */
    /*
    const char *query = "SELECT * FROM STUDENTS;";
    int callback(void *unused, int argc, char **argv, char **colname) {
        for (int i = 0; i < argc; ++i)
            printf("%s = %s\n", colname[i], argv[i] ? argv[i] : "NULL");
        putchar('\n');
        return 0;
    }
    sqlite3_exec(db, query, callback, NULL, NULL);
    */
    /* ------------------------------------------------------------------ */

    sqlite3_close(db);
    return EXIT_SUCCESS;
}