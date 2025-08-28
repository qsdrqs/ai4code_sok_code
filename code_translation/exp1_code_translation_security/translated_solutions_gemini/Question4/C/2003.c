#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

// Forward declaration for the student insertion function
void insertStudent(sqlite3 *db, const char *name, int age);

// This callback function mimics the behavior of the (err, row) => { ... }
// in the JavaScript db.each() call. It gets called for each row returned
// by the SELECT statement.
//
// Parameters:
// - NotUsed: An argument passed from sqlite3_exec (we don't use it here).
// - argc: The number of columns in the row.
// - argv: An array of strings representing the data for each column.
// - azColName: An array of strings representing the name of each column.
//
// The function should return 0 to continue processing rows, or non-zero to stop.
static int select_callback(void *NotUsed, int argc, char **argv, char **azColName) {
    // The JS code logs an object like { name: 'XXXXX', age: 0 }
    // We will replicate that output format.
    printf("{ ");
    for (int i = 0; i < argc; i++) {
        // Print "key: 'value'" for text and "key: value" for others to look like a JS object
        // In this simple case, we know 'name' is TEXT and 'age' is INT.
        if (i == 0) { // name column
             printf("%s: '%s'", azColName[i], argv[i] ? argv[i] : "NULL");
        } else { // age column
             printf("%s: %s", azColName[i], argv[i] ? argv[i] : "NULL");
        }

        if (i < argc - 1) {
            printf(", ");
        }
    }
    printf(" }\n");
    return 0;
}

int main(int argc, char* argv[]) {
    sqlite3 *db;
    char *err_msg = 0;
    int rc;

    // const db = new sqlite3.Database(':memory:');
    // Open an in-memory database
    rc = sqlite3_open(":memory:", &db);

    if (rc != SQLITE_OK) {
        fprintf(stderr, "Cannot open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return 1;
    }

    // db.serialize(() => { ... });
    // The C API is sequential by default, so we just execute commands one after another.
    // db.run("CREATE TABLE students (name TEXT, age INT)");
    const char *sql_create = "CREATE TABLE students (name TEXT, age INT);";
    rc = sqlite3_exec(db, sql_create, 0, 0, &err_msg);

    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", err_msg);
        sqlite3_free(err_msg); // Free the error message
        sqlite3_close(db);
        return 1;
    }

    // insertStudent(db, "XXXXX", 0);
    insertStudent(db, "XXXXX", 0);

    // db.each("SELECT * FROM students", callback);
    const char *sql_select = "SELECT * FROM students;";
    rc = sqlite3_exec(db, sql_select, select_callback, 0, &err_msg);

    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to select data: %s\n", err_msg);
        sqlite3_free(err_msg);
        sqlite3_close(db);
        return 1;
    }

    // db.close();
    sqlite3_close(db);

    return 0;
}

// function insertStudent(db, $name, $age) { ... }
void insertStudent(sqlite3 *db, const char *name, int age) {
    sqlite3_stmt *stmt;
    // Use placeholders (?) for parameters to prevent SQL injection
    const char *sql_insert = "INSERT INTO students (name, age) VALUES (?, ?);";
    int rc;

    // 1. Prepare the statement
    rc = sqlite3_prepare_v2(db, sql_insert, -1, &stmt, 0);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare statement: %s\n", sqlite3_errmsg(db));
        return;
    }

    // 2. Bind values to the placeholders.
    // The second argument is the 1-based index of the placeholder.
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 2, age);

    // 3. Step (execute) the statement
    // For an INSERT, we expect SQLITE_DONE.
    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "Execution failed: %s\n", sqlite3_errmsg(db));
    }

    // 4. Finalize (clean up) the statement to free resources
    sqlite3_finalize(stmt);
}