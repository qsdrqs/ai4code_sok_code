#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

// Function to write a student record to the database
void student_write(sqlite3 *db, const char *name, int age) {
    char *errMsg = 0;
    char sql[256];
    snprintf(sql, sizeof(sql), "INSERT INTO STUDENTS(NAME, AGE) VALUES('%s', %d);", name, age);

    int rc = sqlite3_exec(db, sql, 0, 0, &errMsg);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errMsg);
        sqlite3_free(errMsg);
    } else {
        fprintf(stdout, "Record created successfully\n");
    }
}

// Callback function to print each row
static int callback(void *NotUsed, int argc, char **argv, char **azColName) {
    for (int i = 0; i < argc; i++) {
        printf("%s = %s\n", azColName[i], argv[i] ? argv[i] : "NULL");
    }
    printf("\n");
    return 0;
}

int main() {
    sqlite3 *db;
    char *errMsg = 0;
    int rc;

    // Open the database
    rc = sqlite3_open("/home/codex/Documents/User_Study_UI/database.db", &db);
    if (rc) {
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        return(0);
    } else {
        fprintf(stderr, "Opened database successfully\n");
    }

    // Write a student record
    student_write(db, "XXXXX", 0);

    // Select all records from the STUDENTS table
    const char *sql = "SELECT * FROM STUDENTS";
    rc = sqlite3_exec(db, sql, callback, 0, &errMsg);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errMsg);
        sqlite3_free(errMsg);
    } else {
        fprintf(stdout, "Operation done successfully\n");
    }

    // Close the database
    sqlite3_close(db);
    return 0;
}