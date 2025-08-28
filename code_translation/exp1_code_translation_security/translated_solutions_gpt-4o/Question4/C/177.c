#include <stdio.h>
#include <stdlib.h>
#include <sqlite3.h>

void addStudent(const char *name, int age) {
    sqlite3 *db;
    char *errMsg = 0;
    int rc;
    char sql[256];

    // Open database
    rc = sqlite3_open("students.db", &db);
    if (rc) {
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        return;
    }

    // Create SQL statement
    snprintf(sql, sizeof(sql), "INSERT INTO STUDENTS (NAME, AGE) VALUES ('%s', %d);", name, age);

    // Execute SQL statement
    rc = sqlite3_exec(db, sql, 0, 0, &errMsg);
    if (rc != SQLITE_OK) {
        fprintf(stderr, "SQL error: %s\n", errMsg);
        sqlite3_free(errMsg);
    } else {
        fprintf(stdout, "Records created successfully\n");
    }

    // Close database
    sqlite3_close(db);
}

int main() {
    addStudent("John Doe", 20);
    return 0;
}