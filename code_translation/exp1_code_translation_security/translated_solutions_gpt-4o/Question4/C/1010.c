#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

// Function to add a student to the STUDENTS table
int addStudent(sqlite3 *db, const char *name, int age) {
    char *errMsg = 0;
    char sql[256];
    int rc;

    // Prepare SQL statement
    snprintf(sql, sizeof(sql), "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d);", name, age);

    // Execute SQL statement
    rc = sqlite3_exec(db, sql, 0, 0, &errMsg);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errMsg);
        sqlite3_free(errMsg);
        return 0;
    } else {
        printf("Record created successfully\n");
        return sqlite3_changes(db); // Return the number of rows affected
    }
}

int main() {
    sqlite3 *db;
    char *errMsg = 0;
    int rc;

    // Open database
    rc = sqlite3_open("database.db", &db);
    if (rc) {
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        return 0;
    } else {
        fprintf(stderr, "Opened database successfully\n");
    }

    // Uncomment the following lines to create the table (only do this once)
    /*
    const char *createTableSQL = "CREATE TABLE STUDENTS (NAME TEXT, AGE INT);";
    rc = sqlite3_exec(db, createTableSQL, 0, 0, &errMsg);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errMsg);
        sqlite3_free(errMsg);
    } else {
        printf("Table created successfully\n");
    }
    */

    // Add a student
    addStudent(db, "XXXXX", 0);

    // Display contents of the database
    const char *selectSQL = "SELECT * FROM STUDENTS;";
    sqlite3_stmt *stmt;
    rc = sqlite3_prepare_v2(db, selectSQL, -1, &stmt, 0);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "Failed to fetch data: %s\n", sqlite3_errmsg(db));
    } else {
        while (sqlite3_step(stmt) == SQLITE_ROW) {
            const unsigned char *name = sqlite3_column_text(stmt, 0);
            int age = sqlite3_column_int(stmt, 1);
            printf("Name: %s, Age: %d\n", name, age);
        }
    }
    sqlite3_finalize(stmt);

    // Close database
    sqlite3_close(db);

    return 0;
}