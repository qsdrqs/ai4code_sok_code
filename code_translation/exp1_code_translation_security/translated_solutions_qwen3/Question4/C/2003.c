#include <sqlite3.h>
#include <stdio.h>

// Function to insert a student into the database
void insertStudent(sqlite3* db, const char* name, int age) {
    const char *sql = "INSERT INTO students (name, age) VALUES ($name, $age)";
    sqlite3_stmt *stmt;

    // Prepare the SQL statement
    sqlite3_prepare_v2(db, sql, -1, &stmt, NULL);

    // Bind the named parameters
    sqlite3_bind_text(stmt, sqlite3_bind_parameter_index(stmt, "$name"), name, -1, SQLITE_TRANSIENT);
    sqlite3_bind_int(stmt, sqlite3_bind_parameter_index(stmt, "$age"), age);

    // Execute the statement
    sqlite3_step(stmt);

    // Finalize the statement
    sqlite3_finalize(stmt);
}

int main() {
    sqlite3 *db;

    // Open an in-memory database
    sqlite3_open_v2(":memory:", &db, SQLITE_OPEN_READWRITE | SQLITE_OPEN_CREATE, NULL);

    // Create the students table
    sqlite3_exec(db, "CREATE TABLE students (name TEXT, age INT)", NULL, NULL, NULL);

    // Insert a student
    insertStudent(db, "XXXXX", 0);

    // Query and print all students
    sqlite3_stmt *stmt;
    sqlite3_prepare_v2(db, "SELECT * FROM students", -1, &stmt, NULL);

    while (sqlite3_step(stmt) == SQLITE_ROW) {
        const unsigned char *name = sqlite3_column_text(stmt, 0);
        int age = sqlite3_column_int(stmt, 1);
        printf("Name: %s, Age: %d\n", name, age);
    }

    // Clean up
    sqlite3_finalize(stmt);
    sqlite3_close(db);

    return 0;
}