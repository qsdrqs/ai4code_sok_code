#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

// This is the C equivalent of the JavaScript `addNewEntry` function.
// Note that we must pass the database handle 'db' as an argument,
// as it's not available in a global scope like in the JS example.
void addNewEntry(sqlite3 *db, const char *name, int age) {
    char *zErrMsg = 0;
    int rc;
    sqlite3_stmt *stmt;

    // The 'db.serialize' in Node.js ensures commands run sequentially.
    // The C API is synchronous by default, so calls are already sequential.
    // We simply execute the commands one after another.

    // Corresponds to: db.run("CREATE TABLE IF NOT EXISTS STUDENTS (NAME text, AGE INT)");
    // We use sqlite3_exec for simple, one-off statements that don't return data.
    const char *sql_create = "CREATE TABLE IF NOT EXISTS STUDENTS (NAME text, AGE INT)";
    rc = sqlite3_exec(db, sql_create, 0, 0, &zErrMsg);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error on CREATE TABLE: %s\n", zErrMsg);
        sqlite3_free(zErrMsg);
        // In a real application, you would handle this error more robustly.
    }

    // Corresponds to: const stmt = db.prepare("INSERT INTO STUDENTS VALUES (?, ?)");
    // We prepare a statement for execution to safely bind parameters.
    const char *sql_insert = "INSERT INTO STUDENTS VALUES (?, ?)";
    rc = sqlite3_prepare_v2(db, sql_insert, -1, &stmt, NULL);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare statement: %s\n", sqlite3_errmsg(db));
        return;
    }

    // Corresponds to: stmt.run(name, age);
    // In C, this is a two-step process: bind parameters, then execute (step).

    // 1. Bind parameters. The index is 1-based.
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC); // Bind the name string
    sqlite3_bind_int(stmt, 2, age);                      // Bind the age integer

    // 2. Execute the statement.
    // For an INSERT, we expect SQLITE_DONE when it's finished.
    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "Execution failed: %s\n", sqlite3_errmsg(db));
    }

    // Corresponds to: stmt.finalize();
    // This is crucial to release resources associated with the prepared statement.
    sqlite3_finalize(stmt);
}

// This is a callback function that will be used by sqlite3_exec to print rows.
// It mimics the behavior of the arrow function in `db.each`.
// (void *data) is for custom data, (int argc) is column count,
// (char **argv) is the row data, (char **azColName) is the column names.
static int select_callback(void *data, int argc, char **argv, char **azColName){
   // The 'row' object in JS is represented here by the argv and azColName arrays.
   // We will manually format the output to look like the JS object.
   printf("{ ");
   for(int i = 0; i<argc; i++){
      printf("%s: '%s'%s", azColName[i], argv[i] ? argv[i] : "NULL", i < argc - 1 ? ", " : "");
   }
   printf(" }\n");
   return 0;
}


int main(int argc, char* argv[]) {
    sqlite3 *db;
    char *zErrMsg = 0;
    int rc;

    // Corresponds to: const db = new sqlite3.Database(':memory:');
    // This opens a connection to an in-memory database.
    rc = sqlite3_open(":memory:", &db);

    if (rc) {
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        return(1);
    } else {
        fprintf(stdout, "Opened database successfully\n\n");
    }

    // Corresponds to: addNewEntry("XXXXX", 0);
    addNewEntry(db, "XXXXX", 0);
    addNewEntry(db, "XXXXX", 0);

    // Corresponds to: db.each("SELECT NAME, AGE FROM STUDENTS", (err, row) => { ... });
    // The `db.each` method executes a query and calls a function for each row.
    // The C equivalent is to use `sqlite3_exec` with a callback function.
    // The `select_callback` function will be invoked for each row found.
    const char *sql_select = "SELECT NAME, AGE FROM STUDENTS";
    rc = sqlite3_exec(db, sql_select, select_callback, 0, &zErrMsg);

    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error on SELECT: %s\n", zErrMsg);
        sqlite3_free(zErrMsg);
    }

    // It's essential to close the database connection to free resources.
    sqlite3_close(db);

    return 0;
}