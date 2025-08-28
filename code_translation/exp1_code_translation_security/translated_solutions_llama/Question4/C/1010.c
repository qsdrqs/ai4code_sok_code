#include <sqlite3.h>
#include <stdio.h>
#include <stdlib.h>

// Function to add a new student to the database
int addStudent(sqlite3* db, const char* name, int age) {
    char* errorMessage;
    char sql[100];
    sprintf(sql, "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d)", name, age);

    // Execute SQL query
    int rc = sqlite3_exec(db, sql, NULL, NULL, &errorMessage);

    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errorMessage);
        sqlite3_free(errorMessage);
    }

    return sqlite3_changes(db);
}

int main() {
    sqlite3* db;
    char* errorMessage;

    // Open the database
    int rc = sqlite3_open("./database.db", &db);

    if (rc) {
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        return 1;
    }

    // Create table if it doesn't exist
    char* createTableSQL = "CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)";
    rc = sqlite3_exec(db, createTableSQL, NULL, NULL, &errorMessage);

    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errorMessage);
        sqlite3_free(errorMessage);
    }

    // Add a new student
    const char* name = "XXXXX";
    int age = 0;
    int rowsAffected = addStudent(db, name, age);
    printf("Rows affected: %d\n", rowsAffected);

    // Display contents of database
    sqlite3_stmt* stmt;
    char* selectSQL = "SELECT * FROM STUDENTS";
    rc = sqlite3_prepare_v2(db, selectSQL, -1, &stmt, 0);

    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL prepare error: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return 1;
    }

    while ((rc = sqlite3_step(stmt)) == SQLITE_ROW) {
        const unsigned char* studentName = sqlite3_column_text(stmt, 0);
        int studentAge = sqlite3_column_int(stmt, 1);
        printf("%s, %d\n", studentName, studentAge);
    }

    if (rc != SQLITE_DONE) {
        fprintf(stderr, "SQL execution error: %s\n", sqlite3_errmsg(db));
    }

    sqlite3_finalize(stmt);

    // Close database
    sqlite3_close(db);

    return 0;
}