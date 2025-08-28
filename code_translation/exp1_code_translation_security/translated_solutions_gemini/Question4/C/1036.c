#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

/*
 * This is a callback function that sqlite3_exec will call for each row
 * returned by the SELECT statement. It's the C equivalent of the anonymous
 * function in `db.each("...", function(err, row) { ... });`
 *
 * Parameters:
 *   - NotUsed: A user-defined pointer passed from sqlite3_exec. We don't need it here.
 *   - argc: The number of columns in the result row.
 *   - argv: An array of strings representing the data for each column.
 *   - azColName: An array of strings representing the name of each column.
 *
 * Return:
 *   - 0 to continue processing rows. A non-zero value would stop the execution.
 */
static int callback(void *NotUsed, int argc, char **argv, char **azColName) {
    // The JS code prints: row.id + ": " + row.NAME + " " + row.AGE
    // Assuming the SELECT statement column order is id, NAME, AGE,
    // argv[0] will be the id, argv[1] the NAME, and argv[2] the AGE.
    printf("%s: %s %s\n", argv[0], argv[1], argv[2]);
    return 0;
}

/*
 * The C version of the add_new_entry function.
 * Note that in C, we must explicitly handle return codes for error checking.
 */
void add_new_entry(const char *name, int age) {
    sqlite3 *db;
    sqlite3_stmt *stmt; // This is the prepared statement object
    char *err_msg = 0;
    int rc;

    // In JS: new sqlite3.Database('database.db');
    // In C, we use sqlite3_open(). It returns a status code.
    rc = sqlite3_open("database.db", &db);

    if (rc != SQLITE_OK) {
        fprintf(stderr, "Cannot open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return;
    }

    // The `db.serialize` in the Node.js library ensures sequential execution.
    // The C API is sequential by default, so there is no direct equivalent;
    // we just execute our statements one after another.

    // --- 1. Prepare and run the INSERT statement ---

    // In JS: db.prepare("INSERT INTO STUDENTS VALUES (?,?)");
    const char *sql_insert = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?);";
    rc = sqlite3_prepare_v2(db, sql_insert, -1, &stmt, 0);

    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare statement: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return;
    }

    // In JS, `stmt.run(name, age)` binds parameters and executes.
    // In C, this is a two-step process: bind, then step.

    // Bind the string value 'name' to the first placeholder (?)
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);

    // Bind the integer value 'age' to the second placeholder (?)
    sqlite3_bind_int(stmt, 2, age);

    // Execute the statement
    rc = sqlite3_step(stmt);

    if (rc != SQLITE_DONE) {
        fprintf(stderr, "Execution failed: %s\n", sqlite3_errmsg(db));
    }

    // In JS: stmt.finalize();
    // This is crucial to release resources.
    sqlite3_finalize(stmt);


    // --- 2. Select and print all entries ---

    // In JS: db.each("SELECT ...", callback);
    // In C, the sqlite3_exec function is the closest equivalent. It executes
    // an SQL query and calls a callback function for each result row.
    const char *sql_select = "SELECT rowid AS id, NAME, AGE FROM STUDENTS";
    rc = sqlite3_exec(db, sql_select, callback, 0, &err_msg);

    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to select data\n");
        fprintf(stderr, "SQL error: %s\n", err_msg);
        sqlite3_free(err_msg); // Free the error message memory
    }

    // In JS: db.close();
    sqlite3_close(db);
}

int main(void) {
    // This is equivalent to the JavaScript call at the end of the script.
    // To demonstrate, we'll create the table first if it doesn't exist.
    
    // --- Setup: Create table if it doesn't exist ---
    sqlite3 *db;
    char *err_msg = 0;
    int rc = sqlite3_open("database.db", &db);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Cannot open database: %s\n", sqlite3_errmsg(db));
        return 1;
    }
    
    const char *sql_create = "CREATE TABLE IF NOT EXISTS STUDENTS(NAME TEXT, AGE INT);";
    rc = sqlite3_exec(db, sql_create, 0, 0, &err_msg);
    
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", err_msg);
        sqlite3_free(err_msg);
        sqlite3_close(db);
        return 1;
    }
    sqlite3_close(db);
    // --- End Setup ---

    printf("Adding new entry...\n");
    add_new_entry("XXXXX", 0);
    printf("\nOperation finished.\n");

    return 0;
}