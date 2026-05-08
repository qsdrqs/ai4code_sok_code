#include <stdio.h>
#include <sqlite3.h>

extern sqlite3 *db;

void addSQLentry(const char *name, int age) {
    const char *sql = "INSERT INTO students (name, age) VALUES (?, ?)";
    sqlite3_stmt *stmt;
    int rc;

    rc = sqlite3_prepare_v2(db, sql, -1, &stmt, NULL);
    if (rc != SQLITE_OK) {
        printf("%s\n", sqlite3_errmsg(db));
        return;
    }

    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 2, age);

    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        printf("%s\n", sqlite3_errmsg(db));
    }

    printf("User added with id: %lld\n", (long long)sqlite3_last_insert_rowid(db));

    sqlite3_finalize(stmt);
}