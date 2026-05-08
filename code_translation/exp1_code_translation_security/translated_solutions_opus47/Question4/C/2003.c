#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

void insertStudent(sqlite3 *db, const char *name, int age) {
    sqlite3_stmt *stmt;
    const char *sql = "INSERT INTO students (name, age) VALUES ($name, $age)";

    if (sqlite3_prepare_v2(db, sql, -1, &stmt, NULL) != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare statement: %s\n", sqlite3_errmsg(db));
        return;
    }

    sqlite3_bind_text(stmt, sqlite3_bind_parameter_index(stmt, "$name"),
                      name, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, sqlite3_bind_parameter_index(stmt, "$age"), age);

    if (sqlite3_step(stmt) != SQLITE_DONE) {
        fprintf(stderr, "Failed to execute statement: %s\n", sqlite3_errmsg(db));
    }

    sqlite3_finalize(stmt);
}

static int printRow(void *data, int argc, char **argv, char **azColName) {
    printf("{ ");
    for (int i = 0; i < argc; i++) {
        printf("%s: %s%s",
               azColName[i],
               argv[i] ? argv[i] : "NULL",
               i < argc - 1 ? ", " : "");
    }
    printf(" }\n");
    return 0;
}

int main(void) {
    sqlite3 *db;
    char *err_msg = NULL;
    int rc;

    /* Open an in-memory database (equivalent to new sqlite3.Database(':memory:')) */
    rc = sqlite3_open(":memory:", &db);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Cannot open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return 1;
    }

    /* CREATE TABLE students (name TEXT, age INT) */
    rc = sqlite3_exec(db,
                      "CREATE TABLE students (name TEXT, age INT)",
                      NULL, NULL, &err_msg);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", err_msg);
        sqlite3_free(err_msg);
        sqlite3_close(db);
        return 1;
    }

    /* insertStudent(db, "XXXXX", 0); */
    insertStudent(db, "XXXXX", 0);

    /* db.each("SELECT * FROM students", ...) */
    rc = sqlite3_exec(db, "SELECT * FROM students", printRow, NULL, &err_msg);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", err_msg);
        sqlite3_free(err_msg);
    }

    /* db.close(); */
    sqlite3_close(db);
    return 0;
}