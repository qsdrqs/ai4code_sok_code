#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

// Function to insert a student into the database
void insertStudent(sqlite3 *db, const char *name, int age) {
    char *errMsg = 0;
    char sql[256];
    snprintf(sql, sizeof(sql), "INSERT INTO students (name, age) VALUES ('%s', %d);", name, age);

    int rc = sqlite3_exec(db, sql, 0, 0, &errMsg);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errMsg);
        sqlite3_free(errMsg);
    }
}

int main() {
    sqlite3 *db;
    char *errMsg = 0;
    int rc;

    // Open an in-memory database
    rc = sqlite3_open(":memory:", &db);
    if (rc) {
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        return 1;
    }

    // Create the students table
    const char *createTableSQL = "CREATE TABLE students (name TEXT, age INT);";
    rc = sqlite3_exec(db, createTableSQL, 0, 0, &errMsg);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errMsg);
        sqlite3_free(errMsg);
        sqlite3_close(db);
        return 1;
    }

    // Insert a student
    insertStudent(db, "XXXXX", 0);

    // Callback function to print each row
    int callback(void *NotUsed, int argc, char **argv, char **azColName) {
        for (int i = 0; i < argc; i++) {
            printf("%s = %s\n", azColName[i], argv[i] ? argv[i] : "NULL");
        }
        printf("\n");
        return 0;
    }

    // Select all students and print them
    const char *selectSQL = "SELECT * FROM students;";
    rc = sqlite3_exec(db, selectSQL, callback, 0, &errMsg);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errMsg);
        sqlite3_free(errMsg);
    }

    // Close the database
    sqlite3_close(db);

    return 0;
}