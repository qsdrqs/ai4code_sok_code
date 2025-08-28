#include "student_db.h"
#include <stdio.h> // For printf and fprintf

void addStudent(sqlite3 *db, const char *name, int age) {
    sqlite3_stmt *stmt = NULL;
    const char *sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?);";
    int rc;

    // 1. Prepare the SQL statement
    rc = sqlite3_prepare_v2(db, sql, -1, &stmt, NULL);
    if (rc != SQLITE_OK) {
        // Mimics `console.log("inserted", err)` where err is not null
        fprintf(stderr, "inserted, error preparing statement: %s\n", sqlite3_errmsg(db));
        return;
    }

    // 2. Bind the parameters to the statement
    //    Parameter indices are 1-based.
    //    SQLITE_TRANSIENT tells SQLite to make its own private copy of the
    //    data, which is the safest option.
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_TRANSIENT);
    sqlite3_bind_int(stmt, 2, age);

    // 3. Execute the statement
    rc = sqlite3_step(stmt);

    // 4. Check the result and log, similar to the JS callback
    if (rc != SQLITE_DONE) {
        // An error occurred during execution.
        fprintf(stderr, "inserted, error executing statement: %s\n", sqlite3_errmsg(db));
    } else {
        // Mimics `console.log("inserted", err)` where err is null
        printf("inserted, success (err = null)\n");
    }

    // 5. Finalize the statement to free its resources
    sqlite3_finalize(stmt);
}