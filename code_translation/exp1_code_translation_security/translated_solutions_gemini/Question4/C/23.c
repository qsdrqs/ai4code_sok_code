#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

/**
 * @brief Inserts a new student record into the STUDENTS table in the database.
 *
 * This function connects to an SQLite database file named "students.db".
 * It ensures the 'STUDENTS' table exists and then inserts a new row
 * with the provided name and age. It uses prepared statements to prevent
 * SQL injection vulnerabilities.
 *
 * @param name The name of the student (string).
 * @param age The age of the student (integer).
 */
void insertStudent(const char* name, int age) {
    sqlite3 *db;
    sqlite3_stmt *stmt; // This is the prepared statement object
    int rc; // To store return codes from SQLite functions

    // 1. Open the database connection.
    // SQLite will create the file "students.db" if it does not exist.
    rc = sqlite3_open("students.db", &db);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Cannot open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return;
    }

    // 2. Create the STUDENTS table if it doesn't exist.
    // This is good practice for robust applications.
    const char *create_sql = "CREATE TABLE IF NOT EXISTS STUDENTS("
                             "ID   INTEGER PRIMARY KEY AUTOINCREMENT,"
                             "NAME TEXT NOT NULL,"
                             "AGE  INT  NOT NULL);";
    rc = sqlite3_exec(db, create_sql, 0, 0, 0);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to create table: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return;
    }

    // 3. Prepare the SQL INSERT statement.
    // Using '?' placeholders is the standard way to prevent SQL injection.
    const char *insert_sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?);";
    rc = sqlite3_prepare_v2(db, insert_sql, -1, &stmt, 0);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to prepare statement: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return;
    }

    // 4. Bind the input parameters to the prepared statement.
    // The first '?' is at index 1.
    sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    sqlite3_bind_int(stmt, 2, age);

    // 5. Execute the statement.
    // For an INSERT, we expect SQLITE_DONE.
    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "Execution failed: %s\n", sqlite3_errmsg(db));
    } else {
        // This is equivalent to the 'success' callback in the JS code.
        printf("Successfully inserted student: %s, Age: %d\n", name, age);
    }

    // 6. Clean up.
    // Finalize the statement and close the database connection to free resources.
    sqlite3_finalize(stmt);
    sqlite3_close(db);
}

int main() {
    // Example usage, similar to the JavaScript call.
    insertStudent("Alice", 22);
    insertStudent("Bob", 35);
    
    // The original JS example used placeholder data.
    insertStudent("XXXXX", 0);

    return 0;
}