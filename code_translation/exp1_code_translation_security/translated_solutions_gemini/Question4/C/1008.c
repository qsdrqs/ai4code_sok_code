#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h> // The primary dependency, providing all database functions.

/**
 * @brief C function to create the "students" table.
 *
 * This function translates the JavaScript createTable() function.
 * It executes a "CREATE TABLE IF NOT EXISTS" SQL statement.
 * Instead of alert(), it prints messages to the console.
 *
 * @param db A pointer to the opened SQLite3 database connection.
 */
void createTable(sqlite3 *db) {
    char *sql = "CREATE TABLE IF NOT EXISTS students ("
                "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                "name TEXT NOT NULL, "
                "age INTEGER NOT NULL);";

    char *err_msg = 0;
    int rc = sqlite3_exec(db, sql, 0, 0, &err_msg);

    if (rc != SQLITE_OK) {
        // Equivalent to the JavaScript error callback
        fprintf(stderr, "Error occurred while creating the table: %s\n", err_msg);
        sqlite3_free(err_msg);
    } else {
        // Equivalent to the JavaScript success callback
        printf("Table created successfully\n");
    }
}

/**
 * @brief C function to insert a student's name and age into the 'students' table.
 *
 * This function translates the JavaScript insertStudent() function.
 * It uses a prepared statement to safely insert data and prevent SQL injection.
 *
 * @param db A pointer to the opened SQLite3 database connection.
 * @param name The name of the student (string).
 * @param age The age of the student (integer).
 */
void insertStudent(sqlite3 *db, const char *name, int age) {
    // The SQL statement with placeholders (?) for data binding
    char *sql = "INSERT INTO students (name, age) VALUES (?, ?);";
    sqlite3_stmt *stmt; // This is the prepared statement object

    // 1. Prepare the SQL statement
    // This compiles the SQL query into a byte-code program that can be executed.
    int rc = sqlite3_prepare_v2(db, sql, -1, &stmt, 0);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Cannot prepare statement: %s\n", sqlite3_errmsg(db));
        return;
    }

    // 2. Bind the values to the placeholders
    // The first parameter is the statement, the second is the 1-based index of the placeholder.
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 2, age);

    // 3. Execute the statement
    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "Execution failed: %s\n", sqlite3_errmsg(db));
    } else {
        printf("Student '%s' inserted successfully.\n", name);
    }

    // 4. Finalize the statement to free resources
    sqlite3_finalize(stmt);
}

/**
 * @brief Main function to demonstrate the database operations.
 *
 * This function acts as the driver for the program. It:
 * 1. Opens a database connection (creating the file if it doesn't exist).
 * 2. Calls createTable() to ensure the table exists.
 * 3. Calls insertStudent() multiple times to add data.
 * 4. Closes the database connection.
 */
int main(void) {
    sqlite3 *db;
    int rc;

    // The JavaScript openDatabase() is equivalent to sqlite3_open() in C.
    // This opens the database file "mydb.db". If it doesn't exist, it will be created.
    rc = sqlite3_open("mydb.db", &db);

    if (rc) {
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        return 1;
    } else {
        fprintf(stdout, "Opened database successfully\n");
    }

    // Call the translated functions
    createTable(db);
    insertStudent(db, "Alice", 20);
    insertStudent(db, "Bob", 22);
    insertStudent(db, "Charlie", 21);

    // Close the database connection to free resources
    sqlite3_close(db);

    return 0;
}