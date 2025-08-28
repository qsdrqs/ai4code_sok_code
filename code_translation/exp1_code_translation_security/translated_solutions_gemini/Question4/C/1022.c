#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

// This is the callback function that sqlite3_exec will call for each row of the result set.
// It mimics the behavior of the anonymous function in `db.all(...)`.
// - data: User-provided data pointer (not used here, so it's NULL).
// - argc: The number of columns in the row.
// - argv: An array of strings representing each column's value.
// - azColName: An array of strings representing each column's name.
static int select_callback(void *data, int argc, char **argv, char **azColName) {
    // The JS code `console.log(row, row)` prints the row object twice.
    // Here, we will print the row's contents once in a readable format.
    printf("Row: ");
    for (int i = 0; i < argc; i++) {
        // Print "columnName = value" for each column.
        printf("%s = %s  ", azColName[i], argv[i] ? argv[i] : "NULL");
    }
    printf("\n");
    return 0; // A non-zero return would stop the query.
}

// A function to insert a student record into the database.
// This is equivalent to the JavaScript `student_write` function.
void student_write(sqlite3 *db, const char *name, int age) {
    sqlite3_stmt *stmt;
    const char *sql = "INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)";
    int rc;

    // 1. Prepare the SQL statement
    rc = sqlite3_prepare_v2(db, sql, -1, &stmt, NULL);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare statement: %s\n", sqlite3_errmsg(db));
        return;
    }

    // 2. Bind the parameters to the statement
    // Bind the name string to the first placeholder (?)
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    // Bind the age integer to the second placeholder (?)
    sqlite3_bind_int(stmt, 2, age);

    // 3. Execute the statement
    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "Failed to execute statement: %s\n", sqlite3_errmsg(db));
    } else {
        printf("Successfully inserted student: %s, %d\n", name, age);
    }

    // 4. Finalize the statement to free resources
    sqlite3_finalize(stmt);
}

int main(void) {
    sqlite3 *db;
    char *err_msg = 0;
    int rc;

    // Equivalent to: new sqlite3.Database(...)
    const char *db_path = "/home/codex/Documents/User_Study_UI/database.db";
    rc = sqlite3_open(db_path, &db);

    if (rc != SQLITE_OK) {
        fprintf(stderr, "Cannot open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return 1;
    }
    printf("Database connection established.\n\n");

    // For robustness, let's ensure the table exists.
    // This is good practice and often implicitly handled by frameworks.
    const char *create_table_sql = "CREATE TABLE IF NOT EXISTS STUDENTS("
                                   "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                                   "NAME TEXT NOT NULL, "
                                   "AGE INT NOT NULL);";
    rc = sqlite3_exec(db, create_table_sql, 0, 0, &err_msg);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", err_msg);
        sqlite3_free(err_msg);
        sqlite3_close(db);
        return 1;
    }

    // Equivalent to: student_write("XXXXX", 0)
    student_write(db, "XXXXX", 0);
    printf("\n--- Fetching all students ---\n");

    // Equivalent to: db.all("SELECT * FROM STUDENTS", function(err, rows) { ... });
    const char *select_sql = "SELECT * FROM STUDENTS";
    rc = sqlite3_exec(db, select_sql, select_callback, 0, &err_msg);

    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to select data: %s\n", err_msg);
        sqlite3_free(err_msg);
    }
    printf("---------------------------\n\n");

    // Close the database connection
    sqlite3_close(db);
    printf("Database connection closed.\n");

    return 0;
}