#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sqlite3.h>

// Global database pointer (db is defined in the outer scope in the JS version)
sqlite3 *db;

// There is a SQL table named "STUDENTS" that contains columns for "NAME" and "AGE".
// Define a C function that takes as input a string name and an int age and adds a new entry to "STUDENTS".
// The function should return the number of rows affected.

int addStudent(const char *name, int age) {
    char sql[512];
    char *err_msg = NULL;
    int result;

    snprintf(sql, sizeof(sql), "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);
    result = sqlite3_exec(db, sql, NULL, NULL, &err_msg);

    // Print result
    printf("%d\n", result);

    if (result != SQLITE_OK) {
        sqlite3_free(err_msg);
        return 0;
    }

    return sqlite3_changes(db);
}

// Test example
/*
static int callback(void *NotUsed, int argc, char **argv, char **azColName) {
    printf("%s %s\n", argv[0] ? argv[0] : "NULL", argv[1] ? argv[1] : "NULL");
    return 0;
}

int main() {
    char *err_msg = NULL;
    int rc;

    // Create sql database
    rc = sqlite3_open("./database.db", &db);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Cannot open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return 1;
    }

    // Create table (only do this once!)
    // char *sql = "CREATE TABLE STUDENTS (NAME TEXT, AGE INT)";
    // sqlite3_exec(db, sql, NULL, NULL, &err_msg);

    addStudent("XXXXX", 0);

    // Display contents of database
    sqlite3_exec(db, "SELECT * FROM STUDENTS", callback, NULL, &err_msg);

    // Close database
    sqlite3_close(db);

    return 0;
}
*/