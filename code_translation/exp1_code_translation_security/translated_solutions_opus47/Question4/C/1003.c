#include <stdio.h>
#include <sqlite3.h>
#include "student.h"

void addStudent(sqlite3 *db, const char *name, int age) {
    sqlite3_stmt *stmt = NULL;
    int rc = sqlite3_prepare_v2(db,
        "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)", -1, &stmt, NULL);
    if (rc == SQLITE_OK) {
        sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
        sqlite3_bind_int(stmt, 2, age);
        rc = sqlite3_step(stmt);
    }
    sqlite3_finalize(stmt); // safe to call even if stmt is NULL
    if (rc == SQLITE_DONE) {
        printf("inserted null\n");
    } else {
        printf("inserted %s\n", sqlite3_errmsg(db));
    }
}