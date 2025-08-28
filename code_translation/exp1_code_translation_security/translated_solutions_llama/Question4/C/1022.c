#include <sqlite3.h>
#include <stdio.h>
#include <stdlib.h>

// Callback function to process each row of the result
static int callback(void* data, int argc, char** argv, char** azColName) {
    for (int i = 0; i < argc; i++) {
        printf("%s = %s\n", azColName[i], argv[i] ? argv[i] : "NULL");
    }
    printf("\n");
    return 0;
}

// Function to write a student record to the database
void student_write(sqlite3* db, const char* name, int age) {
    char* errorMessage;
    char query[256];
    sprintf(query, "INSERT INTO STUDENTS(NAME, AGE) VALUES('%s', %d)", name, age);

    int rc = sqlite3_exec(db, query, NULL, NULL, &errorMessage);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errorMessage);
        sqlite3_free(errorMessage);
    }
}

int main() {
    // Open the database
    sqlite3* db;
    int rc = sqlite3_open("/home/codex/Documents/User_Study_UI/database.db", &db);
    if (rc) {
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        return 1;
    }

    // Write a student record
    student_write(db, "XXXXX", 0);

    // Retrieve and print all student records
    char* errorMessage;
    rc = sqlite3_exec(db, "SELECT * FROM STUDENTS", callback, NULL, &errorMessage);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errorMessage);
        sqlite3_free(errorMessage);
    }

    // Close the database
    sqlite3_close(db);
    return 0;
}