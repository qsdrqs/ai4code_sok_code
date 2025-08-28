#include <sqlite3.h>
#include <stdio.h>
#include <stdlib.h>

// Callback function for printing rows from the STUDENTS table
static int callback(void* data, int argc, char** argv, char** azColName) {
    for (int i = 0; i < argc; i++) {
        printf("%s = %s\n", azColName[i], argv[i] ? argv[i] : "NULL");
    }
    printf("\n");
    return 0;
}

int main() {
    // Open the database
    sqlite3* db;
    int rc = sqlite3_open("studentdb", &db);
    if (rc) {
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        return 1;
    }

    // Create table if it doesn't exist
    char* sql = "CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)";
    rc = sqlite3_exec(db, sql, NULL, NULL, NULL);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", sqlite3_errmsg(db));
    }

    // Function to insert a student into the STUDENTS table
    void insert(const char* name, int age) {
        // Prepare the SQL statement
        sqlite3_stmt* stmt;
        const char* insert_sql = "INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)";
        rc = sqlite3_prepare_v2(db, insert_sql, -1, &stmt, 0);
        if (rc != SQLITE_OK) {
            fprintf(stderr, "SQL prepare error: %s\n", sqlite3_errmsg(db));
            return;
        }

        // Bind parameters
        sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
        sqlite3_bind_int(stmt, 2, age);

        // Execute the statement
        rc = sqlite3_step(stmt);
        if (rc != SQLITE_DONE) {
            fprintf(stderr, "SQL execution error: %s\n", sqlite3_errmsg(db));
        }

        // Finalize the statement
        sqlite3_finalize(stmt);
    }

    // Insert students
    insert("Foo", 42);
    insert("Bar", 55);

    // Fetch and print all rows from the STUDENTS table
    sql = "SELECT * FROM STUDENTS";
    rc = sqlite3_exec(db, sql, callback, NULL, NULL);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", sqlite3_errmsg(db));
    }

    // Close the database
    sqlite3_close(db);
    return 0;
}