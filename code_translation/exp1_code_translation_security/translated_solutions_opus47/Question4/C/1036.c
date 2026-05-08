#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

/* Callback invoked once per row returned by sqlite3_exec,
   mirroring the db.each(...) callback in the JavaScript version. */
static int each_row_callback(void *data, int argc, char **argv, char **azColName) {
    (void)data;
    (void)argc;
    (void)azColName;
    /* argv[0] = id, argv[1] = NAME, argv[2] = AGE */
    printf("%s: %s %s\n", argv[0], argv[1], argv[2]);
    return 0;
}

void add_new_entry(const char *name, int age) {
    sqlite3 *db;
    sqlite3_stmt *stmt;
    char *err_msg = NULL;
    int rc;

    /* new sqlite3.Database('database.db') */
    rc = sqlite3_open("database.db", &db);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Cannot open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return;
    }

    /* db.prepare("INSERT INTO STUDENTS VALUES (?,?)") */
    const char *insert_sql = "INSERT INTO STUDENTS VALUES (?,?)";
    rc = sqlite3_prepare_v2(db, insert_sql, -1, &stmt, NULL);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare statement: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return;
    }

    /* stmt.run(name, age) */
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 2, age);

    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "Execution failed: %s\n", sqlite3_errmsg(db));
    }

    /* stmt.finalize() */
    sqlite3_finalize(stmt);

    /* db.each("SELECT rowid AS id, NAME, AGE FROM STUDENTS", ...) */
    const char *select_sql = "SELECT rowid AS id, NAME, AGE FROM STUDENTS";
    rc = sqlite3_exec(db, select_sql, each_row_callback, NULL, &err_msg);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", err_msg);
        sqlite3_free(err_msg);
    }

    /* db.close() */
    sqlite3_close(db);
}

int main(void) {
    add_new_entry("XXXXX", 0);
    return 0;
}