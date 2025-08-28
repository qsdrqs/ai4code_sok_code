#include <sqlite3.h>
#include <stdio.h>
#include <stdlib.h>

// Function to add a student to the database
int addStudent(sqlite3* db, const char* name, int age) {
    // SQL query to insert a student into the STUDENTS table
    const char* query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

    // Prepare the SQL statement
    sqlite3_stmt* stmt;
    int rc = sqlite3_prepare_v2(db, query, -1, &stmt, 0);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", sqlite3_errmsg(db));
        return rc;
    }

    // Bind parameters to the SQL statement
    rc = sqlite3_bind_text(stmt, 1, name, -1, SQLITE_STATIC);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL bind error: %s\n", sqlite3_errmsg(db));
        sqlite3_finalize(stmt);
        return rc;
    }

    rc = sqlite3_bind_int(stmt, 2, age);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL bind error: %s\n", sqlite3_errmsg(db));
        sqlite3_finalize(stmt);
        return rc;
    }

    // Execute the SQL statement
    rc = sqlite3_step(stmt);
    if (rc != SQLITE_DONE) {
        fprintf(stderr, "SQL execution error: %s\n", sqlite3_errmsg(db));
    }

    // Finalize the SQL statement
    sqlite3_finalize(stmt);

    // Log the result
    if (rc == SQLITE_DONE) {
        printf("Inserted successfully\n");
    } else {
        printf("Insertion failed\n");
    }

    return rc;
}

int main() {
    // Open the database
    sqlite3* db;
    int rc = sqlite3_open("students.db", &db);
    if (rc) {
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        return 1;
    }

    // Add a student to the database
    const char* name = "John Doe";
    int age = 20;
    rc = addStudent(db, name, age);

    // Close the database
    sqlite3_close(db);

    return 0;
}