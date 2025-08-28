#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

/*
Given an SQL table named "STUDENTS" with columns "NAME" and "AGE", add a new entry

Args:
    name: string for the "NAME" of the entry
    age: int for the "AGE" of the entry
    db: a given database connection
*/
void addEntry(const char *name, int age, sqlite3 *db) {
    char *errMsg = 0;
    char sql[256];

    // Construct the SQL statement
    snprintf(sql, sizeof(sql), "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d);", name, age);

    // Execute the SQL statement
    int rc = sqlite3_exec(db, sql, 0, 0, &errMsg);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errMsg);
        sqlite3_free(errMsg);
    } else {
        printf("Entry added successfully\n");
    }
}

int main() {
    sqlite3 *db;
    int rc;

    // Open the database connection
    rc = sqlite3_open("students.db", &db);
    if (rc) {
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        return 1;
    } else {
        fprintf(stderr, "Opened database successfully\n");
    }

    // Add an entry
    addEntry("John Doe", 20, db);

    // Close the database connection
    sqlite3_close(db);

    return 0;
}