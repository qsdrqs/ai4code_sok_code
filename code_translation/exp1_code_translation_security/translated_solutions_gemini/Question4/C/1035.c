#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

// A helper function to check SQLite return codes and exit on error.
void check_rc(int rc, sqlite3 *db) {
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQLite error: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        exit(1);
    }
}

// Corresponds to the 'insert' arrow function in JavaScript.
// It uses a prepared statement to safely insert data.
void insert_student(sqlite3 *db, const char *name, int age) {
    sqlite3_stmt *stmt;
    const char *sql = "INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)";
    int rc;

    // 1. Prepare the SQL statement
    // This compiles the SQL query into a statement object.
    rc = sqlite3_prepare_v2(db, sql, -1, &stmt, NULL);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare statement: %s\n", sqlite3_errmsg(db));
        return; // Don't exit the whole program, just return from the function
    }

    // 2. Bind values to the statement's parameters
    // Bind the name (string) to the first parameter (?1)
    // SQLITE_STATIC tells SQLite that the string is a constant and won't be freed.
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);

    // Bind the age (integer) to the second parameter (?2)
    sqlite3_bind_int(stmt, 2, age);

    // 3. Execute the statement
    // For INSERT, UPDATE, DELETE, sqlite3_step() will return SQLITE_DONE on success.
    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "Failed to execute statement: %s\n", sqlite3_errmsg(db));
    } else {
        printf("Successfully inserted: %s, %d\n", name, age);
    }

    // 4. Finalize the statement
    // This is crucial to free up resources and prevent memory leaks.
    sqlite3_finalize(stmt);
}

// This function demonstrates how the commented-out `db.each` would be implemented.
void print_all_students(sqlite3 *db) {
    sqlite3_stmt *stmt;
    const char *sql = "SELECT * FROM STUDENTS";
    int rc;

    printf("\n--- Current Students in Database ---\n");

    rc = sqlite3_prepare_v2(db, sql, -1, &stmt, NULL);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare select statement: %s\n", sqlite3_errmsg(db));
        return;
    }

    // Loop through the results row by row
    while ((rc = sqlite3_step(stmt)) == SQLITE_ROW) {
        const unsigned char *name = sqlite3_column_text(stmt, 0);
        int age = sqlite3_column_int(stmt, 1);
        printf("Row: NAME = %s, AGE = %d\n", name, age);
    }

    if (rc != SQLITE_DONE) {
        fprintf(stderr, "Failed to fetch data: %s\n", sqlite3_errmsg(db));
    }

    sqlite3_finalize(stmt);
    printf("------------------------------------\n");
}


int main(int argc, char *argv[]) {
    sqlite3 *db;
    char *err_msg = 0;
    int rc;

    // Open the database connection. A new file 'studentdb' will be created if it doesn't exist.
    // This is equivalent to: new sqlite3.Database('studentdb');
    rc = sqlite3_open("studentdb", &db);
    check_rc(rc, db);

    fprintf(stdout, "Opened database successfully\n");

    // Create the table.
    // This is equivalent to: db.run("CREATE TABLE...");
    const char *create_sql = "CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)";
    rc = sqlite3_exec(db, create_sql, 0, 0, &err_msg);

    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", err_msg);
        sqlite3_free(err_msg);
        sqlite3_close(db);
        return 1;
    }
    
    fprintf(stdout, "Table created or already exists.\n");

    // Insert records.
    // This is equivalent to the insert("Foo", 42) and insert("Bar", 55) calls.
    // The Node.js `db.serialize` is the default behavior in the C API;
    // calls are executed sequentially.
    insert_student(db, "Foo", 42);
    insert_student(db, "Bar", 55);

    // (Optional) Show the contents of the database to verify the inserts.
    // This corresponds to the commented-out `db.each` block.
    print_all_students(db);

    // Close the database connection.
    // This is equivalent to: db.close();
    sqlite3_close(db);
    fprintf(stdout, "Database connection closed.\n");

    return 0;
}